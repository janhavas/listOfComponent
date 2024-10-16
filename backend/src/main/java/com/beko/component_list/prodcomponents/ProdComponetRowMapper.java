package com.beko.component_list.prodcomponents;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProdComponetRowMapper implements RowMapper<ProdComponent> {
    @Override
    public ProdComponent mapRow(ResultSet rs, int rowNum) throws SQLException {
        String rawStartDate = rs.getString("StartDate").trim();
        String rawEndDate = rs.getString("EndDate").trim();
        String startDate;
        String endDate;
        int lenghtOfStartDate = rawStartDate.length();

        if(lenghtOfStartDate == 8){
            startDate = rawStartDate.substring(0,4)+"-"+rawStartDate.substring(4,6)+"-"+rawStartDate.substring(6,8);
            endDate = rawEndDate.substring(0,4)+"-"+rawEndDate.substring(4,6)+"-"+rawEndDate.substring(6,8);
        }else {
            startDate = rawStartDate.substring(0, 10);
            endDate = rawEndDate.substring(0, 10);
        }
        String rawMatNum = rs.getString("MatNum");

        String matNum = rawMatNum.substring(0,4) + "-" + rawMatNum.substring(4,9) + "-" + rawMatNum.substring(9);
        return new ProdComponent(
                matNum,
                rs.getString("MatDes"),
                rs.getString("FamilyID"),
                rs.getString("CamCode"),
                startDate,
                endDate

        );
    }
}

