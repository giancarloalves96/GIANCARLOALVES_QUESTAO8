package codigo;

class UsuarioMaster extends Usuario {

	UsuarioMaster(Usuario usuario) {
		super(usuario.nome_);
		bloqueado_ = usuario.bloqueado_;
	}
	
	public void bloquear(){
		bloqueado_ = true;
	}
	
	public void desbloquear(){
		bloqueado_ = false;
	}
}
