package me.fsn.filipe.refactoring.model.factories;

import me.fsn.filipe.refactoring.model.Caminhonete;
import me.fsn.filipe.refactoring.model.Veiculo;

public class CaminhoneteFactory extends VeiculoFactory {

    public Veiculo criarVeiculo(final String placa, final String modelo) {
        return Caminhonete.of(placa, modelo);
    }

}
