package codigo;

public class Livro {

	private String nome_;
	private SituacaoEnum situacao_;
	private String nomeUsuario_;
	
	public Livro(String nome){
		nome_ = nome;
		situacao_ = SituacaoEnum.DISPONIVEL;
	}

	public String getNome() {
		return nome_;
	}
	
	public SituacaoEnum getSituacao(){
		return situacao_;
	}
	
	public String getUsuario(){
		return nomeUsuario_;
	}
	
	void emprestarLivro(String nomeUsuario){
		situacao_ = SituacaoEnum.RETIRADO;
		nomeUsuario_ = nomeUsuario;
	}

	public void devolvido() {
		situacao_ = SituacaoEnum.DISPONIVEL;
		nomeUsuario_ = null;
	}
}
