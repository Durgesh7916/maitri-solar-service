package com.msedcl.main.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.msedcl.main.constants.ApiConstants;
import com.msedcl.main.constants.MessageConstants;
import com.msedcl.main.constants.ProcedureConstants;
import com.msedcl.main.constants.QueryConstants;
import com.msedcl.main.dto.ApiResponse;
import com.msedcl.main.dto.ArrearsResponseDto;
import com.msedcl.main.dto.Payload;
import com.msedcl.main.dto.PmsResponseDto;
import com.msedcl.main.dto.SolarRequestDto;
import com.msedcl.main.exception.ResourceNotFoundException;

import jakarta.annotation.PostConstruct;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import oracle.jdbc.OracleTypes;

@Service
public class SolarService {

	@Autowired
	private DataSource dataSource;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private ArrearsService arrearsService;
	
	@Autowired
	private SmsService smsService;
	
	

	
	
	@PostConstruct
    public void testDbConnection() {
        try (Connection con = dataSource.getConnection()) {
            System.out.println("=================================");
           // System.out.println("DB URL = " + con.getMetaData().getURL());
           // System.out.println("DB USER = " + con.getMetaData().getUserName());
            System.out.println("DB CONNECTION SUCCESS");
        } catch (Exception e) {
            System.out.println("DB CONNECTION FAILED: " + e.getMessage());
        }
	}
	
	
	// ======================================================
	// TEST CONNECTION
	// ======================================================
	public String testConnection() {

		validateDatabaseConnection();

		return "Oracle DB Connected Successfully";
	}

	// ======================================================
	// ORIGINAL FUNCTION (PRESERVED)
	// ======================================================
	public Map<String, Object> getSolarConsumerInfo(String consumerNo) {

		validateDatabaseConnection();

		Map<String, Object> finalResponse = new HashMap<>();

		Connection conn = null;

		CallableStatement cs = null;

		try {

			conn = dataSource.getConnection();

			cs = conn.prepareCall(ProcedureConstants.GET_SOLAR_INFO);

			/*
			 * INPUT
			 */
			cs.setString(1, consumerNo);

			/*
			 * OUTPUT CURSORS
			 */
			cs.registerOutParameter(2, OracleTypes.CURSOR);
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			cs.registerOutParameter(4, OracleTypes.CURSOR);
			cs.registerOutParameter(5, OracleTypes.CURSOR);
			cs.registerOutParameter(6, OracleTypes.CURSOR);

			
			System.out.println("hello");
			/*
			 * EXECUTE
			 */
			cs.execute();

			/*
			 * CURSOR 1
			 */
			ResultSet rs1 = (ResultSet) cs.getObject(2);

			List<Map<String, Object>> appInfoList = resultSetToList(rs1);

			/*
			 * CONSUMER NOT FOUND / PD CHECK
			 */
			if (appInfoList == null || appInfoList.isEmpty()) {

				throw new ResourceNotFoundException("Consumer Number " + consumerNo + " is PD or not found");
			}

			/*
			 * CURSOR 2
			 */
			ResultSet rs2 = (ResultSet) cs.getObject(3);

			List<Map<String, Object>> voltageList = resultSetToList(rs2);

			/*
			 * CURSOR 3
			 */
			ResultSet rs3 = (ResultSet) cs.getObject(4);

			List<Map<String, Object>> schemeList = resultSetToList(rs3);

			/*
			 * CURSOR 4
			 */
			ResultSet rs4 = (ResultSet) cs.getObject(5);

			List<Map<String, Object>> generatorTypeList = resultSetToList(rs4);

			/*
			 * CURSOR 5
			 */
			ResultSet rs5 = (ResultSet) cs.getObject(6);

			List<Map<String, Object>> revisedLoadList = resultSetToList(rs5);

			/*
			 * FINAL RESPONSE
			 */
			finalResponse.put("consumerInfo", appInfoList);

			finalResponse.put("voltageValidation", voltageList);

			finalResponse.put("solarSchemes", schemeList);

			finalResponse.put("generatorTypes", generatorTypeList);

			finalResponse.put("revisedLoadDemand", revisedLoadList);

			/*
			 * DEBUG PRINT
			 */
			printList("Consumer Info", appInfoList);

			printList("Voltage Validation", voltageList);

			printList("Solar Schemes", schemeList);

			printList("Generator Types", generatorTypeList);

			printList("Revised Load Demand", revisedLoadList);

			return finalResponse;

		} catch (ResourceNotFoundException e) {

			throw e;

		} catch (Exception e) {

			e.printStackTrace();

			String fullError = e.toString();

			System.out.println("FULL ERROR = " + fullError);

			/*
			 * ORA-01403 HANDLING
			 */
			if (fullError.contains("ORA-01403")) {

				throw new ResourceNotFoundException("No Consumer Data Found For Consumer Number : " + consumerNo);
			}

			/*
			 * OTHER ERROR
			 */
			throw new RuntimeException("Error While Fetching Consumer Data");

		} finally {

			try {

				if (cs != null) {
					cs.close();
				}

				if (conn != null) {
					conn.close();
				}

			} catch (Exception ex) {

				ex.printStackTrace();
			}
		}
	}

