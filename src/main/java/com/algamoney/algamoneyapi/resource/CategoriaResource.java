package com.algamoney.algamoneyapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algamoney.algamoneyapi.event.RecursoCriadoEvent;
import com.algamoney.algamoneyapi.model.Categoria;
import com.algamoney.algamoneyapi.repository.CategoriaRepository;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {

		@Autowired
		private CategoriaRepository categoriaRepository;
		
		@Autowired
		private ApplicationEventPublisher publisher;
		
		@GetMapping
		@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
		public List<Categoria> listar(){
				return categoriaRepository.findAll();
		}
		
		@PostMapping
		@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA') and #oauth2.hasScope('write')")
		public ResponseEntity<Categoria> criar(@Valid @RequestBody Categoria categoria, HttpServletResponse response) {
				Categoria categoriaSalva = categoriaRepository.save(categoria);
				
				publisher.publishEvent(new RecursoCriadoEvent(this,response, categoriaSalva.getCodigo()));
				
				return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
		}
		
		@GetMapping("/{codigo}")
		@PreAuthorize("hasAuthority('ROLE_PESQUISAR_CATEGORIA') and #oauth2.hasScope('read')")
		public ResponseEntity<Categoria> findById(@PathVariable Long codigo) {
				Categoria categoria = categoriaRepository.findOne(codigo);		
				return categoria != null ? ResponseEntity.ok(categoria) : 	ResponseEntity.notFound().build();   
		}
	    
		/*
	    @GetMapping("/{codigo}")
		public Optional<Categoria> buscarPeloCodigo(@PathVariable Long codigo) {
				return categoriaRepository.findById(codigo);
		}*/
		
		/*
		@GetMapping("/{codigo}")
		public ResponseEntity<Categoria> findById(@PathVariable Long codigo) {
				Categoria categoria = categoriaRepository.findById(codigo).orElse(null);		
				return categoria != null ? ResponseEntity.ok(categoria) : 	ResponseEntity.notFound().build();   
		}
	    */
		
		@DeleteMapping("/{codigo}")
		@PreAuthorize("hasAuthority('ROLE_CADASTRAR_CATEGORIA') and #oauth2.hasScope('write')")
		@ResponseStatus(HttpStatus.NO_CONTENT)
		public void Remover(@PathVariable Long codigo) {
				categoriaRepository.delete(codigo);
		}
}
