package com.fcamara.backendtestjava.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import com.fcamara.backendtestjava.models.Empresa;

@Repository
public interface EmpresaRepository extends CrudRepository <Empresa,String>  {
	public List<Empresa> findAll();
	
	public Empresa findByCodigo (long codigo);
}
