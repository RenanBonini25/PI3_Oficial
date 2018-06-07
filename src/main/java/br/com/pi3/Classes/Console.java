package br.com.pi3.Classes;

public class Console extends Produto {

    String descricao;
    String fornecedor;
    String cor;

    public Console() {
    }

    ;
    
    public Console(String nome, int quantidade, double precoCompra, double precoVenda,
            String descricao, String fornecedor, String cor) {
        super(nome, quantidade, precoCompra, precoVenda);
        this.descricao = descricao;
        this.fornecedor = fornecedor;
        this.cor = cor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(String fornecedor) {
        this.fornecedor = fornecedor;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
