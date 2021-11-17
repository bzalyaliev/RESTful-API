package com.example.socks.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BatchRepository extends CrudRepository <BatchEntity, Long> {

    List<BatchEntity> findAllByColorAndCottonPart(String color, Integer cottonPart);

    @Query("select b from BatchEntity b where (:color is null or b.color = :color) and (:cottonPart is null or b.cottonPart > :cottonPart)")
    List<BatchEntity> findBatchEntitiesWithColorPartMoreThan(String color, Integer cottonPart);

    @Query("select b from BatchEntity b where (:color is null or b.color = :color) and (:cottonPart is null or b.cottonPart < :cottonPart)")
    List<BatchEntity> findBatchEntitiesWithColorPartLessThan(String color, Integer cottonPart);

    @Query("select b from BatchEntity b where (:color is null or b.color = :color) and (:cottonPart is null or b.cottonPart = :cottonPart)")
    List<BatchEntity> findBatchEntitiesWithColorPartEqual(String color, Integer cottonPart);
}
