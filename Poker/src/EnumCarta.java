import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public enum EnumCarta {

	TRUCO {
		@Override
		public int sortearNumeroCarta(int totalCartas) {
			int numero = ThreadLocalRandom.current().nextInt(0, totalCartas + 1);
			if (numero > 7 && numero < 10) {
				numero = sortearNumeroCarta(totalCartas);
			}
			return numero;
		}
		
		@Override
		public int sortearNumeroNaipe() {
			return ThreadLocalRandom.current().nextInt(0, 3 + 1);
		}

		@Override
		public int pegarValorReal(Carta carta) {
			for (int i = 0; i < sequencia.length; i++) {
				if (sequencia[i].equals(String.valueOf(carta.getNumero()))) {
					return i;
				}
			}
			return -1;
		}

		@Override
		public List<Carta> pegarCartas() {
			List<Carta> cartas = new ArrayList<>();
			List<Naipe> naipes = new ArrayList<>();
			
			naipes.add(new Naipe("mole", 0));
			naipes.add(new Naipe("espada", 1));
			naipes.add(new Naipe("copa", 2));
			naipes.add(new Naipe("gato", 3));
			
			for (int i = 0; i < sequencia.length; i++) {
				Carta cartaMole = new Carta(Integer.parseInt(sequencia[i]), naipes.get(0));
				Carta cartaEspada = new Carta(Integer.parseInt(sequencia[i]), naipes.get(1));
				Carta cartaCopa = new Carta(Integer.parseInt(sequencia[i]), naipes.get(2));
				Carta cartaPaus = new Carta(Integer.parseInt(sequencia[i]), naipes.get(3));

				cartas.add(cartaMole);
				cartas.add(cartaEspada);
				cartas.add(cartaCopa);
				cartas.add(cartaPaus);
			}
			return cartas;
		}

	};

	public abstract int sortearNumeroCarta(int totalCartas);
	public abstract int sortearNumeroNaipe();

	public abstract List<Carta> pegarCartas();

	public abstract int pegarValorReal(Carta carta);

	private final static String[] sequencia = new String[] { "4", "5", "6", "7", "10", "11", "12", "13", "1", "2",
			"3" };
}
