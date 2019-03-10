package me.fsn.filipe.refactoring.model;

public class Caminhonete extends Veiculo {

    private static final double CINCO_REAIS = 5.0;
    private static final double SETE_REAIS = 7.0;
    private static final double DEZ_REAIS = 10.0;
    private static final double TRINTA_REAIS = 30.0;

    private Caminhonete(final String placa, final String modelo) {
        super(placa, modelo);
    }

    public static Veiculo of(final String placa, final String modelo) {
        checkPlaca(placa);
        checkModelo(modelo);
        return new Caminhonete(placa, modelo);
    }

    public double tarifaAteMeiaHora() {
        return SETE_REAIS;
    }

    public double tarifaAteUmaHora() {
        return DEZ_REAIS;
    }

    public double tarifaHoraAdicional() {
        return CINCO_REAIS;
    }

    public double tarifaDiaria() {
        return TRINTA_REAIS;
    }
}
