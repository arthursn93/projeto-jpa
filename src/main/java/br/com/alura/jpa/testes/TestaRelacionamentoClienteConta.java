package br.com.alura.jpa.testes;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.jpa.modelo.Cliente;
import br.com.alura.jpa.modelo.Conta;

public class TestaRelacionamentoClienteConta {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("contas");
		EntityManager em = emf.createEntityManager();
		
		Conta conta = new Conta();
		conta.setId(2L);
		
		Cliente cliente = new Cliente();
		cliente.setNome("Martins");
		cliente.setEndereco("Rua Panati, 231");
		cliente.setProfissao("Designer");
		cliente.setConta(conta);
		
		em.getTransaction().begin();	
		
		em.persist(cliente);
		
		em.getTransaction().commit();	
		em.close();

	}

}
