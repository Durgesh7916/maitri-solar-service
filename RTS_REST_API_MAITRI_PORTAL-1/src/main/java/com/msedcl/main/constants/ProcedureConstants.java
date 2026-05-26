package com.msedcl.main.constants;

public class ProcedureConstants {

    private ProcedureConstants() {
    }

    public static final String GET_SOLAR_INFO =
            "{call pk_pay_nc_charges.getAppInfoSolarPv(?,?,?,?,?,?)}";

    public static final String CREATE_SOLAR_APPLICATION =
            "{ ? = call PK_PAY_NC_CHARGES.set_information_from_solarpv(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) }";
}