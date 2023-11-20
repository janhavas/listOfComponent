package com.whirlpool.component_list.prodorders;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

@Component
public class ProdOrderRowMapper implements RowMapper<ProdOrder> {
    @Override
    public ProdOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ProdOrder(
                rs.getString("LinSeq").substring(0,2),
                rs.getString("OrdNum"),
                rs.getString("FGNum"),
                rs.getString("AltBOM"),
                rs.getString("IndFGNum"),
                rs.getString("IndFGDesStd"),
                rs.getTimestamp("SchedDate"),
                rs.getInt("OrdQty")
        );
    }
}

