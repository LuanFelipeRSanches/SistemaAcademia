package br.com.luan.academia.application.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.ValidationException;

import br.com.luan.academia.application.util.StringUtils;
import br.com.luan.academia.domain.acesso.Acesso;
import br.com.luan.academia.domain.acesso.AcessoRepository;
import br.com.luan.academia.domain.acesso.TipoAcesso;
import br.com.luan.academia.domain.aluno.Aluno;
import br.com.luan.academia.domain.aluno.AlunoRepository;

@Stateless
public class AcessoService {

	@EJB
	private AcessoRepository acessoRepository;

	@EJB
	private AlunoRepository alunoRepository;

	public TipoAcesso registrarAceso(String matricula, Integer rg) {
		if (StringUtils.isEmpty(matricula) && rg == null) {
			throw new ValidationException("É preciso fornecer  a matrícula ou o RG do Aluno");
		}
		Aluno aluno;
		if (StringUtils.isEmpty(matricula)) {
			aluno = alunoRepository.findByRG(rg);
		} else {
			aluno = alunoRepository.findByMatricula(matricula);
		}
		if (aluno == null) {
			throw new ValidationException("O aluno não foi encontrado");
		}
		Acesso ultimoAcesso = acessoRepository.findUltimoAcesso(aluno);
		TipoAcesso tipoAcesso;
		if (ultimoAcesso == null || ultimoAcesso.isEntradaSaidaPreenchidas()) {
			ultimoAcesso = new Acesso();
			ultimoAcesso.setAluno(aluno);
			tipoAcesso = ultimoAcesso.registrarAcesso();
			acessoRepository.store(ultimoAcesso);
		} else {
			tipoAcesso = ultimoAcesso.registrarAcesso();
		}
		return tipoAcesso;
	}
}
