package br.inpe.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import br.inpe.exception.UsuarioException;
import br.inpe.model.Preferencia;
import br.inpe.model.Usuario;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations  = {"file:src/main/resources/conf/spring.xml"} ) 
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
@ActiveProfiles("test")
@DatabaseSetup(value= "/xml/cleanAll.xml")
public class UsuarioServiceTest{
	
	@Autowired
	private UsuarioService usuarioService;
	private Usuario u;
	
	@Before
	public void init(){
		u = new Usuario("Fabio Oliveira","fab.ajm@gmail.com","123");
	}
	
	@Test
	@DatabaseSetup(value= "/xml/usuarios.xml")
	public void buscarPorLogin(){
		assertEquals(u.getNome(), usuarioService.buscarPorLogin(u.getLogin()).getNome());
	}
	
	@Test
	@DatabaseSetup(value= "/xml/usuariosPreferencias.xml")
	public void logarUsuarioComPreferencias(){
		u = usuarioService.logar(u);
		assertEquals(u.getNome(), u.getNome());
		assertEquals(2, u.getPreferencias().size());
		assertTrue(u.getPreferencias().get(0) instanceof Preferencia);
	}

	@Test(expected=UsuarioException.class)
	public void usuarioNaoCadastrado(){
		u = new Usuario("Joao","joao@gmail.com","123");
		u = usuarioService.logar(u);
	}
	

}
