package teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import codigo.Bibliotecaria;
import codigo.Livro;
import codigo.SituacaoLivroEnum;
import codigo.SituacaoUsuarioEnum;
import codigo.Usuario;
import excecoes.LivroInexistenteException;

public class TesteSprint5 {

	private Bibliotecaria bibliotecaria_;
	
	@Before
	public void setup(){
		bibliotecaria_ = new Bibliotecaria();
		bibliotecaria_.adicionarLivro(new Livro("Metamorfose"));
		bibliotecaria_.adicionarLivro(new Livro("Ensaio sobre a cegueira"));
		bibliotecaria_.adicionarLivro(new Livro("Irmaos Karamazov"));
		bibliotecaria_.adicionarLivro(new Livro("Game of Thrones"));
		bibliotecaria_.adicionarUsuario(new Usuario("João 5"));
		bibliotecaria_.adicionarUsuario(new Usuario("José 5"));
		bibliotecaria_.adicionarUsuario(new Usuario("Maria 5"));
		bibliotecaria_.adicionarUsuario(new Usuario("Reinaldo 5"));
	}
	
	@Test
	public void testBloquearUsuarioPorCobranca() {
		Usuario joao = bibliotecaria_.recuperarUsuario("João 5");
		assertEquals(SituacaoUsuarioEnum.LIBERADO, joao.getSituacao());
		bibliotecaria_.bloquearUsuarioPorCobranca("João 5");
		assertFalse(bibliotecaria_.bloquearUsuarioPorAtraso("João 5"));
		assertFalse(bibliotecaria_.desbloquearUsuarioPorAtraso("João 5"));
		assertEquals(SituacaoUsuarioEnum.BLOQUEADO_POR_COBRANCA, joao.getSituacao());
	}
	
	@Test
	public void testUsuarioBloqueadoPorCobrancaNaoPodePegarLivro(){
		bibliotecaria_.bloquearUsuarioPorCobranca("José 5");
		assertFalse(bibliotecaria_.realizarEmprestimo("Metamorfose", "José 5"));
		assertEquals(SituacaoLivroEnum.DISPONIVEL, 
				bibliotecaria_.recuperarLivro("Metamorfose").getSituacao());
	}

	@Test
	public void testUsuarioBloqueadoPorAtrasoNaoPodePegarLivro(){
		bibliotecaria_.bloquearUsuarioPorAtraso("Maria 5");
		assertFalse(bibliotecaria_.realizarEmprestimo("Metamorfose", "Maria 5"));
		assertEquals(SituacaoLivroEnum.DISPONIVEL, 
				bibliotecaria_.recuperarLivro("Metamorfose").getSituacao());
		bibliotecaria_.desbloquearUsuarioPorAtraso("Maria 5");
		assertTrue(bibliotecaria_.realizarEmprestimo("Metamorfose", "Maria 5"));
		bibliotecaria_.realizarDevolucao("Metamorfose", "Maria 5");
	}
	
	@Test
	public void testBibliotecariaAvaliaSituacaoDeUmLivro(){
		try {
			assertEquals(SituacaoLivroEnum.DISPONIVEL, bibliotecaria_.situacaoLivro("Game of Thrones"));
			bibliotecaria_.realizarEmprestimo("Game of Thrones", "Reinaldo 5");
			assertEquals(SituacaoLivroEnum.RETIRADO, bibliotecaria_.situacaoLivro("Game of Thrones"));
			bibliotecaria_.realizarDevolucao("Game of Thrones", "Reinaldo 5");
			assertEquals(SituacaoLivroEnum.DISPONIVEL, bibliotecaria_.situacaoLivro("Game of Thrones"));
		} catch (LivroInexistenteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
	@Test(expected = LivroInexistenteException.class)
	public void testBibliotecariaAvaliaSituacaoDeUmLivroNaoExistente() throws LivroInexistenteException{
		bibliotecaria_.situacaoLivro("Quimica Basica");
	}
}
