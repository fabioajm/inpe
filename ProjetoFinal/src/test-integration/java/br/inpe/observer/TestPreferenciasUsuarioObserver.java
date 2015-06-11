package br.inpe.observer;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import br.inpe.model.Produto;
import br.inpe.model.Usuario;
import br.inpe.repository.PreferenciaRepository;
import br.inpe.service.UsuarioService;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations  = {"file:src/main/resources/conf/spring.xml"} ) 
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@ActiveProfiles("test")
@DatabaseSetup(value= "/xml/cleanAll.xml")
public class TestPreferenciasUsuarioObserver {
	
	@Autowired
	private UsuarioService uService;
	
	@Autowired
	private PreferenciaRepository prefRepository;
	

	@Test
	@DatabaseSetup(value= "/xml/preferencias.xml")
	public void adicaoPrefernciasUsuario(){
		Usuario u = new Usuario(20l, "Fabio Alves");
		Produto p = new Produto(1, "Filme", 250.0);
		
		CarrinhoObserver co = new PreferenciasUsuarioObserver(u, uService);
		co.notificarAdicao(p, 5);
		assertEquals(1,u.getPreferencias().size());
		assertEquals(1, prefRepository.findAll().size());
	}
	
	@Test
	@DatabaseSetup(value= "/xml/preferencias.xml")
	public void adicaoDuasPrefernciasIguaisUsuario(){
		Usuario u = new Usuario(20l, "Fabio Alves");
		Produto p1 = new Produto(1, "Filme", 250.0);
		
		CarrinhoObserver co = new PreferenciasUsuarioObserver(u, uService);
		co.notificarAdicao(p1, 5);
		co.notificarAdicao(p1, 5);
		assertEquals(2 ,u.getPreferencias().size());
		assertEquals(2, prefRepository.findAll().size());
	}
	
	@Test
	@DatabaseSetup(value= "/xml/preferencias.xml")
	public void adicaoDuasPrefernciasDiferentesUsuario(){
		Usuario u = new Usuario(20l, "Fabio Alves");
		Produto p1 = new Produto(1, "Filme", 250.0);
		Produto p2 = new Produto(2, "Celular", 500.0);
		
		CarrinhoObserver co = new PreferenciasUsuarioObserver(u, uService);
		co.notificarAdicao(p1, 5);
		co.notificarAdicao(p2, 5);
		assertEquals(2 ,u.getPreferencias().size());
		assertEquals(2, prefRepository.findAll().size());
	}
}
