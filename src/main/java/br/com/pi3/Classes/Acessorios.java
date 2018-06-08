package br.com.pi3.Classes;

public class Acessorios extends Produto {

    String descricao;
    String plataforma;
    String cor;

    public Acessorios() {
    }

    ;
    
    public Acessorios(String nome, int quantidade, double precoCompra, double precoVenda,
            String tipo, String descricao, String plataforma, String cor) {
        super(nome, quantidade, precoCompra, precoVenda, tipo);
        this.descricao = descricao;
        this.plataforma = plataforma;
        this.cor = cor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

}
