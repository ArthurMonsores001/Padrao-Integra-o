package integracao;

public abstract class ProcessadorPedido {

    private final ValidadorPedido cadeia;
    private EstrategiaFrete estrategiaFrete;

    public ProcessadorPedido(ValidadorPedido cadeia, EstrategiaFrete estrategiaFrete) {
        this.cadeia = cadeia;
        this.estrategiaFrete = estrategiaFrete;
    }

    public void definirEstrategiaFrete(EstrategiaFrete estrategiaFrete) {
        this.estrategiaFrete = estrategiaFrete;
    }

    public final boolean processar(Pedido pedido) {
        if (!cadeia.validar(pedido)) {
            return false;
        }
        calcularValores(pedido);
        pedido.confirmar();
        notificar(pedido);
        return true;
    }

    private void calcularValores(Pedido pedido) {
        double frete = cobraFrete() ? estrategiaFrete.calcular(pedido) : 0.0;
        pedido.definirFrete(frete);
    }

    protected boolean cobraFrete() {
        return true;
    }

    protected abstract void notificar(Pedido pedido);
}
