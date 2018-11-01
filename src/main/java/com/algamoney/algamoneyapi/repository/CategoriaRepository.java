package com.algamoney.algamoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.algamoney.algamoneyapi.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long>{}
