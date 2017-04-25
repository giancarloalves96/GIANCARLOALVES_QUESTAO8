package codigo;

public class Livro {

	private String nome_;
	private SituacaoLivroEnum situacao_;
	private String nomeUsuario_;
	
	public Livro(String nome){
		nome_ = nome;
		situacao_ = SituacaoLivroEnum.DISPONIVEL;
	}

	public String getNome() {
		return nome_;
	}
	
	public SituacaoLivroEnum getSituacao(){
		return situacao_;
	}
	
	public String getUsuario(){
		return nomeUsuario_;
	}
	
	void emprestarLivro(String nomeUsuario){
		situacao_ = SituacaoLivroEnum.RETIRADO;
		nomeUsuario_ = nomeUsuario;
	}

	public void devolvido() {
		situacao_ = SituacaoLivroEnum.DISPONIVEL;
		nomeUsuario_ = null;
	}
}
