package me.fsn.filipe.refactoring;

import org.junit.Test;

import static org.hamcrest.text.IsEqualIgnoringCase.equalToIgnoringCase;
import static org.junit.Assert.*;

public class VeiculoTest {

    private final String DEVERIA_TER_LANCADO_UMA_EXCECAO = "Deveria ter lançado uma exceção";

    private Veiculo veiculo;

    @Test
    public void nao_deve_permitir_criacao_de_veiculo_com_placa_nula() {
        try {
            veiculo = Veiculo.of(null, "DeLorean DMC-12", TipoVeiculo.CARRO_PASSEIO);
            fail(DEVERIA_TER_LANCADO_UMA_EXCECAO);
        } catch (NullPointerException e) {
            assertThat(e.getMessage(), equalToIgnoringCase("A placa não foi definida"));
        }
    }

    @Test
    public void nao_deve_permitir_criacao_de_veiculo_com_modelo_nulo() {
        try {
            veiculo = Veiculo.of("AAA-1111", null, TipoVeiculo.CARRO_PASSEIO);
            fail(DEVERIA_TER_LANCADO_UMA_EXCECAO);
        } catch (NullPointerException e) {
            assertThat(e.getMessage(), equalToIgnoringCase("O modelo não foi definido"));
        }
    }

    @Test
    public void nao_deve_permitir_criacao_de_veiculo_com_tipo_veiculo_nulo() {
       try {
           veiculo = Veiculo.of("AAA-1111", "DeLorean DMC-12", null);
           fail(DEVERIA_TER_LANCADO_UMA_EXCECAO);
       } catch (NullPointerException e) {
           assertThat(e.getMessage(), equalToIgnoringCase("O tipo do veículo não foi definido"));
       }
    }

}