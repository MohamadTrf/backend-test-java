### Banco de Dados
Foi criado uma instância local do mysql para o banco de dados, tendo os seguinte dados de conexão
Usuário:root
senha:root
endereço:localhost:3306

Rodar os seguintes comandos no mysql
```mysql
create database TestFcamara;
use TestFcamara;
create table Empresa(
 codigo int  AUTO_INCREMENT PRIMARY KEY,
 nome varchar(60),
 cnpj varchar(14),
 endereco varchar(80),
 telefone varchar(11),
 quantitade_moto int,
 quantidade_carro int
);

create table Veiculo(
	codigo int AUTO_INCREMENT PRIMARY KEY,
    marca varchar(30),
    modelo varchar(15),
    cor varchar(10),
    placa varchar(15),
    tipo varchar(10)
);
```
A conexão se encontra no application.properties
