import java.util.ArrayList;
import java.util.List;

public class Mao {

	private List<Carta> cartas;
	
	public Mao() {
		cartas = new ArrayList<>();
	}

	public void adicionarCarta(Carta carta) {
		cartas.add(carta);
	}

	public void removerCarta(Carta carta) {
		cartas.remove(carta);
	}
	
	public List<Carta> getCartas() {
		return cartas;
	}

}
