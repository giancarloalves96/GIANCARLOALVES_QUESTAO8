package teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import codigo.Bibliotecaria;
import codigo.Livro;
import codigo.SituacaoLivroEnum;
import codigo.Usuario;

public class TesteSprint2 {

	private Bibliotecaria bibliotecaria_;
	
	@Before
	public void setup(){
		bibliotecaria_ = new Bibliotecaria();
		bibliotecaria_.adicionarLivro(new Livro("Metamorfose"));
		bibliotecaria_.adicionarUsuario(new Usuario("João 3"));
		bibliotecaria_.adicionarUsuario(new Usuario("Ronaldo 3"));
		bibliotecaria_.adicionarUsuario(new Usuario("Robinho 3"));
	}
	
	@Test
	public void testRegistrarEmpestimo() {
		Livro livro;
		livro = bibliotecaria_.recuperarLivro("Metamorfose");
		assertEquals(SituacaoLivroEnum.DISPONIVEL, livro.getSituacao());
		assertTrue(bibliotecaria_.realizarEmprestimo("Metamorfose", "João 3"));
		assertEquals(SituacaoLivroEnum.RETIRADO, livro.getSituacao());
		assertEquals("João 3", livro.getUsuario());
		assertFalse(bibliotecaria_.realizarEmprestimo("Metamorfose", "João 3"));
		bibliotecaria_.realizarDevolucao("Metamorfose", "João 3");
	}
	
	@Test
	public void testNaoEmprestarParaUsuarioInexistente(){
		Livro livro;
		livro = bibliotecaria_.recuperarLivro("Metamorfose");
		assertEquals(SituacaoLivroEnum.DISPONIVEL, livro.getSituacao());
		assertFalse(bibliotecaria_.realizarEmprestimo("Metamorfose", "José"));
		assertNull(livro.getUsuario());
		assertEquals(SituacaoLivroEnum.DISPONIVEL, livro.getSituacao());
	}
	
	@Test
	public void testNaoEmprestarLivroQueJaFoiEmprestado(){
		Livro livro;
		livro = bibliotecaria_.recuperarLivro("Metamorfose");
		assertTrue(bibliotecaria_.realizarEmprestimo("Metamorfose", "Ronaldo 3"));
		assertEquals(SituacaoLivroEnum.RETIRADO, livro.getSituacao());
		assertFalse(bibliotecaria_.realizarEmprestimo("Metamorfose", "José"));
		assertEquals("Ronaldo 3", livro.getUsuario());
		bibliotecaria_.realizarDevolucao("Metamorfose", "Ronaldo 3");
	}
	
	@Test
	public void testDevolucaoLivro(){
		Livro livro;
		livro = bibliotecaria_.recuperarLivro("Metamorfose");
		bibliotecaria_.realizarEmprestimo("Metamorfose", "Robinho 3");
		assertEquals(SituacaoLivroEnum.RETIRADO, livro.getSituacao());
		assertTrue(bibliotecaria_.realizarDevolucao("Metamorfose", "Robinho 3")); 
	}
	
	@Test
	public void testNaoDevolverLivroInexistente(){
		assertFalse(bibliotecaria_.realizarDevolucao("João 3", "Cem Anos de Solidão"));
	}
}
