package com.example.restservice.rest.repository;

import com.example.restservice.rest.entity.ResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultRepository extends JpaRepository<ResultEntity, Long> {
}
