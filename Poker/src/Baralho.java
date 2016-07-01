import java.util.List;

public class Baralho {

	private List<Carta> baralho;

	public Baralho() {
		this.baralho = EnumCarta.TRUCO.pegarCartas();
	}

	public Carta pegarCarta(Integer random) {
		Carta cartaSorteada = new Carta(1);
		if (random >= 0 && random < baralho.size()-1) {
			cartaSorteada = baralho.get(random);
			baralho.remove(cartaSorteada);
		}
		return cartaSorteada;
	}

	public int getTotalCartas() {
		return baralho.size();
	}

}
