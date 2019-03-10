package me.fsn.filipe.refactoring.model.factories;

import me.fsn.filipe.refactoring.model.Veiculo;

public abstract class VeiculoFactory {

    public abstract Veiculo criarVeiculo(final String placa, final String modelo);

}
