package me.fsn.filipe.refactoring.model;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class Conta {

    private long entrada;

    private long saida;

    private Veiculo veiculo;

    private Conta(long entrada, long saida, Veiculo veiculo) {
        this.entrada = entrada;
        this.saida = saida;
        this.veiculo = veiculo;
    }

    public static Conta of(long entrada, long saida, Veiculo veiculo) {
        checkArgument(entrada >= 0, "Hora de entrada nao pode ser negativa");
        checkArgument(saida >= 0, "Hora de saida nao pode ser negativa");
        checkArgument(entrada <= saida, "A hora de saida nao pode ser anterior a hora de entrada");
        checkNotNull(veiculo, "Veiculo nao pode ser nulo");
        return new Conta(entrada, saida, veiculo);
    }

    public Double gerarConta() {
        long periodoEmMinutos = obterPeriodoDeUtilizacaoEmMinutos();

        if (periodoEmMinutos <=30) {
            return veiculo.tarifaAteMeiaHora();
        } else if (periodoEmMinutos > 30 && periodoEmMinutos <= 60) {
            return veiculo.tarifaAteUmaHora();
        } else if (periodoEmMinutos > 60 && periodoEmMinutos / 60.0 <= 6) {
            // Obtém horas adicionais de utilização
            final long horas = (periodoEmMinutos - 1) / 60;
            return horas * veiculo.tarifaHoraAdicional() + veiculo.tarifaAteUmaHora();
        } else {
            return veiculo.tarifaDiaria();
        }
    }

    private long obterPeriodoDeUtilizacaoEmMinutos() {
        return (saida - entrada) / 1000 / 60;
    }
}
