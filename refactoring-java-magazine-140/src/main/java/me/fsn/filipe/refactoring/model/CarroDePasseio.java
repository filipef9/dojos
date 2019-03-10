package me.fsn.filipe.refactoring.model;

public class CarroDePasseio extends Veiculo {

    private static final double TRES_REAIS = 3.0;
    private static final double CINCO_REAIS = 5.0;
    private static final double OITO_REAIS = 8.0;
    private static final double VINTE_REAIS = 20.0;

    private CarroDePasseio(final String placa, final String modelo) {
        super(placa, modelo);
    }

    public static Veiculo of(final String placa, final String modelo) {
        checkPlaca(placa);
        checkModelo(modelo);
        return new CarroDePasseio(placa, modelo);
    }

    public double tarifaAteMeiaHora() {
        return CINCO_REAIS;
    }

    public double tarifaAteUmaHora() {
        return OITO_REAIS;
    }

    public double tarifaHoraAdicional() {
        return TRES_REAIS;
    }

    public double tarifaDiaria() {
        return VINTE_REAIS;
    }

}
