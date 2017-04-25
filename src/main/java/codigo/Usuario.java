package codigo;

public class Usuario {

	protected String nome_;
	protected boolean bloqueado_;
	
	public Usuario(String nome){
		nome_ = nome;
		bloqueado_ = false;
	}
	
	Usuario(UsuarioMaster master){
		nome_ = master.nome_;
		bloqueado_ = master.bloqueado_;
	}

	public String getNome() {
		return nome_;
	}
	
	public boolean isBloqueado() {
		return bloqueado_;
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
	
	void copy(Usuario usuario){
		nome_ = usuario.nome_;
		bloqueado_ = usuario.bloqueado_;
	}
}
