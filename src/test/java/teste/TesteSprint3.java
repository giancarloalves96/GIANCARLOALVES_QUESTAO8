package teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import codigo.Bibliotecaria;
import codigo.Livro;
import codigo.SituacaoLivroEnum;
import codigo.Usuario;
import excecoes.LivroInexistenteException;
import excecoes.UsuarioInexistenteException;

public class TesteSprint3 {
	
	private Bibliotecaria bibliotecaria_; // Só para manter referência ao banco de livros!!
	
	@Before
	public void setup(){
		bibliotecaria_ = new Bibliotecaria();
		bibliotecaria_.adicionarLivro(new Livro("Metamorfose"));
	}

	@Test
	public void testCadastrar() {
		Usuario usuario = new Usuario("José");
		assertTrue(usuario.cadastrar());
	}
	
	@Test
	public void testNaoCadastraSeTiverNomeRepetido(){
		Usuario usuario = new Usuario("Joãozinho");
		assertTrue(usuario.cadastrar());
		usuario = new Usuario("Joãozinho");
		assertFalse(usuario.cadastrar());
	}
	
	@Test
	public void testPesquisarLivroNaLista() throws Exception{
		Usuario usuario = new Usuario("Maria");
		assertTrue(usuario.cadastrar());
		assertEquals(SituacaoLivroEnum.DISPONIVEL, usuario.situacaoLivro("Metamorfose"));		
	}
	
	@Test(expected=UsuarioInexistenteException.class)
	public void testErroSeUsuarioNaoEstiverCadastrado() throws UsuarioInexistenteException, LivroInexistenteException{
		Usuario usuario = new Usuario("Rony");
		usuario.situacaoLivro("Metamorfose");
	}
	
	@Test(expected=LivroInexistenteException.class)
	public void testErroSeLivroNaoExistir() throws UsuarioInexistenteException, LivroInexistenteException{
		Usuario usuario = new Usuario("Adriano");
		assertTrue(usuario.cadastrar());
		usuario.situacaoLivro("Cem anos de solidão");
	}
}
