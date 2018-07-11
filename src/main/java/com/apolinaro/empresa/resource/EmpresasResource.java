package com.apolinaro.empresa.resource;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.apolinaro.empresa.service.EmpresasService;

@RestController
@RequestMapping("/empresas")
public class EmpresasResource {
	
	@Autowired
	private EmpresasService empresasService;
	
	@GetMapping
	public ResponseEntity<List<Empresa>> listar() {
		return ResponseEntity.ok(empresasService.listar());
	}
	
	@PostMapping
	public ResponseEntity<Void> salvar(@RequestBody Empresa empresa) {
		empresa = empresasService.salvar(empresa);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(empresa.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable("id") Long id) {
		Empresa empresa = empresasService.buscarPorId(id);
		return ResponseEntity.status(HttpStatus.OK).body(empresa); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		empresasService.buscarPorId(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@RequestBody Empresa empresa, @PathVariable("id") Long id) {
		empresa.setId(id);
		empresasService.salvar(empresa);
		return ResponseEntity.noContent().build();
	}

}
