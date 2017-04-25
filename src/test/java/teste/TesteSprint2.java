package teste;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import codigo.Bibliotecaria;
import codigo.Livro;
import codigo.SituacaoEnum;
import codigo.Usuario;

public class TesteSprint2 {

	private Bibliotecaria bibliotecaria_;
	private Livro metamorfose_;
	private Usuario joao_;
	
	@Before
	public void setup(){
		bibliotecaria_ = new Bibliotecaria();
		metamorfose_ = new Livro("Metamorfose");
		joao_ = new Usuario("João");
		bibliotecaria_.adicionarLivro(metamorfose_);
		bibliotecaria_.adicionarUsuario(joao_);
	}
	
	@Test
	public void testRegistrarEmpestimo() {
		Livro livro;
		livro = bibliotecaria_.recuperarLivro("Metamorfose");
		assertEquals(SituacaoEnum.DISPONIVEL, livro.getSituacao());
		assertTrue(bibliotecaria_.realizarEmprestimo("Metamorfose", "João"));
		assertEquals(SituacaoEnum.RETIRADO, livro.getSituacao());
		assertEquals("João", livro.getUsuario());
		assertFalse(bibliotecaria_.realizarEmprestimo("Metamorfose", "João"));
		bibliotecaria_.realizarDevolucao("Metamorfose");
	}
	
	@Test
	public void testNaoEmprestarParaUsuarioInexistente(){
		Livro livro;
		livro = bibliotecaria_.recuperarLivro("Metamorfose");
		assertEquals(SituacaoEnum.DISPONIVEL, livro.getSituacao());
		assertFalse(bibliotecaria_.realizarEmprestimo("Metamorfose", "José"));
		assertNull(livro.getUsuario());
		assertEquals(SituacaoEnum.DISPONIVEL, livro.getSituacao());
	}
	
	@Test
	public void testNaoEmprestarLivroQueJaFoiEmprestado(){
		Livro livro;
		livro = bibliotecaria_.recuperarLivro("Metamorfose");
		assertTrue(bibliotecaria_.realizarEmprestimo("Metamorfose", "João"));
		assertEquals(SituacaoEnum.RETIRADO, livro.getSituacao());
		assertFalse(bibliotecaria_.realizarEmprestimo("Metamorfose", "José"));
		assertEquals("João", livro.getUsuario());
		bibliotecaria_.realizarDevolucao("Metamorfose");
	}
	
	@Test
	public void testDevolucaoLivro(){
		Livro livro;
		livro = bibliotecaria_.recuperarLivro("Metamorfose");
		bibliotecaria_.realizarEmprestimo("Metamorfose", "João");
		assertEquals(SituacaoEnum.RETIRADO, livro.getSituacao());
		assertTrue(bibliotecaria_.realizarDevolucao("Metamorfose")); 
	}
	
	@Test
	public void testNaoDevolverLivroInexistente(){
		assertFalse(bibliotecaria_.realizarDevolucao("Cem Anos de Solidão"));
	}
}
