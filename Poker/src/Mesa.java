import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Mesa {

	private Partida partida;
	
	public void iniciarPartida() {
		this.partida = new Partida();
		this.partida.adicionarJogadores(adicionarJogadores());
		this.partida.jogar();
	}

	private List<Jogador> adicionarJogadores() {
		List<Jogador> jogadores = new ArrayList<>();
		boolean continuarAdicionando;
		do {
			Jogador jogador = new Jogador();
			jogador.setNome(JOptionPane.showInputDialog("Digite seu nome"));
			jogadores.add(jogador);
			continuarAdicionando = JOptionPane.showConfirmDialog(null, "Deseja mais jogadores?") == 1 ? true : false;
		} while (!continuarAdicionando);
		return jogadores;
	}
}
