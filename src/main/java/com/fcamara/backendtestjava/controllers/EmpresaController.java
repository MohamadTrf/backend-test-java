package com.fcamara.backendtestjava.controllers;

import java.util.List;

import javax.transaction.Transactional;

import com.fcamara.backendtestjava.errors.ResourceNotFoundException;
import com.fcamara.backendtestjava.models.Empresa;
import com.fcamara.backendtestjava.models.Estacionamento;
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
public class EmpresaController {
	
	@Autowired
	 private EmpresaRepository ep;
	
	@RequestMapping(value="/cadastrarEmpresa", method = RequestMethod.POST)
	public ResponseEntity<Empresa> cadastrarEmpresa (@RequestBody Empresa empresa){

		//regra para impedir que dois iguais cnpjs esteja no banco
		if(ep.findByCnpj(empresa.getCnpj()).size() > 0) {
			throw new   ResourceNotFoundException("Não pode ter dois Cnpjs iguais ! "); 
		}
		return new ResponseEntity<>(ep.save(empresa),HttpStatus.CREATED);
	}
	
	@RequestMapping(value="buscarEmpresa/{codigo}", method = RequestMethod.GET)
	public ResponseEntity<Empresa> buscarEmpresa (@PathVariable ("codigo") long codigo){
		Empresa empresa = ep.findByCodigo(codigo);
		return new ResponseEntity<>(empresa,HttpStatus.OK);
	}
	
	@RequestMapping(value="/buscarEmpresas", method = RequestMethod.GET)
	public List<Empresa> buscarEmpresas () { return ep.findAll(); }
	
	@RequestMapping(value="deletarEmpresa/{codigo}", method = RequestMethod.DELETE)
	public void deletarEmpresa (@PathVariable ("codigo") long codigo) {
		Empresa empresa = ep.findByCodigo(codigo);
		ep.delete(empresa);
	}
	
	@RequestMapping(value="entradaSaidaEmpresa/{codigo}" , method = RequestMethod.PUT)
	public ResponseEntity<Empresa> entradaSaidaEmpresa(@RequestBody Estacionamento estacionamento , @PathVariable("codigo") long codigo) {
		Empresa empresa = ep.findByCodigo(codigo);
		if(empresa == null) {
			throw new   ResourceNotFoundException("Empresa não existe	! ");
		}
		System.out.println(estacionamento.getTipoAcao());
		System.out.println(estacionamento.getTipoVeiculo());
		if(estacionamento.getTipoAcao().equals("entrada")) {
			if(estacionamento.getTipoVeiculo().equals("moto") ) {
				System.out.println("moto positivo");
				empresa.setQuantidade_moto(empresa.getQuantidade_moto()+1);
			}else {
				empresa.setQuantidade_carro(empresa.getQuantidade_carro()+1);
			}
		} else {
			if(estacionamento.getTipoVeiculo().equals("moto") ) {
				empresa.setQuantidade_moto(empresa.getQuantidade_moto()-1);
			}else {
				empresa.setQuantidade_carro(empresa.getQuantidade_carro()-1);
			}
		}		
		return new ResponseEntity<>(ep.save(empresa),HttpStatus.OK);
	}
	
	@RequestMapping(value ="alterarEmpresa/{codigo}" , method = RequestMethod.PUT)
	@Transactional
	public ResponseEntity<Empresa> alterarEmpresa (@RequestBody Empresa novaEmpresa
			,@PathVariable long codigo){
		Empresa empresa = ep.findByCodigo(codigo);

		if(empresa == null) {
			throw new   ResourceNotFoundException("Empresa Inexistente! "); 
		}
		if(ep.findByCnpj(novaEmpresa.getCnpj()).size() > 0) {
			throw new   ResourceNotFoundException("Não pode ter dois Cnpjs iguais ! "); 
		}

		empresa.setNome(novaEmpresa.getNome());
		empresa.setCnpj(novaEmpresa.getCnpj());
		empresa.setEndereco(novaEmpresa.getEndereco());
		empresa.setQuantidade_carro(novaEmpresa.getQuantidade_carro());
		empresa.setQuantidade_moto(novaEmpresa.getQuantidade_moto());
		empresa.setTelefone(novaEmpresa.getTelefone());
		return new ResponseEntity<>(ep.save(empresa),HttpStatus.OK);
	}
}
