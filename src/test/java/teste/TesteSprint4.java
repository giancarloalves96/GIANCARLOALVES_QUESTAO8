package teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import codigo.Bibliotecaria;
import codigo.Livro;
import codigo.SituacaoUsuarioEnum;
import codigo.Usuario;

public class TesteSprint4 {

	private Bibliotecaria bibliotecaria_;
	
	@Before
	public void setup(){
		bibliotecaria_ = new Bibliotecaria();
		bibliotecaria_.adicionarLivro(new Livro("Metamorfose"));
		bibliotecaria_.adicionarLivro(new Livro("Ensaio sobre a cegueira"));
		bibliotecaria_.adicionarLivro(new Livro("Irmaos Karamazov"));
		bibliotecaria_.adicionarLivro(new Livro("Game of Thrones"));
	}
	
	@Test
	public void testEmprestimosNoPrazo() {
		Usuario usuario = new Usuario("Pedro");
		bibliotecaria_.adicionarUsuario(usuario);
		bibliotecaria_.realizarEmprestimo("Metamorfose", "Pedro");
		bibliotecaria_.realizarEmprestimo("Ensaio sobre a cegueira", "Pedro");
		assertEquals("Empréstimos realizados por Pedro:\n"
				+ "Metamorfose: No prazo.\n"
				+ "Ensaio sobre a cegueira: No prazo.\n", usuario.situacaoEmprestimos());
		bibliotecaria_.realizarDevolucao("Metamorfose", "Pedro");
		bibliotecaria_.realizarDevolucao("Ensaio sobre a cegueira", "Pedro");
	}
	
	@Test
	public void testDevolucoesAtualizamEmprestimos(){
		Usuario usuario = new Usuario("Pedro");
		bibliotecaria_.adicionarUsuario(usuario);
		bibliotecaria_.realizarEmprestimo("Metamorfose", "Pedro");
		bibliotecaria_.realizarEmprestimo("Ensaio sobre a cegueira", "Pedro");
		bibliotecaria_.realizarDevolucao("Metamorfose", "Pedro");
		
		assertEquals("Empréstimos realizados por Pedro:\n"
				+ "Ensaio sobre a cegueira: No prazo.\n", usuario.situacaoEmprestimos());
		bibliotecaria_.realizarDevolucao("Ensaio sobre a cegueira", "Pedro");
	}
	
	@Test
	public void testEmprestimoForaDoPrazo(){
		Usuario usuario = new Usuario("PA");
		bibliotecaria_.adicionarUsuario(usuario);
		bibliotecaria_.realizarEmprestimo("Irmaos Karamazov", "PA");
		bibliotecaria_.realizarEmprestimo("Game of Thrones", "PA");
		
		assertTrue(bibliotecaria_.emprestimoAtrasado("Irmaos Karamazov", "PA"));
		
		assertEquals("Empréstimos realizados por PA:\n"
				+ "Irmaos Karamazov: Fora do prazo.\n"
				+ "Game of Thrones: No prazo.\n", usuario.situacaoEmprestimos());
		bibliotecaria_.realizarDevolucao("Irmaos Karamazov", "PA");
		bibliotecaria_.realizarDevolucao("Game of Thrones", "PA");
	}
	
	@Test
	public void testUsuarioSaberSituacao(){
		Usuario usuario = new Usuario("Fernando");
		bibliotecaria_.adicionarUsuario(usuario);
		bibliotecaria_.realizarEmprestimo("Irmaos Karamazov", "Fernando");
		bibliotecaria_.realizarEmprestimo("Game of Thrones", "Fernando");
		
		bibliotecaria_.emprestimoAtrasado("Irmaos Karamazov", "Fernando");
		
		assertEquals(SituacaoUsuarioEnum.BLOQUEADO_POR_ATRASO, usuario.getSituacao());
		
		bibliotecaria_.realizarDevolucao("Irmaos Karamazov", "Fernando");
		
		assertEquals("LIBERADO", usuario.getSituacao().toString());
		bibliotecaria_.realizarDevolucao("Game of Thrones", "Pedro");
	}
}
