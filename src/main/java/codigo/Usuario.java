package codigo;

import excecoes.LivroInexistenteException;
import excecoes.UsuarioInexistenteException;

public class Usuario {

	private String nome_;
	private boolean bloqueado_;
	private BancoUsuarios bancoUsuarios_;
	private BancoLivros bancoLivros_;
	
	public Usuario(String nome){
		nome_ = nome;
		bloqueado_ = false;
	}
	
	public String getNome() {
		return nome_;
	}
	
	public boolean isBloqueado() {
		return bloqueado_;
	}
	
	public boolean cadastrar(){
		inicBancoUsuarios();
		return bancoUsuarios_.adicionarUsuario(this);
	}
	
	public SituacaoEnum situacaoLivro(String nomeLivro) throws UsuarioInexistenteException, LivroInexistenteException{
		inicBancoUsuarios();
		if(!bancoUsuarios_.estaCadastrado(nome_))
			throw new UsuarioInexistenteException("Usuário não está cadastrado.");
		inicBancoLivros();
		if(!bancoLivros_.existeLivro(nomeLivro))
			throw new LivroInexistenteException("Livro não escontrado.");
		Livro livro = bancoLivros_.recuperarLivro(nomeLivro);
		return livro.getSituacao();
	}
	
	private void inicBancoUsuarios(){
		if(bancoUsuarios_==null)
			bancoUsuarios_ = BancoUsuarios.getInstance();
	}
	
	private void inicBancoLivros(){
		if(bancoLivros_==null)
			bancoLivros_ = BancoLivros.getInstance();
	}
	
	void copy(Usuario usuario){
		nome_ = usuario.nome_;
		bloqueado_ = usuario.bloqueado_;
	}
	
	void bloquear(){
		bloqueado_ = true;
	}
	
	void desbloquear(){
		bloqueado_ = false;
	}
	
	@Override
	public boolean equals(Object obj){
		if (obj == null) {
	        return false;
	    }
	    if (!(obj instanceof Usuario)) {
	        return false;
	    }
	    final Usuario other = (Usuario) obj;
	    if ((nome_ == null) ? (other.nome_ != null) : !nome_.equals(other.nome_)) {
	        return false;
	    }
	    if(bloqueado_ && !other.bloqueado_ || !bloqueado_ && other.bloqueado_){
	    	return false;
	    }
	    // Próximos campos
	    
	    
	    return true;
	}
}
