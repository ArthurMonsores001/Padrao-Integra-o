package integracao;

public class ValidadorPagamento extends ValidadorPedido {

    @Override
    protected boolean verificar(Pedido pedido) {
        if (pedido.getSaldoCliente() < pedido.getValorProdutos()) {
            pedido.rejeitar("Saldo insuficiente");
            return false;
        }
        return true;
    }
}
