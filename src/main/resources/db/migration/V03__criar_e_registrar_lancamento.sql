CREATE TABLE lancamento (
	codigo BIGINT(20)  PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(50) NOT NULL,
	data_vencimento DATE NOT NULL,
	data_pagamento DATE,
	valor DECIMAL(10,2) NOT NULL,
	observacao VARCHAR(100),
	tipo VARCHAR(20) NOT NULL,
	codigo_categoria BIGINT(20) NOT NULL,
	codigo_pessoa BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_categoria) REFERENCES categoria(codigo),
	FOREIGN KEY (codigo_pessoa) REFERENCES pessoa(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8;

INSERT INTO lancamento (descricao, data_vencimento, data_pagamento,valor, observacao,tipo,codigo_categoria, codigo_pessoa) values ('Salário Mensal','2018-08-01', null, 6500.00, 'Distribuição de lucros','RECEITA',1,1);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento,valor, observacao,tipo,codigo_categoria, codigo_pessoa) values ('Extras','2018-08-17', null, 80.00, 'Diversos papelaria','DESPESA',2,3);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento,valor, observacao,tipo,codigo_categoria, codigo_pessoa) values ('Manutenção carro','2018-09-01', '2018-09-01', 500.00, 'Revisão anual','DESPESA',3,2);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento,valor, observacao,tipo,codigo_categoria, codigo_pessoa) values ('Computador','2018-08-21', '2018-09-21', 3500.00, 'Laptop estagiário','DESPESA',4,5);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento,valor, observacao,tipo,codigo_categoria, codigo_pessoa) values ('Café','2018-07-01', '2018-07-01', 100.00, 'Compra material café','DESPESA',5,7);
INSERT INTO lancamento (descricao, data_vencimento, data_pagamento,valor, observacao,tipo,codigo_categoria, codigo_pessoa) values ('Vendas','2018-07-01', null, 16500.00, 'Totalização vendas mês','RECEITA',2,6);