package integracao;

public class Pedido {

    private final String cliente;
    private final double valorProdutos;
    private final int quantidade;
    private final int estoqueDisponivel;
    private final double saldoCliente;
    private final boolean clienteNovo;
    private double frete;
    private double total;
    private String status = "NOVO";
    private String motivoRejeicao;
    private String notificacao;

    public Pedido(String cliente, double valorProdutos, int quantidade,
                  int estoqueDisponivel, double saldoCliente, boolean clienteNovo) {
        this.cliente = cliente;
        this.valorProdutos = valorProdutos;
        this.quantidade = quantidade;
        this.estoqueDisponivel = estoqueDisponivel;
        this.saldoCliente = saldoCliente;
        this.clienteNovo = clienteNovo;
        this.total = valorProdutos;
    }

    public void definirFrete(double frete) {
        this.frete = frete;
        this.total = valorProdutos + frete;
    }

    public void confirmar() {
        this.status = "CONFIRMADO";
    }

    public void rejeitar(String motivo) {
        this.status = "REJEITADO";
        this.motivoRejeicao = motivo;
    }

    public void registrarNotificacao(String mensagem) {
        this.notificacao = mensagem;
    }

    public String getCliente() { return cliente; }
    public double getValorProdutos() { return valorProdutos; }
    public int getQuantidade() { return quantidade; }
    public int getEstoqueDisponivel() { return estoqueDisponivel; }
    public double getSaldoCliente() { return saldoCliente; }
    public boolean isClienteNovo() { return clienteNovo; }
    public double getFrete() { return frete; }
    public double getTotal() { return total; }
    public String getStatus() { return status; }
    public String getMotivoRejeicao() { return motivoRejeicao; }
    public String getNotificacao() { return notificacao; }
}
