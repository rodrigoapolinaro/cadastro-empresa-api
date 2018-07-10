package com.apolinaro.empresa.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apolinaro.empresa.model.Empresa;
import com.apolinaro.empresa.repository.EmpresaRepository;

@RestController
@RequestMapping("/empresas")
public class EmpresaResource {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@GetMapping
	public List<Empresa> listar() {
		return empresaRepository.findAll();
	}
	
	@PostMapping
	public void salvar(@RequestBody Empresa empresa) {
		empresaRepository.save(empresa);
	}
	
	@GetMapping("/{id}")
	public Empresa buscarPorId(@PathVariable("id") Long id) {
		return empresaRepository.findOne(id);
	}
	
	@DeleteMapping("/{id}")
	public void deletar(@PathVariable("id") Long id) {
		empresaRepository.delete(id);
	}
	
	@PutMapping("/{id}")
	public void atualizar(@RequestBody Empresa empresa, @PathVariable("id") Long id) {
		empresa.setId(id);
		empresaRepository.save(empresa);
	}

}
