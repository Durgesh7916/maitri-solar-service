package com.msedcl.main.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SolarRequestDto {

	@NotBlank(message = "Consumer Number is required")
    @Size(min = 12, max = 12,
            message = "Consumer Number must be 12 digits")
    private String consumerNumber;

    @NotBlank(message = "Mobile number is required")
    @Pattern(regexp = "^[0-9]{10}$",
            message = "Mobile number must be 10 digits")
    private String mobile;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$",
            message = "Phone number must be 10 digits")
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Place is required")
    private String place;

    @NotNull(message = "Existing Supply Voltage is required")
    private Integer existingSupplyVoltage;

    @NotBlank(message = "RE Generator String is required")
    private String reGeneratorString;

    @NotNull(message = "Re Arrangement Id is required")
    private Integer reArrangementId;

    @Pattern(regexp = "^[YN]$",
            message = "Load Separated must be Y or N")
    private String loadSeparatedYn;

    @Pattern(regexp = "^[YN]$",
            message = "Connected Parallel must be Y or N")
    private String connectedParallelYn;

    @DecimalMin(value = "0.0",
            message = "Inverter Capacity cannot be negative")
    @Digits(integer = 5, fraction = 2)
    private Double inverterCapacity;

    @DecimalMin(value = "0.0",
            message = "Existing RE Capacity cannot be negative")
    @Digits(integer = 5, fraction = 2)
    private Double existReSancCapacity;

    @Pattern(regexp = "^[YN]$",
            message = "Capacity Extension Flag must be Y or N")
    private String capacityExtensionFlagYn;

    @NotNull(message = "Solar Scheme Code required")
    private Integer solarSchemeCode;

    @NotNull(message = "Solar RE Type Id required")
    private Integer solarReTypeId;

    @DecimalMin(value = "0.0",
            message = "New Load cannot be negative")
    @Digits(integer = 5, fraction = 2)
    private Double newLoad;

    private Integer newLoadUnitId;

    @DecimalMin(value = "0.0",
            message = "New Demand cannot be negative")
    @Digits(integer = 5, fraction = 2)
    private Double newDemand;

    private Integer loadChangeApplicationId;

    @DecimalMin(value = "0.0",
            message = "Existing Load cannot be negative")
    @Digits(integer = 5, fraction = 2)
    private Double existingLoad;

    @DecimalMin(value = "0.0",
            message = "New RE Capacity cannot be negative")
    @Digits(integer = 5, fraction = 2)
    private Double newReCapacity;
    
    private String createdBy = "MAITRI";

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    private String npcRegRefCode;
     
    /*
     * EXISTING CONSUMER CATEGORY
     * Example : 1,2,3
     */
   
    private String consumerCategoryExisting;

    public String getConsumerCategoryExisting() {
        return consumerCategoryExisting;
    }

    public void setConsumerCategoryExisting(
            String consumerCategoryExisting) {

        this.consumerCategoryExisting =
                consumerCategoryExisting;
    }
    
    /*
     * LT / HT CATEGORY
     */
    @NotBlank(message = "Consumer_Category_LT/HT/LTIP is required")
    @Pattern(
            regexp = "LT|HT|LTIP",
            message = "Consumer_Category_LT_HT must be LT or LTIP or HT"
    )
    private String consumerCategoryLtHt;

    public String getConsumerCategoryLtHt() {
        return consumerCategoryLtHt;
    }

    public void setConsumerCategoryLtHt(
            String consumerCategoryLtHt) {

        this.consumerCategoryLtHt =
                consumerCategoryLtHt;
    }
    
    
    public String getNpcRegRefCode() {
		return npcRegRefCode;
	}


	public void setNpcRegRefCode(String npcRegRefCode) {
		this.npcRegRefCode = npcRegRefCode;
	}


	public Double getNewReCapacity() {
		return newReCapacity;
	}


	public void setNewReCapacity(Double newReCapacity) {
		this.newReCapacity = newReCapacity;
	}


	public SolarRequestDto() {
		// TODO Auto-generated constructor stub
	}


	public String getConsumerNumber() {
		return consumerNumber;
	}


	public void setConsumerNumber(String consumerNumber) {
		this.consumerNumber = consumerNumber;
	}


	public String getMobile() {
		return mobile;
	}


	public void setMobile(String mobile) {
		this.mobile = mobile;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPlace() {
		return place;
	}


	public void setPlace(String place) {
		this.place = place;
	}


	public Integer getExistingSupplyVoltage() {
		return existingSupplyVoltage;
	}


	public void setExistingSupplyVoltage(Integer existingSupplyVoltage) {
		this.existingSupplyVoltage = existingSupplyVoltage;
	}


	public String getReGeneratorString() {
		return reGeneratorString;
	}


	public void setReGeneratorString(String reGeneratorString) {
		this.reGeneratorString = reGeneratorString;
	}


	public Integer getReArrangementId() {
		return reArrangementId;
	}


	public void setReArrangementId(Integer reArrangementId) {
		this.reArrangementId = reArrangementId;
	}


	public String getLoadSeparatedYn() {
		return loadSeparatedYn;
	}


	public void setLoadSeparatedYn(String loadSeparatedYn) {
		this.loadSeparatedYn = loadSeparatedYn;
	}


	public String getConnectedParallelYn() {
		return connectedParallelYn;
	}


	public void setConnectedParallelYn(String connectedParallelYn) {
		this.connectedParallelYn = connectedParallelYn;
	}


	public Double getInverterCapacity() {
		return inverterCapacity;
	}


	public void setInverterCapacity(Double inverterCapacity) {
		this.inverterCapacity = inverterCapacity;
	}


	public Double getExistReSancCapacity() {
		return existReSancCapacity;
	}


	public void setExistReSancCapacity(Double existReSancCapacity) {
		this.existReSancCapacity = existReSancCapacity;
	}


	public String getCapacityExtensionFlagYn() {
		return capacityExtensionFlagYn;
	}


	public void setCapacityExtensionFlagYn(String capacityExtensionFlagYn) {
		this.capacityExtensionFlagYn = capacityExtensionFlagYn;
	}


	public Integer getSolarSchemeCode() {
		return solarSchemeCode;
	}


	public void setSolarSchemeCode(Integer solarSchemeCode) {
		this.solarSchemeCode = solarSchemeCode;
	}


	public Integer getSolarReTypeId() {
		return solarReTypeId;
	}


	public void setSolarReTypeId(Integer solarReTypeId) {
		this.solarReTypeId = solarReTypeId;
	}


	public Double getNewLoad() {
		return newLoad;
	}


	public void setNewLoad(Double newLoad) {
		this.newLoad = newLoad;
	}


	public Integer getNewLoadUnitId() {
		return newLoadUnitId;
	}


	public void setNewLoadUnitId(Integer newLoadUnitId) {
		this.newLoadUnitId = newLoadUnitId;
	}


	public Double getNewDemand() {
		return newDemand;
	}


	public void setNewDemand(Double newDemand) {
		this.newDemand = newDemand;
	}


	public Integer getLoadChangeApplicationId() {
		return loadChangeApplicationId;
	}


	public void setLoadChangeApplicationId(Integer loadChangeApplicationId) {
		this.loadChangeApplicationId = loadChangeApplicationId;
	}


	public Double getExistingLoad() {
		return existingLoad;
	}


	public void setExistingLoad(Double existingLoad) {
		this.existingLoad = existingLoad;
	}


	@Override
	public String toString() {
		return "SolarRequestDto [consumerNumber=" + consumerNumber + ", mobile=" + mobile + ", phone=" + phone
				+ ", email=" + email + ", place=" + place + ", existingSupplyVoltage=" + existingSupplyVoltage
				+ ", reGeneratorString=" + reGeneratorString + ", reArrangementId=" + reArrangementId
				+ ", loadSeparatedYn=" + loadSeparatedYn + ", connectedParallelYn=" + connectedParallelYn
				+ ", inverterCapacity=" + inverterCapacity + ", existReSancCapacity=" + existReSancCapacity
				+ ", capacityExtensionFlagYn=" + capacityExtensionFlagYn + ", solarSchemeCode=" + solarSchemeCode
				+ ", solarReTypeId=" + solarReTypeId + ", newLoad=" + newLoad + ", newLoadUnitId=" + newLoadUnitId
				+ ", newDemand=" + newDemand + ", loadChangeApplicationId=" + loadChangeApplicationId
				+ ", existingLoad=" + existingLoad + "]";
	}


	public SolarRequestDto(String consumerNumber, String mobile, String phone, String email, String place,
			Integer existingSupplyVoltage, String reGeneratorString, Integer reArrangementId, String loadSeparatedYn,
			String connectedParallelYn, Double inverterCapacity, Double existReSancCapacity,
			String capacityExtensionFlagYn, Integer solarSchemeCode, Integer solarReTypeId, Double newLoad,
			Integer newLoadUnitId, Double newDemand, Integer loadChangeApplicationId, Double existingLoad) {
		super();
		this.consumerNumber = consumerNumber;
		this.mobile = mobile;
		this.phone = phone;
		this.email = email;
		this.place = place;
		this.existingSupplyVoltage = existingSupplyVoltage;
		this.reGeneratorString = reGeneratorString;
		this.reArrangementId = reArrangementId;
		this.loadSeparatedYn = loadSeparatedYn;
		this.connectedParallelYn = connectedParallelYn;
		this.inverterCapacity = inverterCapacity;
		this.existReSancCapacity = existReSancCapacity;
		this.capacityExtensionFlagYn = capacityExtensionFlagYn;
		this.solarSchemeCode = solarSchemeCode;
		this.solarReTypeId = solarReTypeId;
		this.newLoad = newLoad;
		this.newLoadUnitId = newLoadUnitId;
		this.newDemand = newDemand;
		this.loadChangeApplicationId = loadChangeApplicationId;
		this.existingLoad = existingLoad;
	}
    
}
