package me.fsn.filipe.refactoring.model;

import me.fsn.filipe.refactoring.model.factories.CarroDePasseioFactory;
import me.fsn.filipe.refactoring.model.factories.VeiculoFactory;
import org.junit.Test;

import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class CarroDePasseioTest {

    private static final String MODELO = "DeLorean DMC-12";
    private static final String MODELO_NULO = null;
    private static final String PLACA = "AAA-1111";
    private static final String PLACA_NULA = null;

    private static final VeiculoFactory FABRICA_CARRO_DE_PASSEIO = new CarroDePasseioFactory();

    private static final String DEVERIA_TER_LANCADO_UMA_EXCECAO = "Deveria ter lançado uma exceção";

    private Veiculo veiculo;

    @Test
    public void nao_deve_permitir_criacao_de_carro_de_passeio_com_placa_nula() {
        try {
            veiculo = FABRICA_CARRO_DE_PASSEIO.criarVeiculo(PLACA_NULA, MODELO);
            fail(DEVERIA_TER_LANCADO_UMA_EXCECAO);
        } catch (NullPointerException e) {
            assertThat(e.getMessage(), equalToIgnoringCase("A placa não foi definida"));
        }
    }

    @Test
    public void nao_deve_permitir_criacao_de_carro_de_passeio_com_modelo_nulo() {
        try {
            veiculo = FABRICA_CARRO_DE_PASSEIO.criarVeiculo(PLACA, MODELO_NULO);
            fail(DEVERIA_TER_LANCADO_UMA_EXCECAO);
        } catch (NullPointerException e) {
            assertThat(e.getMessage(), equalToIgnoringCase("O modelo não foi definido"));
        }
    }
}