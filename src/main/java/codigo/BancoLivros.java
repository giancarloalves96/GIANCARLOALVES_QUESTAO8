package codigo;

import java.util.Hashtable;

class BancoLivros {

	private static BancoLivros instance_ = new BancoLivros();
	private Hashtable<String, Livro> livros_; 
	
	private BancoLivros(){
		livros_ = new Hashtable<String, Livro>();
	}
	
	synchronized public static BancoLivros getInstance(){
		return instance_;
	}
	
	boolean atualizarLivro(Livro livro){
		String nome = livro.getNome();
		if(!livros_.containsKey(nome))
			return false;
		livros_.put(nome, livro);
		return true;
	}
	
	boolean existeLivro(String nomeLivro){
		if(livros_.containsKey(nomeLivro))
			return true;
		return false;
	}
	
	public boolean adicionarLivro(Livro novoLivro){
		String nome = novoLivro.getNome();
		if(livros_.containsKey(nome))
			return false;
		livros_.put(nome, novoLivro);
		return true;
	}
	
	public Livro recuperarLivro(String nome){
		if(!livros_.containsKey(nome))
			return null;
		return livros_.get(nome);
	}

	public boolean removerLivro(String nome) {
		if(!livros_.containsKey(nome))
			return false;
		livros_.remove(nome);
		return true;
	}
}
