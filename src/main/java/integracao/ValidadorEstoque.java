package integracao;

public class ValidadorEstoque extends ValidadorPedido {

    @Override
    protected boolean verificar(Pedido pedido) {
        if (pedido.getQuantidade() > pedido.getEstoqueDisponivel()) {
            pedido.rejeitar("Estoque insuficiente");
            return false;
        }
        return true;
    }
}
