package integracao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProcessadorPedidoTest {

    private static final double DELTA = 0.001;

    private ValidadorPedido cadeia;

    @BeforeEach
    void montarCadeia() {
        cadeia = new ValidadorEstoque();
        cadeia.definirProximo(new ValidadorPagamento())
              .definirProximo(new ValidadorAntifraude());
    }

    @Test
    void pedidoValidoPassaPelaCorrenteInteira() {
        Pedido pedido = new Pedido("Ana", 200.0, 2, 10, 500.0, false);
        ProcessadorPedido processador = new ProcessadorFisico(cadeia, new FreteEconomico());

        assertTrue(processador.processar(pedido));
        assertEquals("CONFIRMADO", pedido.getStatus());
        assertEquals(10.0, pedido.getFrete(), DELTA);
        assertEquals(210.0, pedido.getTotal(), DELTA);
    }

    @Test
    void pedidoEhRejeitadoNaUltimaEtapaDaCorrente() {
        Pedido pedido = new Pedido("Bruno", 6000.0, 1, 5, 10000.0, true);
        ProcessadorPedido processador = new ProcessadorFisico(cadeia, new FreteEconomico());

        assertFalse(processador.processar(pedido));
        assertEquals("REJEITADO", pedido.getStatus());
        assertEquals("Possível fraude", pedido.getMotivoRejeicao());
        assertEquals(0.0, pedido.getFrete(), DELTA);
    }

    @Test
    void estrategiaDeFreteTrocadaEmTempoDeExecucao() {
        ProcessadorPedido processador = new ProcessadorFisico(cadeia, new FreteEconomico());
        Pedido primeiro = new Pedido("Carla", 1000.0, 1, 10, 5000.0, false);
        Pedido segundo = new Pedido("Carla", 1000.0, 1, 10, 5000.0, false);

        processador.processar(primeiro);
        processador.definirEstrategiaFrete(new FreteExpresso());
        processador.processar(segundo);

        assertEquals(50.0, primeiro.getFrete(), DELTA);
        assertEquals(150.0, segundo.getFrete(), DELTA);
    }

    @Test
    void processadorDigitalIgnoraFreteMesmoComEstrategia() {
        Pedido pedido = new Pedido("Diego", 300.0, 1, 10, 500.0, false);
        ProcessadorPedido processador = new ProcessadorDigital(cadeia, new FreteExpresso());

        assertTrue(processador.processar(pedido));
        assertEquals(0.0, pedido.getFrete(), DELTA);
        assertEquals(300.0, pedido.getTotal(), DELTA);
        assertTrue(pedido.getNotificacao().contains("download"));
    }

    @Test
    void processadorAssinaturaVariaApenasANotificacao() {
        Pedido pedido = new Pedido("Elisa", 100.0, 1, 10, 500.0, false);
        ProcessadorPedido processador = new ProcessadorAssinatura(cadeia, new FreteRetirada());

        assertTrue(processador.processar(pedido));
        assertEquals("CONFIRMADO", pedido.getStatus());
        assertTrue(pedido.getNotificacao().contains("mensal"));
    }

    @Test
    void integracaoDosTresPadroesNoMesmoProcessador() {
        ProcessadorPedido processador = new ProcessadorFisico(cadeia, new FreteRetirada());
        Pedido aprovado = new Pedido("Fabio", 1000.0, 1, 10, 5000.0, false);
        Pedido semEstoque = new Pedido("Gina", 1000.0, 20, 10, 5000.0, false);

        processador.definirEstrategiaFrete(new FreteExpresso());

        assertTrue(processador.processar(aprovado));
        assertEquals(1150.0, aprovado.getTotal(), DELTA);
        assertFalse(processador.processar(semEstoque));
        assertEquals("Estoque insuficiente", semEstoque.getMotivoRejeicao());
        assertEquals(1000.0, semEstoque.getTotal(), DELTA);
    }
}
