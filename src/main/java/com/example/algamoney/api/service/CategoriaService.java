package com.example.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Categoria;
import com.example.algamoney.api.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria atualizar(Long codigo, Categoria categoria) {
		Categoria categoriaSalva = buscaCategoriaPeloCodigo(codigo);
		
		// copia as informações de "categoria" para "categoriaSalva" exceto a informação do campo "codigo".
		BeanUtils.copyProperties(categoria, categoriaSalva, "codigo");		
		return categoriaRepository.save(categoriaSalva);
	}

	private Categoria buscaCategoriaPeloCodigo(Long codigo) {
		Categoria categoriaSalva = this.categoriaRepository.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));
		return categoriaSalva;
	}

}
