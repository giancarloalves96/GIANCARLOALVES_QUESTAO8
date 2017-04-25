package codigo;

import java.util.ArrayList;

import excecoes.LivroInexistenteException;
import excecoes.UsuarioInexistenteException;

public class Usuario {

	private String nome_;
	private SituacaoUsuarioEnum situacao_;
	private BancoUsuarios bancoUsuarios_;
	private BancoLivros bancoLivros_;
	private BancoEmprestimos bancoEmprestimos_;
	
	public Usuario(String nome){
		nome_ = nome;
		situacao_ = SituacaoUsuarioEnum.LIBERADO;
	}
	
	public String getNome() {
		return nome_;
	}
	
	public SituacaoUsuarioEnum getSituacao() {
		return situacao_;
	}
	
	public String situacaoEmprestimos(){
		inicBancoEmprestimos();
		ArrayList<Emprestimo> emprestimos = bancoEmprestimos_.recuperarEmprestimos(nome_);
		String msg = "Empréstimos realizados por "+nome_+":\n";
		for(Emprestimo emprestimo : emprestimos)
			msg+=emprestimo.toString();
		return msg;
	}
	
	public boolean cadastrar(){
		inicBancoUsuarios();
		return bancoUsuarios_.adicionarUsuario(this);
	}
	
	public SituacaoLivroEnum situacaoLivro(String nomeLivro) throws UsuarioInexistenteException, LivroInexistenteException{
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
	
	private void inicBancoEmprestimos(){
		if(bancoEmprestimos_==null)
			bancoEmprestimos_ = BancoEmprestimos.getInstance();
	}
	
	void copy(Usuario usuario){
		nome_ = usuario.nome_;
		situacao_ = usuario.situacao_;
	}
	
	void bloquearPorAtraso(){
		situacao_ = SituacaoUsuarioEnum.BLOQUEADO_POR_ATRASO;
	}
	
	void bloquearPorCobranca(){
		situacao_ = SituacaoUsuarioEnum.BLOQUEADO_POR_COBRANCA;
	}
	
	void desbloquear(){
		situacao_ = SituacaoUsuarioEnum.LIBERADO;
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
	    if(situacao_ != other.situacao_){
	    	return false;
	    }
	    
	    return true;
	}
}
