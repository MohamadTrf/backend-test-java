package com.fcamara.backendtestjava.controllers;

import java.util.List;
import com.fcamara.backendtestjava.models.Empresa;
import com.fcamara.backendtestjava.repository.EmpresaRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
		return new ResponseEntity<>(ep.save(empresa),HttpStatus.CREATED);
	}
	
	@RequestMapping(value="buscarEmpresa/{codigo}", method = RequestMethod.GET)
	public ResponseEntity<Empresa> buscarEmpresa (@PathVariable ("codigo") long codigo){
		Empresa empresa = ep.findByCodigo(codigo);
		return new ResponseEntity<>(empresa,HttpStatus.OK);
	}
	
	@RequestMapping(value="deletarEmpresa/{codigo}", method = RequestMethod.DELETE)
	public void deletarEmpresa (@PathVariable ("codigo") long codigo) {
		Empresa empresa = ep.findByCodigo(codigo);
		ep.delete(empresa);
	}
}
