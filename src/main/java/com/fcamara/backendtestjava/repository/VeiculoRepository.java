package com.fcamara.backendtestjava.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.fcamara.backendtestjava.models.Veiculo;

@Repository
public interface VeiculoRepository extends CrudRepository<Veiculo,String> {
	
	public Veiculo findByCodigo(long codigo);
}
