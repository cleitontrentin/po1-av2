import java.util.ArrayList;
import java.util.List;

public class Rodada {

	private List<Jogada> jogadas;
	private Carta cartaCoringa;
	private Jogada melhorJogada;

	public Rodada(Carta carta) {
		this.cartaCoringa = carta;
		this.jogadas = new ArrayList<>();
		this.melhorJogada = new Jogada(null, null);
	}

	public void adicionarJogada(Jogada jogada) {
		this.jogadas.add(jogada);
		verificarJogada();
	}

	public void atribuirPontos() {
		melhorJogada.getJogador().aumentarPontos();
	}

	private void verificarJogada() {
		for (Jogada jogada : this.jogadas) {
			if (isMaiorCarta(this.melhorJogada.getCarta(), jogada.getCarta())) {
				this.melhorJogada = jogada;
			}
		}
	}

	private boolean isMaiorCarta(Carta melhorCarta, Carta novaCarta) {
		if (melhorCarta == null) {
			return true;
		}
		int valorMelhorCarta = EnumCarta.TRUCO.pegarValorReal(melhorCarta);
		int valorNovaCarta = EnumCarta.TRUCO.pegarValorReal(novaCarta);
		if (valorNovaCarta > valorMelhorCarta) {
			return true;
		} else if (valorNovaCarta == valorMelhorCarta) {
			if (novaCarta.getNipe() > melhorCarta.getNipe()) {
				return true;
			}
		}
		return false;
	}

	public List<Jogada> getJogadas() {
		return jogadas;
	}

	public void setJogadas(List<Jogada> jogadas) {
		this.jogadas = jogadas;
	}

	public Carta getCartaCoringa() {
		return cartaCoringa;
	}

	public void setCartaCoringa(Carta cartaCoringa) {
		this.cartaCoringa = cartaCoringa;
	}

	public Jogada getMelhorJogada() {
		return melhorJogada;
	}

	public void setMelhorJogada(Jogada melhorJogada) {
		this.melhorJogada = melhorJogada;
	}

}