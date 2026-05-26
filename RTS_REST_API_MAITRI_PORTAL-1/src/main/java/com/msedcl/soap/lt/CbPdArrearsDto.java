
package com.msedcl.soap.lt;

import java.math.BigDecimal;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for cbPdArrearsDto complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>{@code
 * <complexType name="cbPdArrearsDto">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="activity_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="bill_amount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="bill_due_date" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="calculated_pd_interest" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         <element name="connected_load_kw" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         <element name="consumerNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="consumer_type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="contract_demand_kva" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         <element name="court_case_status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="decree_flag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="demand_violation_flag" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="max_recorded_demand" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="pd_td_date" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="plan_status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="sanctioned_demand_kva" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         <element name="sanctioned_load_kw" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         <element name="sdHeld" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="subsidy_start_date" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="subsidy_type_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="tariff_code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="textile_declaration_submitted" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="totalArrearsAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/>
 *         <element name="total_sd_arrears" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="uid_number" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="vd_plan_status" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cbPdArrearsDto", propOrder = {
    "activityCode",
    "billAmount",
    "billDueDate",
    "calculatedPdInterest",
    "connectedLoadKw",
    "consumerNumber",
    "consumerType",
    "contractDemandKva",
    "courtCaseStatus",
    "decreeFlag",
    "demandViolationFlag",
    "maxRecordedDemand",
    "pdTdDate",
    "planStatus",
    "sanctionedDemandKva",
    "sanctionedLoadKw",
    "sdHeld",
    "subsidyStartDate",
    "subsidyTypeCode",
    "tariffCode",
    "textileDeclarationSubmitted",
    "totalArrearsAmount",
    "totalSdArrears",
    "uidNumber",
    "vdPlanStatus"
})
public class CbPdArrearsDto {

    @XmlElement(name = "activity_code")
    protected String activityCode;
    @XmlElement(name = "bill_amount")
    protected String billAmount;
    @XmlElement(name = "bill_due_date")
    protected String billDueDate;
    @XmlElement(name = "calculated_pd_interest")
    protected BigDecimal calculatedPdInterest;
    @XmlElement(name = "connected_load_kw")
    protected BigDecimal connectedLoadKw;
    protected String consumerNumber;
    @XmlElement(name = "consumer_type")
    protected String consumerType;
    @XmlElement(name = "contract_demand_kva")
    protected BigDecimal contractDemandKva;
    @XmlElement(name = "court_case_status")
    protected String courtCaseStatus;
    @XmlElement(name = "decree_flag")
    protected String decreeFlag;
    @XmlElement(name = "demand_violation_flag")
    protected String demandViolationFlag;
    @XmlElement(name = "max_recorded_demand")
    protected String maxRecordedDemand;
    @XmlElement(name = "pd_td_date")
    protected String pdTdDate;
    @XmlElement(name = "plan_status")
    protected String planStatus;
    @XmlElement(name = "sanctioned_demand_kva")
    protected BigDecimal sanctionedDemandKva;
    @XmlElement(name = "sanctioned_load_kw")
    protected BigDecimal sanctionedLoadKw;
    protected String sdHeld;
    @XmlElement(name = "subsidy_start_date")
    protected String subsidyStartDate;
    @XmlElement(name = "subsidy_type_code")
    protected String subsidyTypeCode;
    @XmlElement(name = "tariff_code")
    protected String tariffCode;
    @XmlElement(name = "textile_declaration_submitted")
    protected String textileDeclarationSubmitted;
    protected BigDecimal totalArrearsAmount;
    @XmlElement(name = "total_sd_arrears")
    protected String totalSdArrears;
    @XmlElement(name = "uid_number")
    protected String uidNumber;
    @XmlElement(name = "vd_plan_status")
    protected String vdPlanStatus;

