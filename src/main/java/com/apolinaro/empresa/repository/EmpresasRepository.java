package com.apolinaro.empresa.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apolinaro.empresa.model.Empresa;

public interface EmpresasRepository extends JpaRepository<Empresa, Long>{

}
