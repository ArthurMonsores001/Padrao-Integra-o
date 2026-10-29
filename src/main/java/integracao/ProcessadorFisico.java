package integracao;

public class ProcessadorFisico extends ProcessadorPedido {

    public ProcessadorFisico(ValidadorPedido cadeia, EstrategiaFrete estrategiaFrete) {
        super(cadeia, estrategiaFrete);
    }

    @Override
    protected void notificar(Pedido pedido) {
        pedido.registrarNotificacao("Pedido de " + pedido.getCliente()
                + " confirmado. Entrega pela transportadora. Total: " + pedido.getTotal());
    }
}
