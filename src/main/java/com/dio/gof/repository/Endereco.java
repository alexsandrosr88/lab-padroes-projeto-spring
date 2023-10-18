package com.dio.gof.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Endereco extends JpaRepository<Endereco, Long> {
}
