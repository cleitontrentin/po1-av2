
public class Naipe {

	private String descricao;
	private Integer numero;
	
	public Naipe(String descricao, Integer numero) {
		this.descricao = descricao;
		this.numero = numero;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

}
