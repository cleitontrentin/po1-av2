import java.util.ArrayList;
import java.util.List;

public class Time {

	private List<Jogador> jogadores;
	private Integer ponto;
	
	public Time() {
		jogadores = new ArrayList<>();
	}
	
	public void adicionarJogador(Jogador jogador) {
		if (jogadores.size() > 2) 
			return;
		jogadores.add(jogador);
	}

	public Integer getPonto() {
		return ponto;
	}

	public void setPonto(Integer ponto) {
		this.ponto = ponto;
	}
	
	
	
}
