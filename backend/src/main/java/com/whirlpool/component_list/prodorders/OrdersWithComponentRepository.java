package com.whirlpool.component_list.prodorders;

import com.whirlpool.component_list.prodorders.OrderWithComponent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersWithComponentRepository extends MongoRepository<OrderWithComponent, String> {

    Optional<OrderWithComponent> findOrderWithComponentByOrdNum(String ordNum);
    void deleteOrderWithComponentByOrdNum(String ordNum);

    List<OrderWithComponent> findOrderWithComponentsBySchedDateIsGreaterThanAndLineId(LocalDateTime date, String lineId);

    List<OrderWithComponent> findOrderWithComponentsBySchedDateBetweenAndLineId(LocalDateTime from, LocalDateTime to, String lineId);

    void deleteOrderWithComponentsBySchedDateIsBefore(LocalDateTime date);
}
