package codigo;

public class Bibliotecaria {
	
	private BancoUsuarios usuarios_;
	private BancoLivros livros_;
	
	public Bibliotecaria(){
		usuarios_ = BancoUsuarios.getInstance();
		livros_ = BancoLivros.getInstance();
	}

	public boolean adicionarUsuario(Usuario novoUsuario){
		return usuarios_.adicionarUsuario(novoUsuario);
	}
	
	public Usuario recuperarUsuario(String nome){
		return usuarios_.recuperarUsuario(nome);
	}
	
	public boolean removerUsuario(String nome){
		return usuarios_.removerUsuario(nome);
	}
	
	public boolean bloquearUsuario(Usuario usuario){
		usuario.bloquear();
		usuarios_.atualizarUsuario(usuario);
		return true;
	}
	
	public boolean desbloquearUsuario(Usuario usuario){
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
		if(livro.getSituacao()!=SituacaoEnum.DISPONIVEL)
			return false;
		livro.emprestarLivro(nomeUsuario);
		livros_.atualizarLivro(livro);
		return true;
	}
	
	public boolean realizarDevolucao(String nomeLivro){
		Livro livro = livros_.recuperarLivro(nomeLivro);
		if(livro == null)
			return false;
		if(livro.getSituacao()!=SituacaoEnum.RETIRADO)
			return false;
		livro.devolvido();
		livros_.adicionarLivro(livro);
		return true;
	}

	public boolean adicionarLivro(Livro novoLivro) {
		if(novoLivro.getSituacao()!=SituacaoEnum.DISPONIVEL)
			return false;
		return livros_.adicionarLivro(novoLivro);
	}
	
	public Livro recuperarLivro(String nomeLivro){
		return livros_.recuperarLivro(nomeLivro);
	}
}
