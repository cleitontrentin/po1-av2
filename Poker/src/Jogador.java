import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JOptionPane;

public class Jogador {

	private Integer id;
	private String nome;
	private Mao mao;
	private Integer pontos;
	
	public Jogador() {
		mao = new Mao();
		id = ThreadLocalRandom.current().nextInt(1, 1000 + 1);
		pontos = 0;
	}
	
	public void aumentarPontos(){
		pontos ++;
	}

	public Integer getPontos() {
		return pontos;
	}
	
	public Integer getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Mao getMao() {
		return mao;
	}

	public void setMao(Mao mao) {
		this.mao = mao;
	}
	
	private String mostrarCartas() {
		StringBuilder builder = new StringBuilder();
		builder.append("Jogador: "+this.nome + "\n");
		this.mao.getCartas().forEach((carta) -> {
			builder.append(carta.toString() + "\n");
		});
		return builder.toString();
	}
	
	public Carta selecionarCarta() {
		int index = Integer.parseInt(JOptionPane.showInputDialog(mostrarCartas()));
		if (index < 0 || index > 2) {
			return selecionarCarta();
		}
		return this.mao.getCartas().get(index);
	}

}
