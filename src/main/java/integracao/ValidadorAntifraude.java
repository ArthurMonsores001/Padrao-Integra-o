package integracao;

public class ValidadorAntifraude extends ValidadorPedido {

    private static final double LIMITE_CLIENTE_NOVO = 5000.0;

    @Override
    protected boolean verificar(Pedido pedido) {
        if (pedido.isClienteNovo() && pedido.getValorProdutos() > LIMITE_CLIENTE_NOVO) {
            pedido.rejeitar("Possível fraude");
            return false;
        }
        return true;
    }
}
