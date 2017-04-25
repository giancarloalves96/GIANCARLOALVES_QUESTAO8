package codigo;

public class Bibliotecaria {
	
	private BancoUsuarios usuarios_;
	
	public Bibliotecaria(){
		usuarios_ = BancoUsuarios.getInstance();
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
	
	public void bloquearUsuario(Usuario usuario){
		UsuarioMaster master = new UsuarioMaster(usuario);
		master.bloquear();
		Usuario novoUsuario = new Usuario(master);
		usuario.copy(novoUsuario);
	}
	
	public void desbloquearUsuario(Usuario usuario){
		UsuarioMaster master = new UsuarioMaster(usuario);
		master.desbloquear();
		Usuario novoUsuario = new Usuario(master);
		usuario.copy(novoUsuario);
	}
}
