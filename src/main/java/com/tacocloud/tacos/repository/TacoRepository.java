package com.tacocloud.tacos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tacocloud.tacos.domain.Taco;

public interface TacoRepository extends JpaRepository<Taco, Long> {

}
