package me.fsn.filipe.refactoring.model;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class Veiculo {

    private final String placa;

    private final String modelo;

    protected Veiculo(String placa, String modelo) {
        this.placa = placa;
        this.modelo = modelo;
    }

    protected static void checkPlaca(final String placa) {
        checkNotNull(placa, "A placa não foi definida");
    }

    protected static void checkModelo(final String modelo) {
        checkNotNull(modelo, "O modelo não foi definido");
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public abstract double tarifaAteMeiaHora();

    public abstract double tarifaAteUmaHora();

    public abstract double tarifaHoraAdicional();

    public abstract double tarifaDiaria();

}
