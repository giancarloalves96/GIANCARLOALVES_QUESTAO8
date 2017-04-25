package codigo;

import java.util.ArrayList;

import excecoes.LivroInexistenteException;
import excecoes.UsuarioInexistenteException;

public class Bibliotecaria {
	
	private BancoUsuarios usuarios_;
	private BancoLivros livros_;
	private BancoEmprestimos emprestimos_;
	
	public Bibliotecaria(){
		usuarios_ = BancoUsuarios.getInstance();
		livros_ = BancoLivros.getInstance();
		emprestimos_ = BancoEmprestimos.getInstance();
	}

	public boolean adicionarUsuario(Usuario novoUsuario){
		return usuarios_.adicionarUsuario(novoUsuario);
	}
	
	public Usuario recuperarUsuario(String nome){
		return usuarios_.recuperarUsuario(nome);
	}
	
	public boolean removerUsuario(String nome){
		emprestimos_.removerUsuario(nome);
		return usuarios_.removerUsuario(nome);
	}
	
	public boolean bloquearUsuarioPorCobranca(String nomeUsuario){
		Usuario usuario = usuarios_.recuperarUsuario(nomeUsuario);
		if(usuario == null)
			return false;
		usuario.bloquearPorCobranca();
		return true;
	}
	
	public boolean bloquearUsuarioPorAtraso(String nomeUsuario){
		Usuario usuario = usuarios_.recuperarUsuario(nomeUsuario);
		if(usuario == null)
			return false;
		if(usuario.getSituacao()==SituacaoUsuarioEnum.BLOQUEADO_POR_COBRANCA)
			return false;
		usuario.bloquearPorAtraso();
		usuarios_.atualizarUsuario(usuario);
		return true;
	}
	
	public boolean desbloquearUsuarioPorAtraso(String nomeUsuario){
		Usuario usuario = usuarios_.recuperarUsuario(nomeUsuario);
		if(usuario == null)
			return false;
		if(usuario.getSituacao()==SituacaoUsuarioEnum.BLOQUEADO_POR_COBRANCA)
			return false;
		usuario.desbloquear();
		usuarios_.atualizarUsuario(usuario);
		return true;
	}
	
	public boolean realizarEmprestimo(String nomeLivro, String nomeUsuario){
		Livro livro = livros_.recuperarLivro(nomeLivro);
		if(livro == null)
			return false;
		Usuario usuario = usuarios_.recuperarUsuario(nomeUsuario);
		if(usuario == null)
			return false;
		if(livro.getSituacao()!=SituacaoLivroEnum.DISPONIVEL)
			return false;
		if(usuario.getSituacao()!=SituacaoUsuarioEnum.LIBERADO)
			return false;
		
		livro.emprestarLivro(nomeUsuario);
		livros_.atualizarLivro(livro);
		emprestimos_.adicionarEmprestimo(nomeUsuario, nomeLivro);
		return true;
	}
	
	public boolean realizarDevolucao(String nomeLivro, String nomeUsuario){
		Livro livro = livros_.recuperarLivro(nomeLivro);
		if(livro == null)
			return false;
		if(livro.getSituacao()!=SituacaoLivroEnum.RETIRADO)
			return false;
		livro.devolvido();
		livros_.atualizarLivro(livro);
		
		emprestimos_.retirarEmprestimo(nomeUsuario, nomeLivro);
		atualizarEmprestimosAtrasados(nomeUsuario);
		return true;
	}
	
	private void atualizarEmprestimosAtrasados(String nomeUsuario) {
		ArrayList<Emprestimo> emprestimos = emprestimos_.recuperarEmprestimos(nomeUsuario);
		
		boolean flag = false;
		for(Emprestimo emprestimo : emprestimos)
			if(emprestimo.isAtrasado())
				flag = true;
		if(!flag)
			desbloquearUsuarioPorAtraso(nomeUsuario);
		
	}

	public boolean emprestimoAtrasado(String nomeLivro, String nomeUsuario){
		if(!emprestimos_.existeEmprestimo(nomeUsuario, nomeLivro))
			return false;
		emprestimos_.atrasarEmprestimo(nomeUsuario, nomeLivro);
		bloquearUsuarioPorAtraso(nomeUsuario);
		return true;
	}

	public boolean adicionarLivro(Livro novoLivro) {
		if(novoLivro.getSituacao()!=SituacaoLivroEnum.DISPONIVEL)
			return false;
		return livros_.adicionarLivro(novoLivro);
	}
	
	public Livro recuperarLivro(String nomeLivro){
		return livros_.recuperarLivro(nomeLivro);
	}
	
	public SituacaoLivroEnum situacaoLivro(String nomeLivro) throws LivroInexistenteException{
		if(!livros_.existeLivro(nomeLivro))
			throw new LivroInexistenteException("Livro não escontrado.");
		Livro livro = livros_.recuperarLivro(nomeLivro);
		return livro.getSituacao();
	}
}
