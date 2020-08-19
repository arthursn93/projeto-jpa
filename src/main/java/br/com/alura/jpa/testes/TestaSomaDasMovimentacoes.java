package br.com.alura.jpa.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import br.com.alura.jpa.modelo.Movimentacao;

public class TestaSomaDasMovimentacoes {

	public static void main(String[] args) {
		
		// EntityManager faz a conexão com banco de dados pelo persistence.xml
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();
		
		// Criteria builder cria CriteriaQuery para receber o tipo de objeto (BigDecimal)
		CriteriaBuilder builder = em.getCriteriaBuilder();         
		CriteriaQuery<BigDecimal> query = builder.createQuery(BigDecimal.class);
		
		// Root pra referenciar a raíz da nossa query, que é do tipo Movimentacao
		Root<Movimentacao> root = query.from(Movimentacao.class);
		
		// select sum(m.valor)
		Expression<BigDecimal> sum = builder.sum(root.<BigDecimal>get("valor")); 		
		query.select(sum);
		
		//Executa a query
		TypedQuery<BigDecimal> typedQuery = em.createQuery(query);
		System.out.println("Soma total das movimentações: " + typedQuery.getSingleResult());
	}
}
