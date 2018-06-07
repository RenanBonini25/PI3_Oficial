package br.com.pi3.Classes;

public class ActionFigure extends Produto {

    String descricao;
    String fabricante;
    float tamanho;
    String cor;

    public ActionFigure() {
    }

    ;
    
    public ActionFigure(String nome, int quantidade, double precoCompra, double precoVenda,
            String descricao, String fabricante, float tamanho, String cor) {
        super(nome, quantidade, precoCompra, precoVenda);
        this.descricao = descricao;
        this.fabricante = fabricante;
        this.tamanho = tamanho;
        this.cor = cor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public float getTamanho() {
        return tamanho;
    }

    public void setTamanho(float tamanho) {
        this.tamanho = tamanho;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

}
