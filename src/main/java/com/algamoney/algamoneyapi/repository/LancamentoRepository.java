package com.algamoney.algamoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algamoney.algamoneyapi.model.Lancamento;
import com.algamoney.algamoneyapi.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {
		
		
	
}
