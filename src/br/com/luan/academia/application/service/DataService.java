package br.com.luan.academia.application.service;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.luan.academia.domain.aluno.Estado;
import br.com.luan.academia.domain.aluno.EstadoRepository;
import br.com.luan.academia.domain.aluno.Aluno.Sexo;
import br.com.luan.academia.domain.aluno.Aluno.Situacao;

@Stateless
public class DataService {

	@EJB
	private EstadoRepository estadoRepository;

	public List<Estado> listaEstados() {
		return estadoRepository.listaEstado();
	}

	public Sexo[] getSexos() {
		return Sexo.values();
	}

	public Situacao[] getSituacoes() {
		return Situacao.values();
	}

}
