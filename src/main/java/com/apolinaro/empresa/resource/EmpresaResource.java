package com.apolinaro.empresa.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.apolinaro.empresa.model.Empresa;
import com.apolinaro.empresa.repository.EmpresaRepository;

@RestController
@RequestMapping("/empresas")
public class EmpresaResource {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@GetMapping
	public ResponseEntity<List<Empresa>> listar() {
		return ResponseEntity.ok(empresaRepository.findAll());
	}
	
	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody Empresa empresa) {
		empresa = empresaRepository.save(empresa);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(empresa.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable("id") Long id) {
		Empresa empresa = empresaRepository.findOne(id);
		
		return empresa != null ? ResponseEntity.ok(empresa) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		try {
			empresaRepository.delete(id);
		} catch (EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@RequestBody Empresa empresa, @PathVariable("id") Long id) {
		empresa.setId(id);
		empresaRepository.save(empresa);
		
		return ResponseEntity.noContent().build();
	}

}
