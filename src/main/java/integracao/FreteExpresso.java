package integracao;

public class FreteExpresso implements EstrategiaFrete {

    @Override
    public double calcular(Pedido pedido) {
        return Math.max(pedido.getValorProdutos() * 0.15, 25.0);
    }
}
