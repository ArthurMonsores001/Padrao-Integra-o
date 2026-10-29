package integracao;

public class ProcessadorAssinatura extends ProcessadorPedido {

    public ProcessadorAssinatura(ValidadorPedido cadeia, EstrategiaFrete estrategiaFrete) {
        super(cadeia, estrategiaFrete);
    }

    @Override
    protected void notificar(Pedido pedido) {
        pedido.registrarNotificacao("Assinatura de " + pedido.getCliente()
                + " ativada. Cobrança mensal de " + pedido.getTotal());
    }
}
