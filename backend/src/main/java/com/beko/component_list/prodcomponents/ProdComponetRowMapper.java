package com.beko.component_list.prodcomponents;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProdComponetRowMapper implements RowMapper<ProdComponent> {
    @Override
    public ProdComponent mapRow(ResultSet rs, int rowNum) throws SQLException {
        String rawStartDate = rs.getString("StartDate");
        String rawEndDate = rs.getString("EndDate");

        String startDate = rawStartDate.substring(0, 10);
        String endDate = rawEndDate.substring(0, 10);

/*        String startDate = rawStartDate.substring(0, 4) + "-" + rawStartDate.substring(4, 6) + "-" + rawStartDate.substring(6, 8);
        String endDate = rawEndDate.substring(0, 4) + "-" + rawEndDate.substring(4, 6) + "-" + rawEndDate.substring(6, 8);*/

        String rawMatNum = rs.getString("MatNum");

        String matNum = rawMatNum.substring(0,4) + "-" + rawMatNum.substring(4,9) + "-" + rawMatNum.substring(9);
        return new ProdComponent(
                matNum,
                rs.getString("MatDes"),
                rs.getString("FamilyID"),
                startDate,
                endDate

        );
    }
}

