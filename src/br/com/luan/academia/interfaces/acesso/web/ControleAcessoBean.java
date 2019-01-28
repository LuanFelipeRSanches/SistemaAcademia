package br.com.luan.academia.interfaces.acesso.web;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ValidationException;

import br.com.luan.academia.application.service.AcessoService;
import br.com.luan.academia.domain.acesso.TipoAcesso;

@Named
@RequestScoped
public class ControleAcessoBean implements Serializable {

	@EJB
	private AcessoService acessoService;

	@Inject
	private FacesContext facesContext;
	private String matricula;

	private Integer rg;

//botao
	public String registrarAcesso() {

		TipoAcesso tipoAcesso;
		try {
			tipoAcesso = acessoService.registrarAceso(matricula, rg);
		} catch (ValidationException e) {
			facesContext.addMessage(null, new FacesMessage(e.getMessage()));
			return null;
		}
		String msg;
		if (tipoAcesso == TipoAcesso.Entrada) {
			msg = "ENTRADA registrada!";
		} else if (tipoAcesso == TipoAcesso.Saida) {
			msg = "SAÍDA registrada!";
		} else {
			msg = "Dados de rtegistro de acesso inconsistente";
		}
		facesContext.addMessage(null, new FacesMessage(msg));
		return null;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Integer getRg() {
		return rg;
	}

	public void setRg(Integer rg) {
		this.rg = rg;
	}

}
