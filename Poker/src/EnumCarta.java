import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public enum EnumCarta {

	POKER {
		@Override
		public int pegarNumero() {
			return ThreadLocalRandom.current().nextInt(2, 14 + 1);
		}
		@Override
		public int pegarValorReal(Carta carta) {
			return -1;
		}
	},
	TRUCO {
		@Override
		public int pegarNumero() {
			int numero = ThreadLocalRandom.current().nextInt(1, 13 + 1);
			if (numero > 7 && numero < 10) {
				numero = pegarNumero();
			}
			return numero;
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

	};

	public abstract int pegarNumero();

	public abstract int pegarValorReal(Carta carta);

	public static Naipe pegarNaipe() {
		criarNaipes();
		return naipes.get(ThreadLocalRandom.current().nextInt(0, 3 + 1));
	};
	
	private static void criarNaipes() {
		if (!naipes.isEmpty()) return;
		
		naipes.add(new Naipe("moles", 0));
		naipes.add(new Naipe("espadilhas", 1));
		naipes.add(new Naipe("copas", 2));
		naipes.add(new Naipe("paus", 3));
	}

	//private final static Integer[] naipes = { 0, 1, 2, 3 };
	private final static List<Naipe> naipes = new ArrayList<>();
	private final static String[] sequencia = new String[] { "4", "5", "6", "7", "10", "11", "12", "13", "1", "2", "3" };
}
