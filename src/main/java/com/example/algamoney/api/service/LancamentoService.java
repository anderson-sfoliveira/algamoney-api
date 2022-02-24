package com.example.algamoney.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoney.api.model.Lancamento;
import com.example.algamoney.api.model.Pessoa;
import com.example.algamoney.api.repository.LancamentoRepository;
import com.example.algamoney.api.repository.PessoaRepository;
import com.example.algamoney.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class LancamentoService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	public Lancamento salvar(Lancamento lancamento) {
		Pessoa pessoa = this.pessoaRepository.findById(lancamento.getPessoa().getCodigo()).orElseThrow(() -> new PessoaInexistenteOuInativaException());

		if (pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		
		return lancamentoRepository.save(lancamento);
	}

	public Lancamento atualizar(Long codigo, Lancamento lancamento) {
		Lancamento lancamentoSalvo = buscaLancamentoPeloCodigo(codigo);
		
		Pessoa pessoa = this.pessoaRepository.findById(lancamento.getPessoa().getCodigo()).orElseThrow(() -> new PessoaInexistenteOuInativaException());

		if (pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		
		BeanUtils.copyProperties(lancamento, lancamentoSalvo, "codigo");		
		return lancamentoRepository.save(lancamentoSalvo);
	}

	private Lancamento buscaLancamentoPeloCodigo(Long codigo) {
		Lancamento lancamentoSalvo = this.lancamentoRepository.findById(codigo).orElseThrow(() -> new EmptyResultDataAccessException(1));
		return lancamentoSalvo;
	}

}
