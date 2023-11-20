package com.whirlpool.component_list.prodcellorders;

import com.whirlpool.component_list.prodcomponents.ProdComponent;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProdCellOrderRowMapper implements RowMapper<ProdCellOrder> {
    @Override
    public ProdCellOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new ProdCellOrder(
          rs.getString("FAS")
        );
    }
}
