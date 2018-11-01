CREATE TABLE pessoa (
	codigo BIGINT(20)  PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(50) NOT NULL,
	ativo BOOLEAN NOT NULL,
	logradouro VARCHAR(50),
	numero VARCHAR(10),
	complemento VARCHAR(30),
	bairro VARCHAR(20),
	cep VARCHAR(9),
	cidade VARCHAR(50),
	estado VARCHAR(2)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Igor Fernandes', TRUE, 'Rua General Osório','174','apto 202','Centro','28625-630','Nova Friburgo','RJ');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('João da Silva', TRUE, 'Av Alberto Braune','25','apto 902','Centro','28600-630','Nova Friburgo','RJ');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Diogo Martins', TRUE, 'Rua General Osório','174','apto 202','Centro','28625-630','Sõo Paulo','SP');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Branca Maravilha', TRUE, 'Rua General Osório','174','apto 202','Centro','28625-630','Goiânia','GO');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Raul Fenômeno', TRUE, 'Rua General Osório','174','apto 202','Centro','28625-630','Florianópolis','SC');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Jair Messias Bolsonaro', TRUE, 'Av das Américas','6790','casa 17','Barra','21234-630','Rio de Janeiro','RJ');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Jorge Doria', FALSE, 'Rua Francisco Bicalho','14','Casa','Itaimbibi','45625-630','São Paulo','SP');
INSERT INTO pessoa (nome,ativo,logradouro,numero,complemento,bairro,cep,cidade,estado) values ('Francisco Mendes', FALSE, 'Rua Diagonal','17','apto 201','Centro','98625-630','Manaus','AM');
