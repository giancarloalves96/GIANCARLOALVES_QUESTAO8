package teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import codigo.Bibliotecaria;
import codigo.SituacaoUsuarioEnum;
import codigo.Usuario;

public class TesteSprint1 {

	private Bibliotecaria bibliotecaria_;
	private Usuario joao_;
	
	@Before
	public void setup(){
		bibliotecaria_ = new Bibliotecaria();
		joao_ = new Usuario("Jo�o");
	}
	
	/* Como bibliotec�ria, eu quero inserir um novo usu�rio, de modo que esse
	usu�rio consiga usar emprestar livros. */
	
	@Test
	public void testCriarUsuarioNormalmente() {
		assertTrue(bibliotecaria_.adicionarUsuario(joao_));
		assertEquals(joao_, bibliotecaria_.recuperarUsuario("Jo�o"));
	}
	
	@Test
	public void testCriarUsuarioNomeJaExistente(){
		bibliotecaria_.adicionarUsuario(joao_);
		Usuario usuario2 = new Usuario("Jo�o");
		assertFalse(bibliotecaria_.adicionarUsuario(usuario2));
		assertEquals(joao_, bibliotecaria_.recuperarUsuario("Jo�o"));
	}
	
	@Test
	public void testRecuperarUsuarioInexistente(){
		Usuario usuario = bibliotecaria_.recuperarUsuario("Jos�");
		assertNull(usuario);
	}
	
	/* Como bibliotec�ria, eu quero remover usu�rio do sistema, de modo que
	ele n�o possa usar mais o sistema*/
	
	@Test
	public void testRemoverUsuarioNormalmente(){
		bibliotecaria_.adicionarUsuario(joao_);
		assertTrue(bibliotecaria_.removerUsuario("Jo�o"));
		assertNull(bibliotecaria_.recuperarUsuario("Jo�o"));
	}
	
	@Test
	public void testRemoverUsuarioInexistente(){
		assertFalse(bibliotecaria_.removerUsuario("Jos�"));
	}
	
	/* Como bibliotec�ria, eu quero bloquear usu�rio do sistema por um prazo
	definido, de modo que ele n�o possa emprestar livros enquanto perdurar
	atraso na devolu��o. */
	
	@Test
	public void testBloquearUsuarioNormalmente(){
		assertEquals(SituacaoUsuarioEnum.LIBERADO, joao_.getSituacao());
		bibliotecaria_.adicionarUsuario(joao_);
		bibliotecaria_.bloquearUsuarioPorAtraso("Jo�o");
		joao_ = bibliotecaria_.recuperarUsuario("Jo�o");
		assertEquals(SituacaoUsuarioEnum.BLOQUEADO_POR_ATRASO, joao_.getSituacao());
		bibliotecaria_.removerUsuario("Jo�o");
	}

	@Test
	public void testDesbloquearUsuario(){
		bibliotecaria_.adicionarUsuario(joao_);
		bibliotecaria_.bloquearUsuarioPorAtraso("Jo�o");
		joao_ = bibliotecaria_.recuperarUsuario("Jo�o");
		assertEquals(SituacaoUsuarioEnum.BLOQUEADO_POR_ATRASO, joao_.getSituacao());
		bibliotecaria_.desbloquearUsuarioPorAtraso("Jo�o");
		joao_ = bibliotecaria_.recuperarUsuario("Jo�o");
		assertEquals(SituacaoUsuarioEnum.LIBERADO, joao_.getSituacao());
		bibliotecaria_.removerUsuario("Jo�o");
	}
}
