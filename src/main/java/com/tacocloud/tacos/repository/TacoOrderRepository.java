package com.tacocloud.tacos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tacocloud.tacos.domain.TacoOrder;

public interface TacoOrderRepository extends JpaRepository<TacoOrder, Long> {

}
