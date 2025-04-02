package com.springboot.rest_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.rest_api.model.WareHouse;

public interface WareHouseRepository extends JpaRepository<WareHouse, Integer>
{

}
