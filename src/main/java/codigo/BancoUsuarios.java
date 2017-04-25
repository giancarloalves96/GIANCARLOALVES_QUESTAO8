package codigo;

import java.util.Hashtable;

class BancoUsuarios {

	private static BancoUsuarios instance_ = new BancoUsuarios();
	private Hashtable<String, Usuario> usuarios_; 
	
	private BancoUsuarios(){
		usuarios_ = new Hashtable<String, Usuario>();
	}
	
	synchronized public static BancoUsuarios getInstance(){
		return instance_;
	}
	
	public boolean adicionarUsuario(Usuario novoUsuario){
		String nome = novoUsuario.getNome();
		if(usuarios_.containsKey(nome))
			return false;
		usuarios_.put(nome, novoUsuario);
		return true;
	}
	
	public Usuario recuperarUsuario(String nome){
		if(!usuarios_.containsKey(nome))
			return null;
		return usuarios_.get(nome);
	}

	public boolean removerUsuario(String nome) {
		if(!usuarios_.containsKey(nome))
			return false;
		usuarios_.remove(nome);
		return true;
	}
}
