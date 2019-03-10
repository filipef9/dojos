package me.fsn.filipe.refactoring.model;

import me.fsn.filipe.refactoring.model.factories.*;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class ContaTest {

    private final VeiculoFactory FABRICA_CARRO_DE_PASSEIO = new CarroDePasseioFactory();
    private final VeiculoFactory FABRICA_CAMINHONETE = new CaminhoneteFactory();

    private final Veiculo CARRO_DE_PASSEIO = FABRICA_CARRO_DE_PASSEIO.criarVeiculo("ABC-1234", "FIAT/PALIO");

    private final Veiculo CAMINHONETE = FABRICA_CAMINHONETE.criarVeiculo("DEF-5678", "FIAT/STRADA");

    private final Calendar DOZE_HORAS = new GregorianCalendar(2019, Calendar.FEBRUARY, 16, 12, 0);
    private final Calendar DOZE_HORAS_E_TRINTA_MINUTOS = new GregorianCalendar(2019, Calendar.FEBRUARY, 16, 12, 30);
    private final Calendar TREZE_HORAS = new GregorianCalendar(2019, Calendar.FEBRUARY, 16, 13, 0);
    private final Calendar DEZESSETE_HORAS = new GregorianCalendar(2019, Calendar.FEBRUARY, 16, 17, 0);
    private final Calendar DEZOITO_HORAS_E_TRINTA_MINUTOS = new GregorianCalendar(2019, Calendar.FEBRUARY, 16, 18, 30);

    private final String DEVERIA_TER_LANCADO_UMA_EXCECAO = "Deveria ter lançado uma exceção";
    private final int HORA_NEGATIVA = -1;

    private final double CINCO_REAIS = 5.00;
    private final double SETE_REAIS = 7.00;
    private final double OITO_REAIS = 8.00;
    private final double DEZ_REAIS = 10.00;
    private final double VINTE_REAIS = 20.00;
    private final double TRINTA_REAIS = 30.00;

    private Conta conta;

    @Test
    public void deve_saber_calcular_a_conta_de_um_carro_de_passeio_ate_meia_hora() {
        final long horaEntrada = DOZE_HORAS.getTimeInMillis();
        final long horaSaida = DOZE_HORAS_E_TRINTA_MINUTOS.getTimeInMillis();

        conta = Conta.of(horaEntrada, horaSaida, CARRO_DE_PASSEIO);
        assertThat(conta.gerarConta(), is(CINCO_REAIS));
    }

    @Test
    public void deve_saber_calcular_a_conta_de_um_carro_de_passeio_acima_de_trinta_minutos_ate_uma_hora() {
        final long horaEntrada = DOZE_HORAS.getTimeInMillis();
        final long horaSaida = TREZE_HORAS.getTimeInMillis();

        conta = Conta.of(horaEntrada, horaSaida, CARRO_DE_PASSEIO);
        assertThat(conta.gerarConta(), is(OITO_REAIS));
    }

    @Test
    public void deve_saber_calcular_a_conta_de_um_carro_de_passeio_acima_de_uma_hora_ate_seis_horas() {
        final long horaEntrada = DOZE_HORAS.getTimeInMillis();
        final long horaSaida = DEZESSETE_HORAS.getTimeInMillis();

        conta = Conta.of(horaEntrada, horaSaida, CARRO_DE_PASSEIO);
        assertThat(conta.gerarConta(), is(VINTE_REAIS));
    }

    @Test
    public void deve_saber_calcular_a_conta_de_um_carro_de_passeio_de_diaria() {
        final long horaEntrada = DOZE_HORAS.getTimeInMillis();
        final long horaSaida = DEZOITO_HORAS_E_TRINTA_MINUTOS.getTimeInMillis();

        conta = Conta.of(horaEntrada, horaSaida, CARRO_DE_PASSEIO);
        assertThat(conta.gerarConta(), is(VINTE_REAIS));
    }

    @Test
    public void deve_saber_calcular_a_conta_de_uma_caminhonete_ate_meia_hora() {
        final long horaEntrada = DOZE_HORAS.getTimeInMillis();
        final long horaSaida = DOZE_HORAS_E_TRINTA_MINUTOS.getTimeInMillis();

        conta = Conta.of(horaEntrada, horaSaida, CAMINHONETE);
        assertThat(conta.gerarConta(), is(SETE_REAIS));
    }

    @Test
    public void deve_saber_calcular_a_conta_de_uma_caminhonete_acima_de_trinta_minutos_ate_uma_hora() {
        final long horaEntrada = DOZE_HORAS.getTimeInMillis();
        final long horaSaida = TREZE_HORAS.getTimeInMillis();

        conta = Conta.of(horaEntrada, horaSaida, CAMINHONETE);
        assertThat(conta.gerarConta(), is(DEZ_REAIS));
    }

    @Test
    public void deve_saber_calcular_a_conta_de_uma_caminhonete_acima_de_uma_hora_ate_seis_horas() {
        final long horaEntrada = DOZE_HORAS.getTimeInMillis();
        final long horaSaida = DEZESSETE_HORAS.getTimeInMillis();

        conta = Conta.of(horaEntrada, horaSaida, CAMINHONETE);
        assertThat(conta.gerarConta(), is(TRINTA_REAIS));
    }

    @Test
    public void deve_saber_calcular_a_conta_de_uma_caminhonete_de_diaria() {
        final long horaEntrada = DOZE_HORAS.getTimeInMillis();
        final long horaSaida = DEZOITO_HORAS_E_TRINTA_MINUTOS.getTimeInMillis();

        conta = Conta.of(horaEntrada, horaSaida, CAMINHONETE);
        assertThat(conta.gerarConta(), is(TRINTA_REAIS));
    }

    @Test
    public void nao_deve_permitir_hora_entrada_com_valor_negativo() {
        try {
            final long horaEntrada = HORA_NEGATIVA;
            final long horaSaida = TREZE_HORAS.getTimeInMillis();

            conta = Conta.of(horaEntrada, horaSaida, CARRO_DE_PASSEIO);
            fail(DEVERIA_TER_LANCADO_UMA_EXCECAO);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), equalToIgnoringCase("Hora de entrada nao pode ser negativa"));
        }
    }

    @Test
    public void nao_deve_permitir_hora_saida_com_valor_negativo() {
        try {
            final long horaEntrada = DOZE_HORAS.getTimeInMillis();
            final long horaSaida = HORA_NEGATIVA;

            conta = Conta.of(horaEntrada, horaSaida, CARRO_DE_PASSEIO);
            fail(DEVERIA_TER_LANCADO_UMA_EXCECAO);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), equalToIgnoringCase("Hora de saida nao pode ser negativa"));
        }
    }

    @Test
    public void nao_deve_permitir_hora_saida_anterior_hora_entrada() {
        try {
            final long horaEntrada = TREZE_HORAS.getTimeInMillis();
            final long horaSaida = DOZE_HORAS.getTimeInMillis();

            conta =  Conta.of(horaEntrada, horaSaida, CARRO_DE_PASSEIO);
            fail(DEVERIA_TER_LANCADO_UMA_EXCECAO);
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), equalToIgnoringCase("A hora de saida nao pode ser anterior a hora de entrada"));
        }
    }

    @Test
    public void nao_deve_permitir_veiculo_nulo() {
        try {
            final long horaEntrada = DOZE_HORAS.getTimeInMillis();
            final long horaSaida = TREZE_HORAS.getTimeInMillis();

            conta = Conta.of(horaEntrada, horaSaida, null);
            fail(DEVERIA_TER_LANCADO_UMA_EXCECAO);
        } catch (NullPointerException e) {
            assertThat(e.getMessage(), equalToIgnoringCase("Veiculo nao pode ser nulo"));
        }
    }

}