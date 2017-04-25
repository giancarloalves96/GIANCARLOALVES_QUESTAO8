package codigo;

class Emprestimo {

	private String nomeLivro_;
	private boolean noPrazo_;
	
	public Emprestimo(String nomeLivro) {
		nomeLivro_ = nomeLivro;
		noPrazo_ = true;
	}

	public String getLivro(){
		return nomeLivro_;
	}
	
	public boolean isAtrasado(){
		return !noPrazo_;
	}
	
	@Override
	public String toString(){
		String prazo;
		if(noPrazo_)
			prazo = "No prazo.\n";
		else prazo = "Fora do prazo.\n";
		return nomeLivro_+": "+prazo;
	}
	
	public void atrasado(){
		noPrazo_ = false;
	}
}
