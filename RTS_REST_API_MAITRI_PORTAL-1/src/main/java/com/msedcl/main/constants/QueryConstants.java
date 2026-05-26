package com.msedcl.main.constants;

public class QueryConstants {

    private QueryConstants() {
    }

    public static final String VALIDATE_SOLAR_SCHEME = """
        SELECT COUNT(1)
        FROM solar_scheme
        WHERE Status_cd_c='A'
        AND solar_scheme_code = ?
        """;

    public static final String VALIDATE_RE_TYPE = """
        SELECT COUNT(1)
        FROM nc_renew_energy_generator_type
        WHERE Status_cd_c='A'
        AND re_generator_type_id_n = ?
        """;

    public static final String VALIDATE_REARRANGEMENT = """
        SELECT COUNT(1)
        FROM re_arrangements_master
        WHERE  Status_cd_c='A' and arrangement_id_n = ?
        """;

    public static final String VALIDATE_VOLTAGE = """
        SELECT COUNT(1)
        FROM nc_voltage_master
        WHERE Status_cd_c='A' and voltage_id_n = ?
        """;
    
    public static final String CHECK_GOVT_CONSUMER = """
            SELECT COUNT(1)
            FROM rts_govt_consumer_billing_consumers
            WHERE consumer_number = ?
            """;
    
    public static final String GET_NPC_REG_REF_CODE = """
    	    SELECT npc_reg_ref_code
    	    FROM solar_npc_reg_cons_list
    	    WHERE consumer_number = ?
    	    """;
    
    
    public static final String GET_ALLOWED_VOLTAGES = """
    	    SELECT VOLTAGE_ID_N, VOLTAGE_NAME_C
    	    FROM NC_VOLTAGE_MASTER
    	    WHERE Status_cd_c='A' and VOLTAGE_ID_N NOT IN (1,2)
    	    ORDER BY VOLTAGE_ID_N
    	    """;
}