    /**
     * Gets the value of the activityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActivityCode() {
        return activityCode;
    }

    /**
     * Sets the value of the activityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActivityCode(String value) {
        this.activityCode = value;
    }

    /**
     * Gets the value of the billAmount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillAmount() {
        return billAmount;
    }

    /**
     * Sets the value of the billAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillAmount(String value) {
        this.billAmount = value;
    }

    /**
     * Gets the value of the billDueDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillDueDate() {
        return billDueDate;
    }

    /**
     * Sets the value of the billDueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillDueDate(String value) {
        this.billDueDate = value;
    }

    /**
     * Gets the value of the calculatedPdInterest property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCalculatedPdInterest() {
        return calculatedPdInterest;
    }

    /**
     * Sets the value of the calculatedPdInterest property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCalculatedPdInterest(BigDecimal value) {
        this.calculatedPdInterest = value;
    }

    /**
     * Gets the value of the connectedLoadKw property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getConnectedLoadKw() {
        return connectedLoadKw;
    }

    /**
     * Sets the value of the connectedLoadKw property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setConnectedLoadKw(BigDecimal value) {
        this.connectedLoadKw = value;
    }

    /**
     * Gets the value of the consumerNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsumerNumber() {
        return consumerNumber;
    }

    /**
     * Sets the value of the consumerNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsumerNumber(String value) {
        this.consumerNumber = value;
    }

    /**
     * Gets the value of the consumerType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsumerType() {
        return consumerType;
    }

    /**
     * Sets the value of the consumerType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsumerType(String value) {
        this.consumerType = value;
    }

    /**
     * Gets the value of the contractDemandKva property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getContractDemandKva() {
        return contractDemandKva;
    }

    /**
     * Sets the value of the contractDemandKva property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setContractDemandKva(BigDecimal value) {
        this.contractDemandKva = value;
    }

    /**
     * Gets the value of the courtCaseStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCourtCaseStatus() {
        return courtCaseStatus;
    }

    /**
     * Sets the value of the courtCaseStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCourtCaseStatus(String value) {
        this.courtCaseStatus = value;
    }

    /**
     * Gets the value of the decreeFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDecreeFlag() {
        return decreeFlag;
    }

    /**
     * Sets the value of the decreeFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDecreeFlag(String value) {
        this.decreeFlag = value;
    }

    /**
     * Gets the value of the demandViolationFlag property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDemandViolationFlag() {
        return demandViolationFlag;
    }

    /**
     * Sets the value of the demandViolationFlag property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDemandViolationFlag(String value) {
        this.demandViolationFlag = value;
    }

    /**
     * Gets the value of the maxRecordedDemand property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaxRecordedDemand() {
        return maxRecordedDemand;
    }

    /**
     * Sets the value of the maxRecordedDemand property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaxRecordedDemand(String value) {
        this.maxRecordedDemand = value;
    }

    /**
     * Gets the value of the pdTdDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPdTdDate() {
        return pdTdDate;
    }

    /**
     * Sets the value of the pdTdDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPdTdDate(String value) {
        this.pdTdDate = value;
    }

    /**
     * Gets the value of the planStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPlanStatus() {
        return planStatus;
    }

    /**
     * Sets the value of the planStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPlanStatus(String value) {
        this.planStatus = value;
    }

    /**
     * Gets the value of the sanctionedDemandKva property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSanctionedDemandKva() {
        return sanctionedDemandKva;
    }

    /**
     * Sets the value of the sanctionedDemandKva property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSanctionedDemandKva(BigDecimal value) {
        this.sanctionedDemandKva = value;
    }

    /**
     * Gets the value of the sanctionedLoadKw property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSanctionedLoadKw() {
        return sanctionedLoadKw;
    }

    /**
     * Sets the value of the sanctionedLoadKw property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSanctionedLoadKw(BigDecimal value) {
        this.sanctionedLoadKw = value;
    }

    /**
     * Gets the value of the sdHeld property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSdHeld() {
        return sdHeld;
    }

    /**
     * Sets the value of the sdHeld property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSdHeld(String value) {
        this.sdHeld = value;
    }

    /**
     * Gets the value of the subsidyStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubsidyStartDate() {
        return subsidyStartDate;
    }

    /**
     * Sets the value of the subsidyStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubsidyStartDate(String value) {
        this.subsidyStartDate = value;
    }

    /**
     * Gets the value of the subsidyTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubsidyTypeCode() {
        return subsidyTypeCode;
    }

    /**
     * Sets the value of the subsidyTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubsidyTypeCode(String value) {
        this.subsidyTypeCode = value;
    }

    /**
     * Gets the value of the tariffCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTariffCode() {
        return tariffCode;
    }

    /**
     * Sets the value of the tariffCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTariffCode(String value) {
        this.tariffCode = value;
    }

    /**
     * Gets the value of the textileDeclarationSubmitted property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextileDeclarationSubmitted() {
        return textileDeclarationSubmitted;
    }

    /**
     * Sets the value of the textileDeclarationSubmitted property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextileDeclarationSubmitted(String value) {
        this.textileDeclarationSubmitted = value;
    }

    /**
     * Gets the value of the totalArrearsAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalArrearsAmount() {
        return totalArrearsAmount;
    }

    /**
     * Sets the value of the totalArrearsAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalArrearsAmount(BigDecimal value) {
        this.totalArrearsAmount = value;
    }

    /**
     * Gets the value of the totalSdArrears property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalSdArrears() {
        return totalSdArrears;
    }

    /**
     * Sets the value of the totalSdArrears property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalSdArrears(String value) {
        this.totalSdArrears = value;
    }

    /**
     * Gets the value of the uidNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUidNumber() {
        return uidNumber;
    }

    /**
     * Sets the value of the uidNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUidNumber(String value) {
        this.uidNumber = value;
    }

    /**
     * Gets the value of the vdPlanStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVdPlanStatus() {
        return vdPlanStatus;
    }

    /**
     * Sets the value of the vdPlanStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVdPlanStatus(String value) {
        this.vdPlanStatus = value;
    }

}
