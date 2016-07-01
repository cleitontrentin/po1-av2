import java.util.ArrayList;
import java.util.List;

public class Partida {

	private List<Jogador> jogadores;
	private List<Rodada> rodadas;
	private Baralho baralho;

	public Partida() {
		rodadas = new ArrayList<>();
		jogadores = new ArrayList<>();
		baralho = new Baralho();
	}

	private void darCartas() {
		jogadores.forEach((jogador) -> {
			jogador.getMao()
					.adicionarCarta(baralho.pegarCarta(EnumCarta.TRUCO.sortearNumeroCarta(baralho.getTotalCartas())));
			jogador.getMao()
					.adicionarCarta(baralho.pegarCarta(EnumCarta.TRUCO.sortearNumeroCarta(baralho.getTotalCartas())));
			jogador.getMao()
					.adicionarCarta(baralho.pegarCarta(EnumCarta.TRUCO.sortearNumeroCarta(baralho.getTotalCartas())));
		});
	}

	public void adicionarJogador(Jogador jogador) {
		this.jogadores.add(jogador);
	}

	public void adicionarJogadores(List<Jogador> jogadores) {
		this.jogadores.addAll(jogadores);
		this.darCartas();
	}

	private boolean checarSeAlguemGanhou() {
		for (Jogador jogador : this.jogadores) {
			if (jogador.getPontos() >= 12) {
				return true;
			}
		}
		return false;
	}

	public void jogar() {
		while (!checarSeAlguemGanhou()) {
			Rodada rodada = new Rodada(baralho.pegarCarta(EnumCarta.TRUCO.sortearNumeroCarta(baralho.getTotalCartas())));
			rodada.iniciar(this.jogadores);
			this.rodadas.add(rodada);
		}
	}
}
