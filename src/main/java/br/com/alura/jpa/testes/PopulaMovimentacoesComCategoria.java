package br.com.alura.jpa.testes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Categoria;
import br.com.alura.jpa.modelo.Conta;
import br.com.alura.jpa.modelo.Movimentacao;
import br.com.alura.jpa.modelo.TipoMovimentacao;

public class PopulaMovimentacoesComCategoria {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();
		
		Categoria categoria = new Categoria();
		categoria.setNome("Viagem");
		Categoria categoria2 = new Categoria();
		categoria2.setNome("Negócios");
		
		Conta conta = em.find(Conta.class, 2L);
		
		Movimentacao movimentacao = new Movimentacao();
		movimentacao.setDescricao("Viagem à RS");
		movimentacao.setTipoMovimentacao(TipoMovimentacao.SAIDA);
		movimentacao.setData(LocalDateTime.now()); //hoje
		movimentacao.setValor(new BigDecimal(300.0));
		movimentacao.setCategorias(Arrays.asList(categoria));
		movimentacao.setConta(conta);
		
		Movimentacao movimentacao2 = new Movimentacao();
		movimentacao2.setDescricao("Viagem à ES");
		movimentacao2.setTipoMovimentacao(TipoMovimentacao.SAIDA);
		movimentacao2.setData(LocalDateTime.now().plusDays(1));//amanhã
		movimentacao2.setValor(new BigDecimal(250.0));
		movimentacao2.setCategorias(Arrays.asList(categoria2));
		movimentacao2.setConta(conta);
		
		em.getTransaction().begin();	
		
		em.persist(categoria);
		em.persist(categoria2);
		em.persist(movimentacao);
		em.persist(movimentacao2);
		
		em.getTransaction().commit();	
		em.close();

	}

}
