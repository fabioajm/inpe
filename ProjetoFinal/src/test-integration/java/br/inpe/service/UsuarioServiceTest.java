package br.inpe.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import br.inpe.exception.UsuarioException;
import br.inpe.model.Usuario;


@ContextConfiguration(locations = {"file:src/main/resources/conf/spring.xml"} ) 
@ActiveProfiles("test")
public class UsuarioServiceTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private UsuarioService usuarioService;
	private Usuario u;
	
	@Before
	public void init(){
		deleteFromTables("Usuario");
		u = new Usuario("Fabio","fab.ajm@gmail.com","123");
	}
	
	@Test
	public void salvarUsuario(){
		usuarioService.save(u);
		assertEquals("Fabio", usuarioService.buscarPorLogin(u.getLogin()).getNome());
	}
	
	@Test
	public void logarUsuario(){
		usuarioService.save(u);
		u = usuarioService.logar(u);
		assertEquals("Fabio", u.getNome());
	}

	@Test(expected=UsuarioException.class)
	public void usuarioNaoCadastrado(){
		u = usuarioService.logar(u);
	}

}
