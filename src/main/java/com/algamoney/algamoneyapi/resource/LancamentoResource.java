package com.algamoney.algamoneyapi.resource;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.algamoney.algamoneyapi.event.RecursoCriadoEvent;
import com.algamoney.algamoneyapi.exceptionhandler.AlgamoneyExceptionHandler.Erro;
import com.algamoney.algamoneyapi.model.Lancamento;
import com.algamoney.algamoneyapi.repository.LancamentoRepository;
import com.algamoney.algamoneyapi.repository.filter.LancamentoFilter;
import com.algamoney.algamoneyapi.repository.projection.ResumoLancamento;
import com.algamoney.algamoneyapi.service.LancamentoService;
import com.algamoney.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

		@Autowired
		private LancamentoRepository lancamentoRepository;
		
		@Autowired
		private LancamentoService lancamentoService;
		
		@Autowired
		private MessageSource messageSource;
		
		@Autowired	
		private ApplicationEventPublisher publisher;
		
		@GetMapping
		@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oath2.hasScope('read')")
		public Page<Lancamento> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable){
				return lancamentoRepository.filtrar(lancamentoFilter, pageable);
		}
		
		@GetMapping(params="resumo")	
		@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oath2.hasScope('read')")
		public Page<ResumoLancamento> filtrarResumo(LancamentoFilter lancamentoFilter, Pageable pageable){
				return lancamentoRepository.filtrarResumo(lancamentoFilter, pageable);
		}
		
		
		@PostMapping
		@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oath2.hasScope('write')")
		public ResponseEntity<Lancamento> criar(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response) {
				Lancamento lancamentoSalvo = lancamentoService.salvar(lancamento);
			
				publisher.publishEvent(new RecursoCriadoEvent(this,response, lancamentoSalvo.getCodigo()));
				
				return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
		}
		
		@GetMapping("/{codigo}")
		@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oath2.hasScope('read')")
		public ResponseEntity<Lancamento> findById(@PathVariable Long codigo) {
				Lancamento lancamento = lancamentoRepository.findOne(codigo);		
				return lancamento != null ? ResponseEntity.ok(lancamento) : 	ResponseEntity.notFound().build();   
		}
	    		
		@DeleteMapping("/{codigo}")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		@PreAuthorize("hasAuthority('ROLE_REMOVER_LANCAMENTO') and #oath2.hasScope('write')")
		public void Remover(@PathVariable Long codigo) {
				lancamentoRepository.delete(codigo);
		}
		
		@PutMapping("/{codigo}")
		@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oath2.hasScope('write')")
		public ResponseEntity<Lancamento> atualizar(@PathVariable Long codigo, @Valid @RequestBody Lancamento lancamento) {
					Lancamento lancamentoSalva = lancamentoService.atualizar(codigo,lancamento);
					return ResponseEntity.ok(lancamentoSalva) ;   
		}
				
		@ExceptionHandler({PessoaInexistenteOuInativaException.class})
		public ResponseEntity<Object>HandlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex, WebRequest request) {
			
			String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
			String mensagemDesenvolvedor = ex.toString();
			List<Erro> erros = Arrays.asList (new Erro(mensagemUsuario, mensagemDesenvolvedor));
			
			return ResponseEntity.badRequest().body(erros);
	}
		
}
