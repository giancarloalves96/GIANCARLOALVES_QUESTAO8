package codigo;

import java.util.ArrayList;
import java.util.Hashtable;

class BancoEmprestimos {

	private static BancoEmprestimos instance_ = new BancoEmprestimos();
	private Hashtable<String, ArrayList<Emprestimo>> emprestimos_; 
	
	private BancoEmprestimos(){
		emprestimos_ = new Hashtable<String, ArrayList<Emprestimo>>();
	}
	
	synchronized public static BancoEmprestimos getInstance(){
		return instance_;
	}
	
	public void adicionarEmprestimo(String nomeUsuario, String nomeLivro){
		if(!emprestimos_.containsKey(nomeUsuario))
			emprestimos_.put(nomeUsuario, new ArrayList<Emprestimo>());
		ArrayList<Emprestimo> emprestimos = emprestimos_.get(nomeUsuario);
		emprestimos.add(new Emprestimo(nomeLivro));
	}
	
	public void retirarEmprestimo(String nomeUsuario, String nomeLivro){
		if(!emprestimos_.containsKey(nomeUsuario))
			return;
		ArrayList<Emprestimo> emprestimos = emprestimos_.get(nomeUsuario);
		Emprestimo aSerRemovido = null;
		for(Emprestimo emprestimo : emprestimos)
			if(emprestimo.getLivro() == nomeLivro)
				aSerRemovido = emprestimo;
		if(aSerRemovido!=null)
			emprestimos.remove(aSerRemovido);
	}
	
	public ArrayList<Emprestimo> recuperarEmprestimos(String nomeUsuario){
		if(!emprestimos_.containsKey(nomeUsuario))
			return new ArrayList<Emprestimo>();
		return emprestimos_.get(nomeUsuario);
	}
	
	public boolean existeEmprestimo(String nomeUsuario, String nomeLivro){
		if(!emprestimos_.containsKey(nomeUsuario))
			return false;
		ArrayList<Emprestimo> emprestimos = emprestimos_.get(nomeUsuario);
		boolean flag = false;
		for(Emprestimo emprestimo : emprestimos)
			if(emprestimo.getLivro() == nomeLivro)
				flag = true;
		return flag;
	}

	public void atrasarEmprestimo(String nomeUsuario, String nomeLivro) {
		if(!emprestimos_.containsKey(nomeUsuario))
			return;
		ArrayList<Emprestimo> emprestimos = emprestimos_.get(nomeUsuario);
		Emprestimo atrasado = null;
		
		for(Emprestimo emprestimo : emprestimos)
			if(emprestimo.getLivro() == nomeLivro)
				atrasado = emprestimo;
		
		atrasado.atrasado();
	}
}
