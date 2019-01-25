package br.com.luan.academia.interfaces.aluno.web;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import br.com.luan.academia.application.service.AlunoService;
import br.com.luan.academia.application.util.ValidationException;
import br.com.luan.academia.domain.aluno.Aluno;

@Named
@SessionScoped // Garante que o bean fica ativo quando o navegador ficar aberto
public class PesquisaAlunoBean implements Serializable {

	@EJB
	private AlunoService alunoService;

	private FacesContext facesContext;

	private String matricula;
	private String nome;
	private Integer rg;
	private Integer telefone;

	private List<Aluno> alunos;

	public String pesquisar() {
		try {
			alunos = alunoService.listAluno(matricula, nome, rg, telefone);
		} catch (ValidationException e) {
			facesContext.addMessage(null, new FacesMessage(e.getMessage()));
		}
		return null;
	}

	public String excluir(String matricula) {
		alunoService.delete(matricula);
		return pesquisar();
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getRg() {
		return rg;
	}

	public void setRg(Integer rg) {
		this.rg = rg;
	}

	public Integer getTelefone() {
		return telefone;
	}

	public void setTelefone(Integer telefone) {
		this.telefone = telefone;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}
}
