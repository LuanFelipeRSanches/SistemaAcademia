package br.com.luan.academia.domain.aluno;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EstadoRepository {

	@PersistenceContext
	private EntityManager em;

	public List<Estado> listaEstado() {
		return em.createQuery("SELECT e FROM Estado e ORDER BY e.nome", Estado.class).getResultList();

	}
}
