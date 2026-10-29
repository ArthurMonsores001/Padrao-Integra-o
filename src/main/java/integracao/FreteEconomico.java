package integracao;

public class FreteEconomico implements EstrategiaFrete {

    @Override
    public double calcular(Pedido pedido) {
        return Math.max(pedido.getValorProdutos() * 0.05, 10.0);
    }
}
