package com.msedcl.main.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SolarRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> getConsDataForLoadChange(
            String consumerNumber) {

        String sql = """
                SELECT consumer_name,
                       METER_ADDRESS_L1,
                       METER_ADDRESS_L2,
                       METER_ADDRESS_L3,
                       METER_PIN_CODE,a.bu,
                       s_name sub_division_name
                FROM mbc_consumer_master a,bult b,
                WHERE a.bu=b.bu and consumer_no = ?
                """;

        return jdbcTemplate.queryForList(
                sql,
                consumerNumber);
    }
}