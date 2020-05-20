package challenge;

// GitHub: MarceloSouza1983		e-mail: map_souza1983@gmail.com		Cel: (12) 98813-6040

import java.util.ArrayList;
import java.util.List;

public class Estacionamento {

	private final Integer numeroVagas = 10;
	private List<Carro> carroLista = new ArrayList<>();

	public void estacionar(Carro carro) {

		if (carrosEstacionados() < numeroVagas) {
			if (carro.getMotorista() == null) {
				throw new EstacionamentoException("Carros autônomos não são permitidos!");
			}
			if (carro.getMotorista().getIdade() < 18) {
				throw new EstacionamentoException("Motorista não pode ser menor de idade.");
			}
			if (carro.getMotorista().getPontos() > 20) {
				throw new EstacionamentoException("A habilitação não pode estar suspensa!");
			}

			carroLista.add(carro);

		} else if (carrosEstacionados() == numeroVagas) {
			Carro carroDeveSair = carroLista.stream()
					.filter(x -> x.getMotorista().getIdade() < 55)
					.findFirst()
					.orElseThrow(() -> new EstacionamentoException("Todos os motoristas são sêniors"));
			carroLista.remove(carroDeveSair);
			carroLista.add(carro);
		}

	} 

	public int carrosEstacionados() {
		return carroLista.size();
	}

	public boolean carroEstacionado(Carro carro) {
		return carroLista.contains(carro);
	}
}
