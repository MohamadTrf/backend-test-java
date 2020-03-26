package com.fcamara.backendtestjava.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;;


@Entity
@Table(name = "Empresa")
public class Empresa {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO) // gerando um codigo automaticante do tipo long
	private long codigo;
	
	private String nome;
	
	
	private String cnpj;
	
	private String endereco;
	
	private String telefone;
	
	private int quantidade_moto;
	
	private int quantidade_carro;
	
	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getQuantidade_moto() {
		return quantidade_moto;
	}

	public void setQuantidade_moto(int quantidade_moto) {
		this.quantidade_moto = quantidade_moto;
	}

	public int getQuantidade_carro() {
		return quantidade_carro;
	}

	public void setQuantidade_carro(int quantidade_carro) {
		this.quantidade_carro = quantidade_carro;
	}
	
	
}
