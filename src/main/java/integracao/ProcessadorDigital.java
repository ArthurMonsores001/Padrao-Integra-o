package integracao;

public class ProcessadorDigital extends ProcessadorPedido {

    public ProcessadorDigital(ValidadorPedido cadeia, EstrategiaFrete estrategiaFrete) {
        super(cadeia, estrategiaFrete);
    }

    @Override
    protected boolean cobraFrete() {
        return false;
    }

    @Override
    protected void notificar(Pedido pedido) {
        pedido.registrarNotificacao("Pedido de " + pedido.getCliente()
                + " confirmado. Link de download enviado por e-mail. Total: " + pedido.getTotal());
    }
}
