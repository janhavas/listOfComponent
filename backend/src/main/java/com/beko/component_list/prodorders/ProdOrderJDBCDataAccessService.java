package com.beko.component_list.prodorders;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Repository("jdbc_fas")
public class ProdOrderJDBCDataAccessService implements ProdOrderDao {

    private final String ORDER_NUMBER = "";
    private final String ORDER_STATUS = "I0002";
    private final String GLOBAL_PROD_PLANT = "";
    private final JdbcTemplate jdbcTemplate;

    private final ProdOrderRowMapper prodOrderRowMapper;

    private static final String SP_GET_ORDER_BY_CRITERIA = "{call CellAdpt.dbo.StoredGetOrdersByCriteria(?, ?, ?, ?, ?)}";

    public ProdOrderJDBCDataAccessService(JdbcTemplate jdbcTemplate, ProdOrderRowMapper prodOrderRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.prodOrderRowMapper = prodOrderRowMapper;
    }


    @Override
    public List<ProdOrder> selectAllProdOrders() {
        String startDate = LocalDate.now().toString();
        LocalDate nextDay = setMondayIfWeekend(LocalDate.now().plusDays(2));
        String endDate = nextDay.toString();

        return jdbcTemplate.query(connection -> {
            CallableStatement callableStatement = connection.prepareCall(SP_GET_ORDER_BY_CRITERIA);
            callableStatement.setString(1, ORDER_NUMBER);
            callableStatement.setString(2, ORDER_STATUS);
            callableStatement.setString(3, GLOBAL_PROD_PLANT);
            callableStatement.setString(4, startDate);
            callableStatement.setString(5, endDate);
            return callableStatement;
        },prodOrderRowMapper);
    }

    private LocalDate setMondayIfWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        switch (dayOfWeek) {
            case SATURDAY -> {
                return date.plusDays(3);
            }
            case SUNDAY -> {
                return date.plusDays(2);
            }
            default -> {
                return date;
            }
        }
    }
}
