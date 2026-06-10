package com.example.birthday.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.birthday.model.power_food;

public interface power_foodRepository
extends JpaRepository<power_food, Integer> {
// idで検索するだけなのでメソッド追加不要
	
	@Query(value = "SELECT * FROM power_food ORDER BY RANDOM() LIMIT 3", nativeQuery = true)
    List<power_food> findRandom3();

}