	// ======================================================
	// CREATE SOLAR APPLICATION (WITH LOAD EXTENSION FLOW FIXED)
	// ======================================================
	public String createSolarApplication(SolarRequestDto req) {

		validateDatabaseConnection();

		Connection conn = null;
		CallableStatement loadCs = null;
		CallableStatement solarCs = null;
		ResultSet rs = null;

		try {

			conn = dataSource.getConnection();

			// System.out.println("DB Connection Successful");

			// ==================================================
			// STEP 1: FETCH EXISTING LOAD
			// ==================================================
			try {

				CallableStatement getLoadInfo = conn.prepareCall(ProcedureConstants.GET_SOLAR_INFO);

				getLoadInfo.setString(1, req.getConsumerNumber().trim());

				getLoadInfo.registerOutParameter(2, OracleTypes.CURSOR);
				getLoadInfo.registerOutParameter(3, OracleTypes.CURSOR);
				getLoadInfo.registerOutParameter(4, OracleTypes.CURSOR);
				getLoadInfo.registerOutParameter(5, OracleTypes.CURSOR);
				getLoadInfo.registerOutParameter(6, OracleTypes.CURSOR);

				getLoadInfo.execute();

				rs = (ResultSet) getLoadInfo.getObject(2);

				if (rs == null) {

					return "Consumer Data Not Found";
				}

				if (rs.next()) {

					String consumerStatus = rs.getString("Consumer_Status");

					if (consumerStatus != null && !consumerStatus.equalsIgnoreCase("0")
							&& !consumerStatus.equalsIgnoreCase("C") && !consumerStatus.equalsIgnoreCase("1")
							&& !consumerStatus.equalsIgnoreCase("T")) {

						return "Consumer " + req.getConsumerNumber() + " is PD";

						// your code
					}

					req.setExistingLoad(rs.getDouble("SANCTIONED_LOAD"));

					// req.setCons(rs.getString("LT_HT_CD"));

					req.setConsumerCategoryExisting(rs.getString("LT_HT_CD"));

					String[] arr = req.getReGeneratorString().split("@");

					String newCapacity = arr[2]; // 5
					String existingCapacity = arr[3]; // 0

					req.setNewReCapacity(Double.parseDouble(newCapacity));

				}

				rs.close();
				getLoadInfo.close();

			} catch (SQLException e) {

				if (e.getMessage().contains("ORA-01403")) {

					return "Consumer Data Not Found Pl Provide Valid Consumer Number";
				}

				throw e;
			}

			/*
			 * CallableStatement getLoadInfo =
			 * conn.prepareCall("{call pk_pay_nc_charges.getAppInfoSolarPv(?,?,?,?,?,?)}");
			 * 
			 * getLoadInfo.setString(1, req.getConsumerNumber());
			 * 
			 * getLoadInfo.registerOutParameter(2, OracleTypes.CURSOR);
			 * getLoadInfo.registerOutParameter(3, OracleTypes.CURSOR);
			 * getLoadInfo.registerOutParameter(4, OracleTypes.CURSOR);
			 * getLoadInfo.registerOutParameter(5, OracleTypes.CURSOR);
			 * getLoadInfo.registerOutParameter(6, OracleTypes.CURSOR);
			 * 
			 * getLoadInfo.execute();
			 * 
			 * rs = (ResultSet) getLoadInfo.getObject(2);
			 * 
			 * // ResultSetMetaData md = rs.getMetaData();
			 * 
			 * if(rs==null) { return
			 * "Consumer Data Not Found Pl Provide Valid Consumer Number "; }
			 */

			/*
			 * for (int i = 1; i <= md.getColumnCount(); i++) {
			 * 
			 * System.out.println(md.getColumnName(i)); }
			 * 
			 */

			System.out.println("Existing Load = " + req.getExistingLoad());

			// ==================================================
			// STEP 2: LOAD EXTENSION LOGIC
			// ==================================================
			boolean loadExtensionRequired = req.getNewLoad() > req.getExistingLoad();

			//boolean reCapacityCheck = (req.getExistReSancCapacity() + req.getNewReCapacity()) > req.getExistingLoad();
			
			boolean reCapacityCheck =
			        Math.ceil(req.getExistReSancCapacity() + req.getNewReCapacity())
			        > Math.ceil(req.getExistingLoad());
			        
			        
			/*
			 * boolean reCapacityCheck = Math.round((float)(req.getExistReSancCapacity() +
			 * req.getNewReCapacity())) > Math.round((float)req.getExistingLoad());
			 */

			int loadChangeApplicationId = 0;

			// if (loadExtensionRequired) {

			/*
			 * System.out.println("Load Extension Required");
			 * 
			 * loadCs =
			 * conn.prepareCall("{ ? = call PK_LOAD_CHANGE.CREATE_APP(?,?,?,?,?) }");
			 * 
			 * loadCs.registerOutParameter(1, java.sql.Types.INTEGER);
			 * 
			 * loadCs.setString(2, req.getConsumerNumber()); loadCs.setInt(3,
			 * req.getNewLoad()); loadCs.setInt(4, req.getNewLoadUnitId()); loadCs.setInt(5,
			 * req.getNewDemand()); loadCs.setString(6, req.getMobile());
			 * 
			 * loadCs.execute();
			 * 
			 * loadChangeApplicationId = loadCs.getInt(1);
			 * 
			 * System.out.println("Load Change App ID = " + loadChangeApplicationId);
			 * 
			 * // STRICT VALIDATION if (loadChangeApplicationId <= 0) { return
			 * "ERROR: Load Change Application not created"; }
			 */
			// req.setLoadChangeApplicationId(loadChangeApplicationId);

			// } else {

			// System.out.println("No Load Extension Required");
			// req.setLoadChangeApplicationId(0);
			// }

			// ==================================================
			// STEP 3: SAFETY CHECK BEFORE SOLAR APP
			// ==================================================

			if (loadExtensionRequired) {
				req.setLoadChangeApplicationId(0);
				return "ERROR: Cannot proceed to Solar Application, Load Extension Required First Create Load Change Application \n"
						+ "Applied Load=" + req.getNewLoad() + "  Existing load =" + req.getExistingLoad();
			}

			if (reCapacityCheck) {
				// req.setLoadChangeApplicationId(0);
				return "ERROR: Cannot proceed to Solar Application, "
						+ "RE Sanctioned Capacity Cannot Be Greater Then Existing Total Load\n"
						+ "Total RE Capacity Applied =" + (req.getExistReSancCapacity() + req.getNewReCapacity()) + "\n"
						+ "Existing Total load =" + req.getExistingLoad() + "\n"
						+ "Please Extend Load OR Apply Solar Capacity UpTo Total Load";
			}
			
			
			if(req.getExistReSancCapacity()>0)
			{
			if( req.getNewReCapacity()>req.getExistReSancCapacity() ||  req.getNewReCapacity()<req.getExistReSancCapacity())
			{
				return "Error : Extension/Reduction Of Solar Capacity Not Allowed"
						+ "Total RE Capacity Applied =" + (req.getExistReSancCapacity() + req.getNewReCapacity()) + "\n"
				        +"Existing capacity =" + req.getExistReSancCapacity() + "\n"
				        +"New Capacity ="+req.getNewReCapacity();
			}
			
			if( req.getNewReCapacity()>req.getExistReSancCapacity() ||  req.getNewReCapacity()<req.getExistReSancCapacity())
			{
				
				if(req.getCapacityExtensionFlagYn().equalsIgnoreCase("N"))
                {
	                return "Error :For Extension/Reduction Of Solar Capacity Capacity Extension Flag Should be Y"; 
	
                }
				
			}
			}
			if(req.getSolarSchemeCode()==5)
			{
			return "Error : Solar Smart Scheme Application Not Allowed From Maitri Portal"	;		}
			
			

			// Validate solarSchemeCode
			/*
			 * String validateSql = """ SELECT COUNT(1) FROM solar_scheme WHERE
			 * Status_cd_c='A' and solar_scheme_code = ? """;
			 */

			Integer count = jdbcTemplate.queryForObject(QueryConstants.VALIDATE_SOLAR_SCHEME, Integer.class, req.getSolarSchemeCode());

			if (count == null || count == 0) {
				return MessageConstants.INVALID_SOLAR_SCHEME;
			}
			// end solarSchemeCode

			// Validate solarReTypeId
			/*
			 * String reTypeSql = """ SELECT COUNT(1) FROM nc_renew_energy_generator_type
			 * WHERE Status_cd_c='A' and re_generator_type_id_n = ? """;
			 */

			Integer reTypeCount = jdbcTemplate.queryForObject(QueryConstants.VALIDATE_RE_TYPE, Integer.class, req.getSolarReTypeId());

			if (reTypeCount == null || reTypeCount == 0) {

				return MessageConstants.INVALID_RE_TYPE;

			}
			// end solarReTypeId

			// Validate reArrangementId
			/*
			 * String rearrangementSql = """ SELECT COUNT(1) FROM re_arrangements_master
			 * WHERE arrangement_id_n = ? """;
			 */

			Integer rearrangementCount = jdbcTemplate.queryForObject(QueryConstants.VALIDATE_REARRANGEMENT, Integer.class,
					req.getReArrangementId());

			if (rearrangementCount == null || rearrangementCount == 0) {

				return MessageConstants.INVALID_RE_TYPE;

			}
			// end reArrangementId

			// Validate reGeneratorString
			String reGeneratorString = req.getReGeneratorString();

			if (reGeneratorString == null || reGeneratorString.isBlank()) {

				return "ERROR: Re Generator String is required";

			}

			String[] values = reGeneratorString.split("@");

			// Check minimum values
			if (values.length < 2) {

				return "ERROR: Invalid Re Generator String Format";
			}

			/*
			 * VALIDATE re_generator_type_id First value of reGeneratorString
			 */
			if (values.length > 0) {

				Integer reGeneratorTypeId = Integer.parseInt(values[0]);

				/*
				 * CHECK MASTER TABLE
				 */
				/*
				 * String reGeneratorMasterSql = """ SELECT COUNT(1) FROM
				 * nc_renew_energy_generator_type WHERE status_cd_c='A' AND
				 * re_generator_type_id_n = ? """;
				 */

				Integer reGeneratorMasterCount = jdbcTemplate.queryForObject(QueryConstants.VALIDATE_RE_TYPE, Integer.class,
						reGeneratorTypeId);

				/*
				 * MASTER VALIDATION
				 */
				if (reGeneratorMasterCount == null || reGeneratorMasterCount == 0) {

					return "ERROR: reGeneratorString reGenerator type id not found in master";
				}

				/*
				 * MATCH WITH solarReTypeId
				 */
				if (!reGeneratorTypeId.equals(req.getSolarReTypeId())) {

					return "ERROR: reGeneratorString re_generator_type_id should be same as solarReTypeId";
				}
			}

			// Second value = voltage id
			Integer voltageId = Integer.parseInt(values[1]);

			// Validate voltageId from nc_rst_capacity table
			/*
			 * String voltageSql = """ SELECT COUNT(1) FROM nc_voltage_master WHERE
			 * voltage_id_n = ? """;
			 */

			Integer voltageCount = jdbcTemplate.queryForObject(QueryConstants.VALIDATE_VOLTAGE, Integer.class, voltageId);

			if (voltageCount == null || voltageCount == 0) {

				return MessageConstants.INVALID_VOLTAGE;

			}

			// end reGeneratorString

			// Validate reArrangementId = 3 condition
			if (req.getReArrangementId() == 3) {

				if (!"Y".equalsIgnoreCase(req.getLoadSeparatedYn())
						|| !"Y".equalsIgnoreCase(req.getConnectedParallelYn())) {

					return "ERROR: Pl Select LoadSeparatedYn And ConnectedParallelYn As Y";

				}
			}

			
			if("Y".equalsIgnoreCase(req.getLoadSeparatedYn())
						|| "Y".equalsIgnoreCase(req.getConnectedParallelYn()))
				
			{
				if(req.getReArrangementId() != 3)
				return "ERROR: If Selected LoadSeparatedYn And ConnectedParallelYn As Y then Re_Arragnment_ID Should be 3";
				
			}
			
			
			  // =========================
	        // VALIDATION
	        // =========================

	        if (req.getReArrangementId() == 3 &&
	                (voltageId == 1 || voltageId == 2)) {

	            StringBuilder msg =
	                    new StringBuilder();

	            msg.append(
	                    "ERROR: Invalid Voltage ID For Rearrangement Type 3. Allowed Voltages Are : ");

				/*
				 * String voltageMasterQuery = "SELECT VOLTAGE_ID_N, VOLTAGE_NAME_C " +
				 * "FROM NC_VOLTAGE_MASTER " + "WHERE VOLTAGE_ID_N NOT IN (1,2) " +
				 * "ORDER BY VOLTAGE_ID_N";
				 */

	            PreparedStatement psMaster =
	                    conn.prepareStatement(QueryConstants.GET_ALLOWED_VOLTAGES);

	            ResultSet rsMaster =
	                    psMaster.executeQuery();

	            while (rsMaster.next()) {

	                msg.append(
	                        rsMaster.getInt("VOLTAGE_ID_N"))
	                        .append(" - ")
	                        .append(
	                                rsMaster.getString("VOLTAGE_NAME_C"))
	                        .append(", ");
	            }

	            String finalMessage =
	                    msg.substring(0, msg.length() - 2);

	            throw new RuntimeException(finalMessage);
	        }
			
			
			
			
			
			
			
			
			
			
			
			
			// end Validate reArrangementId = 3 condition
			if (Math.ceil(req.getInverterCapacity())> Math.ceil(req.getExistingLoad())) {
				return "ERROR: Cannot proceed to Solar Application, Inverter Capacity Cannot Be Greater Then Existing Total Load \n"
						+ "Total RE Capacity Applied =" + (req.getExistReSancCapacity() + req.getNewReCapacity())
						+ "  \nExisting Total load =" + req.getExistingLoad() + "  \nInverter Capacity="
						+ req.getInverterCapacity();
			}

			if (!req.getConsumerCategoryExisting().equalsIgnoreCase(req.getConsumerCategoryLtHt())) {
				return "Error: Existing Category " + req.getConsumerCategoryExisting() + " And Provided Category "
						+ req.getConsumerCategoryLtHt() + "  are Different";
			}

			if (req.getSolarSchemeCode() == 4) {

				//String sql = "SELECT npc_reg_ref_code " + "FROM solar_npc_reg_cons_list " + "WHERE consumer_number = ?";

				List<String> result = jdbcTemplate.query(QueryConstants.GET_NPC_REG_REF_CODE, (rs1, rowNum) -> rs1.getString("npc_reg_ref_code"),
						req.getConsumerNumber());

				if (!result.isEmpty()) {

					String npcRegRefCode = result.get(0);
					System.out.println("PMSuryaGhar Registration Code.." + npcRegRefCode);

					req.setNpcRegRefCode(npcRegRefCode);

					try {

						// ===== CALL PMSURYAGHAR API =====

						RestTemplate restTemplate = new RestTemplate();

						String url = ApiConstants.PMS_CONSUMER_APP_DATA_URL;

						HttpHeaders headers = new HttpHeaders();
						headers.setContentType(MediaType.APPLICATION_JSON);

						// request body
						Map<String, Object> requestBody = new HashMap<>();

						requestBody.put("registrationReferenceCode", npcRegRefCode);

						HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

						ResponseEntity<PmsResponseDto> response = restTemplate.exchange(url, HttpMethod.POST, entity,
								PmsResponseDto.class);

						/*
						 * RESPONSE
						 */
						PmsResponseDto responseDto = response.getBody();

						System.out.println(responseDto);

						if (responseDto != null && responseDto.getPayload() != null) {

							System.out.println("Connected To PM SuryaGhar Portal");

							Payload payload = responseDto.getPayload();

							String pmsCapacity = payload.getCapacity();

							if (req.getNewReCapacity() > Double.parseDouble(pmsCapacity)) {

								return "Applied Solar Capacity Should not be Greater Than PM SuryaGhar Applied Capacity"
										+ "\n Applied Solar Capacity = " + req.getNewReCapacity()
										+ "\n PMSuryaGhar Capacity = " + pmsCapacity;
							}
						}

					} catch (Exception ex) {

						ex.printStackTrace();

						return MessageConstants.PM_SERVER_DOWN;
					}
				} else {

					return "No PM Suryaghar application found for consumer : " + req.getConsumerNumber();
				}
			}

			// Arrears Check

			// Arrears Check
			boolean isGovtConsumer = isGovtConsumer(req.getConsumerNumber());

			/*
			 * RULE 1: Only govt consumer can use ReArrangementId = 7
			 */
			if (req.getReArrangementId() == 7 && !isGovtConsumer) {

				return "ERROR: Given consumer is not a Government RTS consumer, "
						+ "so cannot select ReArrangementId = 7";
			}

			/*
			 * RULE 2: Govt consumer must use only ReArrangementId = 7
			 */
			if (isGovtConsumer && req.getReArrangementId() != 7) {

				return "ERROR: Government consumer must use ReArrangementId = 7 only";
			}

			// STEP 2: Only non-govt goes to arrears
			if (req.getSolarSchemeCode() != 4) {

				if (!isGovtConsumer) {

					ArrearsResponseDto arrearsResponse = arrearsService.checkArrears(req.getConsumerNumber(),
							req.getConsumerCategoryExisting(), req.getReArrangementId(), req.getSolarSchemeCode());

					if (!arrearsResponse.isStatus()) {
						return arrearsResponse.getMessage();
					}
				}
			}

			// Arrears Check end
			// ==================================================
			// STEP 4: SOLAR APPLICATION CREATION
			// ==================================================
			solarCs = conn.prepareCall(
					"{ ? = call PK_PAY_NC_CHARGES.set_information_from_solarpv(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }");

			solarCs.registerOutParameter(1, java.sql.Types.VARCHAR);

			solarCs.setString(2, req.getConsumerNumber());
			solarCs.setString(3, req.getMobile());
			solarCs.setString(4, req.getPhone());
			solarCs.setString(5, req.getEmail());
			solarCs.setString(6, req.getPlace());
			solarCs.setInt(7, req.getExistingSupplyVoltage());
			solarCs.setString(8, req.getReGeneratorString());
			solarCs.setInt(9, req.getReArrangementId());
			solarCs.setString(10, req.getLoadSeparatedYn());
			solarCs.setString(11, req.getConnectedParallelYn());
			solarCs.setDouble(12, req.getInverterCapacity());
			solarCs.setDouble(13, req.getExistReSancCapacity());
			// solarCs.setString(14, req.getCapacityExtensionFlagYn());
			solarCs.setString(14, "N");
			solarCs.setInt(15, req.getSolarSchemeCode());
			solarCs.setInt(16, req.getSolarReTypeId());
			/*
			 * solarCs.setDouble(17, req.getNewLoad()); 
			 * solarCs.setInt(18,			 * req.getNewLoadUnitId()); 
			 * solarCs.setDouble(19, req.getNewDemand());
			 * solarCs.setInt(20, req.getLoadChangeApplicationId());
			 */
			solarCs.setDouble(17, 0);
			solarCs.setInt(18, 0);
			solarCs.setDouble(19, 0);
			solarCs.setInt(20, 0);
			solarCs.setString(21,req.getCreatedBy());

			/*
			 * solarCs.setDouble(17, req.getNewLoad()); solarCs.setInt(18,
			 * req.getNewLoadUnitId()); solarCs.setDouble(19, req.getNewDemand());
			 * solarCs.setInt(20, req.getLoadChangeApplicationId());
			 */
			solarCs.execute();

			/*
			 * insertMNREDetailsNew( conn, req, applicationId, serviceTypeId,
			 * reArrangementId, payload );
			 */

			String result = solarCs.getString(1);

			System.out.println("Solar Result = " + result);
			/*
			 * smsService.sendApplicationCreationSms( req.getMobile(), result,
			 * req.getConsumerNumber() );
			 */
			
			
			String applicationId =
			        result.replaceAll("[^0-9]", "");
			
			/****** sms service*******************
			/*smsService.sendSolarApplicationSms(

			        req.getConsumerNumber(),

			        applicationId,

			       "9309986649" //req.getMobile() // TEST MOBILE NUMBER
			);*/
			

/***************sms service end***************/
			
			return "SUCCESS: " + result;

		} catch (Exception e) {

			e.printStackTrace();
			return "ERROR: " + e.getMessage();

		} finally {

			try {
				if (rs != null)
					rs.close();
				if (loadCs != null)
					loadCs.close();
				if (solarCs != null)
					solarCs.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// ======================================================
	// RESULTSET CONVERTER (UNCHANGED)
	// ======================================================
	private List<Map<String, Object>> resultSetToList(ResultSet rs) throws Exception {

		List<Map<String, Object>> list = new ArrayList<>();

		ResultSetMetaData metaData = rs.getMetaData();

		int columnCount = metaData.getColumnCount();

		while (rs.next()) {

			Map<String, Object> row = new HashMap<>();

			for (int i = 1; i <= columnCount; i++) {

				row.put(metaData.getColumnName(i), rs.getObject(i));
			}

			list.add(row);
		}

		return list;
	}

	// ======================================================
	// PRINT METHOD (UNCHANGED)
	// ======================================================
	public void printList(String title, List<Map<String, Object>> list) {

		System.out.println("========== " + title + " ==========");

		for (Map<String, Object> row : list) {

			for (Map.Entry<String, Object> entry : row.entrySet()) {

				System.out.println(entry.getKey() + " : " + entry.getValue());
			}

			System.out.println("--------------------------------");
		}
	}

	/********** createLoadChangeApplication **********************/

	/*
	 * public LoadChangeResponse createLoadChangeApplication( SolarRequestDto req,
	 * String schemeCodeId) {
	 * 
	 * boolean flag = true;
	 * 
	 * LoadChangeResponse response = new LoadChangeResponse();
	 * 
	 * try {
	 * 
	 * List<Map<String, Object>> dt = solarRepository.getConsDataForLoadChange(
	 * req.getConsumerNumber());
	 * 
	 * LoadChangeRequestDTO dto = new LoadChangeRequestDTO();
	 * 
	 * dto.setConsumerNo(req.getConsumerNumber());
	 * 
	 * dto.setIsLoadChangeAppliedWhileSolarYn("Y");
	 * 
	 * dto.setServiceTypeId("5");
	 * 
	 * dto.setCreatedBy("Solar");
	 * 
	 * dto.setApplicationDate( LocalDate.now()
	 * .format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
	 * 
	 * dto.setRevisedLoad( req.getNewLoad());
	 * 
	 * dto.setRevisedLoadUnitId("4");
	 * 
	 * dto.setApplicantEmail(req.getEmail());
	 * 
	 * dto.setApplicantMobile(req.getMobile());
	 * 
	 * dto.setSolarSchemeCodeId(schemeCodeId);
	 * 
	 * // dto.setTotalSanctionedSolarCapacity( //
	 * req.getTotalSanctionedSolarCapacity());
	 * 
	 * if (dt != null && !dt.isEmpty()) {
	 * 
	 * Map<String, Object> dr = dt.get(0);
	 * 
	 * dto.setApplicantFirstName( Objects.toString(dr.get("consumer_name"), ""));
	 * 
	 * dto.setApplicantPlotFlatSurveyHouseBldgName(
	 * Objects.toString(dr.get("METER_ADDRESS_L1"), ""));
	 * 
	 * dto.setApplicantVillage( Objects.toString(dr.get("METER_ADDRESS_L3"), ""));
	 * 
	 * dto.setApplicantSocietyName( Objects.toString(dr.get("METER_ADDRESS_L2"),
	 * ""));
	 * 
	 * dto.setRequestedLoad( req.getExistingLoad());
	 * 
	 * dto.setRequestedLoadUnitId("4");
	 * 
	 * dto.setApplicantPinCodeId( Objects.toString(dr.get("METER_PIN_CODE"), ""));
	 * 
	 * String village = Objects.toString(dr.get("METER_ADDRESS_L3"), "");
	 * 
	 * if (village != null && !village.isEmpty()) { dto.setPlace(village); } else {
	 * dto.setPlace( Objects.toString( dr.get("sub_division_name"), "")); } }
	 * 
	 * // actual external API call ServiceResponseDTO serviceResp =
	 * loadChangeWebService .createNewConnection(dto);
	 * 
	 * Long appId = serviceResp.getApplicationID();
	 * 
	 * if (appId == null || appId == 0 || "Y".equalsIgnoreCase(
	 * serviceResp.getIsApplicationAlreadyExistsYN())) {
	 * 
	 * flag = false;
	 * 
	 * if (serviceResp.getMessageText() != null &&
	 * !serviceResp.getMessageText().isEmpty()) {
	 * 
	 * response.setMessage( serviceResp.getMessageText());
	 * 
	 * } else {
	 * 
	 * response.setMessage( "Error Creating Load Change Application"); }
	 * 
	 * } else {
	 * 
	 * flag = true;
	 * 
	 * response.setMessage( "Application ID : " + appId +
	 * " for Load Change Request is received."); }
	 * 
	 * response.setFlag(flag);
	 * 
	 * response.setLoadChangeID(appId);
	 * 
	 * } catch (Exception ex) {
	 * 
	 * response.setFlag(false);
	 * 
	 * response.setMessage(ex.getMessage()); }
	 * 
	 * return response; }
	 * 
	 * 
	 *//*********** end createLoadChangeApploiactin ****************/

	/********** end insert mnre solar details table ************/

	public boolean isGovtConsumer(String consumerNo) {

		//String sql = "SELECT COUNT(1) " + "FROM rts_govt_consumer_billing_consumers " + "WHERE consumer_number = ?";

		Integer count = jdbcTemplate.queryForObject(QueryConstants.CHECK_GOVT_CONSUMER, Integer.class, consumerNo);

		return count != null && count > 0;
	}

	private void validateDatabaseConnection() {

		try (Connection con = dataSource.getConnection()) {

			if (!con.isValid(15)) {

				throw new RuntimeException(MessageConstants.DB_CONNECTION_FAILED);
			}

		} catch (Exception e) {

			throw new RuntimeException(MessageConstants.DB_CONNECTION_FAILED);
		}
	}
}