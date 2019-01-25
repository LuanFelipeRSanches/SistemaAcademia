package br.com.luan.academia.application.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import br.com.luan.academia.application.util.StringUtils;
import br.com.luan.academia.application.util.Validation;
import br.com.luan.academia.application.util.ValidationException;
import br.com.luan.academia.domain.aluno.Aluno;
import br.com.luan.academia.domain.aluno.AlunoRepository;

//criar ou atualizar o aluno
@Stateless
public class AlunoService {

	@EJB
	private AlunoRepository alunoRepository;

	public void createOrUpdate(Aluno aluno) {
		if (StringUtils.isEmpty(aluno.getMatricula())) {
			create(aluno);
		} else {
			update(aluno);
		}

	}

	private void create(Aluno aluno) {
		Validation.assertNotEmpty(aluno);

		String maxMatricula = alunoRepository.getMaxMatriculaAno();
		aluno.gerarMatricula(maxMatricula);
		aluno.getMatricula();
		alunoRepository.store(aluno);
	}

	public void delete(String matricula) {
		alunoRepository.delete(matricula);
	}

	private void update(Aluno aluno) {
		Validation.assertNotEmpty(aluno);
		Validation.assertNotEmpty(aluno.getMatricula());

		alunoRepository.update(aluno);
	}

	public Aluno findByMatricula(String matricula) {
		return alunoRepository.findByMatricula(matricula);
	}

	public List<Aluno> listAluno(String matricula, String nome, Integer rg, Integer telefone) {
		if(StringUtils.isEmpty(matricula) && StringUtils.isEmpty(nome) && rg == null && telefone == null) {
			throw new ValidationException("Pelo menos um critério tem que ser preenchido");
		}
		return alunoRepository.listAlunos(matricula, nome, rg, telefone);

	}
}
