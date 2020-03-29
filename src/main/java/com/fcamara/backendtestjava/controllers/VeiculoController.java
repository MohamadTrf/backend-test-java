package com.fcamara.backendtestjava.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
	public ResponseEntity<Veiculo> cadastrarVeiculo (@RequestBody Veiculo veiculo){
		if(vp.findByPlaca(veiculo.getPlaca()).size() > 0) {
			throw new   ResourceNotFoundException("Não pode ter dois carros com a mesma placa! "); 
		}
		return new ResponseEntity<>(vp.save(veiculo),HttpStatus.CREATED);
	}
	
	@RequestMapping(value="deletarVeiculo/{codigo}", method = RequestMethod.DELETE)
	public void deletarVeiculo (@PathVariable long codigo) {
		Veiculo veiculo = vp.findByCodigo(codigo);
		vp.delete(veiculo);
	}
	
	@RequestMapping(value="buscarVeiculo/{codigo}", method = RequestMethod.GET)
	public ResponseEntity<Veiculo> buscarVeiculo (@PathVariable ("codigo") long codigo){
		Veiculo veiculo = vp.findByCodigo(codigo);
		if(veiculo == null) {
			throw new   ResourceNotFoundException("busca inválida! ");
		}
		return new ResponseEntity<>(veiculo, HttpStatus.OK);
	}
	
	@RequestMapping(value="buscarVeiculos", method = RequestMethod.GET)
	public List<Veiculo> buscarTodosVeiculos () {
		return vp.findAll();
	}
	
	@RequestMapping(value="alterarVeiculo/{codigo}", method = RequestMethod.PUT)
	public ResponseEntity<Veiculo> alterarVeiculo (@RequestBody Veiculo novoVeiculo , @PathVariable long codigo){
		Veiculo veiculo = vp.findByCodigo(codigo);
		
		if(veiculo == null) {
			throw new ResourceNotFoundException("Alteração Inválida");
		}
		
		veiculo.setMarca(novoVeiculo.getMarca());
		veiculo.setModelo(novoVeiculo.getModelo());
		veiculo.setCor(novoVeiculo.getCor());
		veiculo.setPlaca(novoVeiculo.getPlaca());
		veiculo.setTipo(novoVeiculo.getTipo());
		
		return new ResponseEntity<>(vp.save(veiculo),HttpStatus.OK);
	}

}
