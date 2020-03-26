package com.fcamara.backendtestjava.controllers;

import java.util.List;
import com.fcamara.backendtestjava.models.Empresa;
import com.fcamara.backendtestjava.repository.EmpresaRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BackEndController {
	
	@Autowired
	 private EmpresaRepository ep;
	
	@RequestMapping(value="/cadastrarEmpresa", method = RequestMethod.POST)
	public ResponseEntity<Empresa> cadastarEmpresa (@RequestBody Empresa empresa){
		System.out.print(empresa);
		return new ResponseEntity<>(ep.save(empresa),HttpStatus.CREATED);
	}
}
