package teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import codigo.Bibliotecaria;
import codigo.Usuario;

public class TesteSprint1 {

	private Bibliotecaria bibliotecaria_;
	private Usuario joao_;
	
	@Before
	public void setup(){
		bibliotecaria_ = new Bibliotecaria();
		joao_ = new Usuario("João");
	}
	
	/* Como bibliotecária, eu quero inserir um novo usuário, de modo que esse
	usuário consiga usar emprestar livros. */
	
	@Test
	public void testCriarUsuarioNormalmente() {
		assertTrue(bibliotecaria_.adicionarUsuario(joao_));
		assertEquals(joao_, bibliotecaria_.recuperarUsuario("João"));
	}
	
	@Test
	public void testCriarUsuarioNomeJaExistente(){
		bibliotecaria_.adicionarUsuario(joao_);
		Usuario usuario2 = new Usuario("João");
		assertFalse(bibliotecaria_.adicionarUsuario(usuario2));
		assertEquals(joao_, bibliotecaria_.recuperarUsuario("João"));
	}
	
	@Test
	public void testRecuperarUsuarioInexistente(){
		Usuario usuario = bibliotecaria_.recuperarUsuario("José");
		assertNull(usuario);
	}
	
	/* Como bibliotecária, eu quero remover usuário do sistema, de modo que
	ele não possa usar mais o sistema*/
	
	@Test
	public void testRemoverUsuarioNormalmente(){
		bibliotecaria_.adicionarUsuario(joao_);
		assertTrue(bibliotecaria_.removerUsuario("João"));
		assertNull(bibliotecaria_.recuperarUsuario("João"));
	}
	
	@Test
	public void testRemoverUsuarioInexistente(){
		assertFalse(bibliotecaria_.removerUsuario("José"));
	}
	
	/* Como bibliotecária, eu quero bloquear usuário do sistema por um prazo
	definido, de modo que ele não possa emprestar livros enquanto perdurar
	atraso na devolução. */
	
	@Test
	public void testBloquearUsuarioNormalmente(){
		assertFalse(joao_.isBloqueado());
		bibliotecaria_.bloquearUsuario(joao_);
		assertTrue(joao_.isBloqueado());
	}

	@Test
	public void testDesbloquearUsuario(){
		bibliotecaria_.bloquearUsuario(joao_);
		assertTrue(joao_.isBloqueado());
		bibliotecaria_.desbloquearUsuario(joao_);
		assertFalse(joao_.isBloqueado());
	}
}
