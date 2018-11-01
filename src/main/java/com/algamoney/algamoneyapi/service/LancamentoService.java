package com.algamoney.algamoneyapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.algamoney.algamoneyapi.model.Lancamento;
import com.algamoney.algamoneyapi.model.Pessoa;
import com.algamoney.algamoneyapi.repository.LancamentoRepository;
import com.algamoney.algamoneyapi.repository.PessoaRepository;
import com.algamoney.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

		@Autowired
		private LancamentoRepository lancamentoRepository;
		
		@Autowired
		private PessoaRepository pessoaRepository;
		
		
		public Lancamento atualizar(Long codigo, Lancamento lancamento) {
				Lancamento lancamentoSalvo = buscarLancamentoPeloCodigo(codigo);
				BeanUtils.copyProperties(lancamento, lancamentoSalvo,"codigo");
				return lancamentoRepository.save(lancamentoSalvo);  
		}
		
		private Lancamento buscarLancamentoPeloCodigo(Long codigo) {
				Lancamento lancamentoSalva = lancamentoRepository.findOne(codigo);
				if(lancamentoSalva==null)
						throw new EmptyResultDataAccessException(1);
				return lancamentoSalva;
		}

		public Lancamento salvar(Lancamento lancamento) {
				Pessoa pessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
				if(pessoa==null || pessoa.isInativo())
					throw new PessoaInexistenteOuInativaException();
				return lancamentoRepository.save(lancamento);
		}
}
