package com.apolinaro.empresa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.apolinaro.empresa.model.Empresa;
import com.apolinaro.empresa.repository.EmpresasRepository;
import com.apolinaro.empresa.service.exception.EmpresaNaoEncontradaExcepton;

@Service
public class EmpresasService {
	
	@Autowired
	private EmpresasRepository empresasRepository;
	
	public List<Empresa> listar() {
		return empresasRepository.findAll();
	}
	
	public Empresa salvar(Empresa empresa) {
		empresa.setId(null);
		return empresasRepository.save(empresa);
	}
	
	public Empresa buscarPorId(Long id) {
		Empresa empresa = empresasRepository.findOne(id);
		if (empresa == null) {
			throw new EmpresaNaoEncontradaExcepton("A empresa não pode ser encontrada.");
		}
		return empresa;
	}
	
	public void deletar(Long id) {
		try {
			empresasRepository.findOne(id);
		} catch (EmptyResultDataAccessException e) {
			throw new EmpresaNaoEncontradaExcepton("A empresa não pode ser encontrada.");		
		}
	}
	
	public void atualizar(Empresa empresa) {
		verificarExistencia(empresa);
		empresasRepository.save(empresa);
	}

	private void verificarExistencia(Empresa empresa) {
		buscarPorId(empresa.getId());
	}

}
