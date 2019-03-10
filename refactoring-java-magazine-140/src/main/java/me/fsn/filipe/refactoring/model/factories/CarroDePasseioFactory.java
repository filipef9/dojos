package me.fsn.filipe.refactoring.model.factories;

import me.fsn.filipe.refactoring.model.CarroDePasseio;
import me.fsn.filipe.refactoring.model.Veiculo;

public class CarroDePasseioFactory extends VeiculoFactory {

    public Veiculo criarVeiculo(final String placa, final String modelo) {
        return CarroDePasseio.of(placa, modelo);
    }

}
