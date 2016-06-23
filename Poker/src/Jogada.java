
public class Jogada {

	private Jogador jogador;
	private Carta carta;

	public Jogada(Jogador idInteger, Carta carta) {
		this.jogador = idInteger;
		this.carta = carta;
	}

	public Jogador getJogador() {
		return jogador;
	}

	public void setJogador(Jogador idJogador) {
		this.jogador = idJogador;
	}

	public Carta getCarta() {
		return carta;
	}

	public void setCarta(Carta carta) {
		this.carta = carta;
	}

}
