package integracao;

public abstract class ValidadorPedido {

    private ValidadorPedido proximo;

    public ValidadorPedido definirProximo(ValidadorPedido proximo) {
        this.proximo = proximo;
        return proximo;
    }

    public final boolean validar(Pedido pedido) {
        if (!verificar(pedido)) {
            return false;
        }
        return proximo == null || proximo.validar(pedido);
    }

    protected abstract boolean verificar(Pedido pedido);
}
