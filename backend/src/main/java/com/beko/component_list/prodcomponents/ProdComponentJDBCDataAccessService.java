package com.beko.component_list.prodcomponents;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.util.List;

@Repository("jdbc_comp")
public class ProdComponentJDBCDataAccessService implements ProdComponentDao {
    @Value("${application.config.familyIdList}")
    private String famCodeString;
    @Value("${application.config.camCodeList}")
    private String camCodeList;

    private final JdbcTemplate jdbcTemplate;

    private final ProdComponetRowMapper prodComponetRowMapper;


    private static final String SP_GET_EXPBOM_BY_FAMILYCODE_BY_DATE = "{call CellAdpt.dbo.StoredGetExpBOM_FamilyCode(?, ?, ?)}";
    private static final String SP_GET_EXPBOM_BY_CAMCODE = "{call CellAdpt.dbo.StoredGetExpBOM_CAMCODE_BY_DATE(?, ?, ?)}";

    public ProdComponentJDBCDataAccessService(JdbcTemplate jdbcTemplate, ProdComponetRowMapper prodComponetRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.prodComponetRowMapper = prodComponetRowMapper;

    }

    @Override
    public List<ProdComponent> selectAllComponents(String fg, String altBom) {
/*        System.out.println("fg>" +fg);
        System.out.println("altBom>" +altBom);
        System.out.println("fam code>" + famCodeString);*/
        return jdbcTemplate.query(connection -> {
            CallableStatement callableStatement = connection.prepareCall(SP_GET_EXPBOM_BY_FAMILYCODE_BY_DATE);
            callableStatement.setString(1, fg);
            callableStatement.setString(2, altBom);
            callableStatement.setString(3, famCodeString);
            return callableStatement;
        }, prodComponetRowMapper);
    }

    @Override
    public List<ProdComponent> selectAllComponentsByCamCode(String fg, String altBom) {
        return jdbcTemplate.query(connection -> {
            CallableStatement callableStatement = connection.prepareCall(SP_GET_EXPBOM_BY_CAMCODE);
            callableStatement.setString(1, fg);
            callableStatement.setString(2, altBom);
            callableStatement.setString(3, camCodeList);
            return callableStatement;
        }, prodComponetRowMapper);
    }


}
