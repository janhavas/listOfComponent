package com.whirlpool.component_list.prodcellorders;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("jdbc_cell")
public class ProdCellOrderJDBCDtataAccessService implements ProdCellOrderDao {

    private final JdbcTemplate jdbcTemplate;
    private final ProdCellOrderRowMapper prodCellOrderRowMapper;

    public ProdCellOrderJDBCDtataAccessService(@Qualifier("cellJdbc") JdbcTemplate jdbcTemplate, ProdCellOrderRowMapper prodCellOrderRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.prodCellOrderRowMapper = prodCellOrderRowMapper;
    }

    @Override
    public List<ProdCellOrder> selectAllProdCellOrders() {
        var sql = """
                SELECT FAS
                FROM vCMN_PRODPLAN
                WHERE OrderStatus IN ('D', 'S')
                """;

        return jdbcTemplate.query(sql, prodCellOrderRowMapper);
    }
}
