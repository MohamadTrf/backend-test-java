package com.fcamara.backendtestjava.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fcamara.backendtestjava.errors.ResourceNotFoundException;
import com.fcamara.backendtestjava.models.Veiculo;
import com.fcamara.backendtestjava.repository.VeiculoRepository;

@RestController
public class VeiculoController {

	@Autowired
	private VeiculoRepository vp;
	
	@RequestMapping(value="/cadastrarVeiculo", method = RequestMethod.POST)
	public ResponseEntity<Veiculo> cadastarEmpresa (@RequestBody Veiculo veiculo){
		if(vp.findByPlaca(veiculo.getPlaca()).size() > 0) {
			throw new   ResourceNotFoundException("Não pode ter dois carros com a mesma placa! "); 
		}
		return new ResponseEntity<>(vp.save(veiculo),HttpStatus.CREATED);
	}

}
