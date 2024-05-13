package com.beko.component_list.prodorders;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
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

    @Query(value = "{ 'lineId': ?0, 'components.famCode': { $in: ?1 } }", fields = "{ 'id': 1, 'lineId': 1, 'ordNum': 1, 'fgNum': 1, 'altBom': 1, 'indFgNum': 1, 'indFgDesc': 1, 'schedDate': 1, 'ordQty': 1, 'components': 1 }")
    /*@Query(value = "{ 'lineId': ?0, 'components.famCode': { $in: ?1 } }", fields = "{ 'id': 1, 'lineId': 1, 'ordNum': 1, 'fgNum': 1, 'altBom': 1, 'indFgNum': 1, 'indFgDesc': 1, 'schedDate': 1, 'ordQty': 1, 'components': { $elemMatch: { 'famCode': { $in: ?1 } } } }")*/
    List<OrderWithComponent> findOrderWithComponentsByLineIdAndMatNum(String lineId, List<String> famCodes);

}

