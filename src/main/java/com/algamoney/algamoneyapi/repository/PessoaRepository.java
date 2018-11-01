package com.algamoney.algamoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.algamoney.algamoneyapi.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>{}
