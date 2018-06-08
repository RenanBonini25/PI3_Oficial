package br.com.pi3.Classes;

import java.util.ArrayList;

public class Game extends Produto {
    
    private String plataforma;
    private String desenvolvedora;
    private String classIndicativa;
    private String categoria;

    public Game() {
    }

    public Game(String nome, int quantidade, double precoCompra, double precoVenda, 
            String tipo, String plataforma, String desenvolvedora, String classIndicativa, String categoria) {
        super(nome, quantidade, precoCompra, precoVenda, tipo);
        this.plataforma = plataforma;
        this.desenvolvedora = desenvolvedora;
        this.classIndicativa = classIndicativa;
        this.categoria = categoria;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getDesenvolvedora() {
        return desenvolvedora;
    }

    public void setDesenvolvedora(String desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }

    public String getClassIndicativa() {
        return classIndicativa;
    }

    public void setClassIndicativa(String classIndicativa) {
        this.classIndicativa = classIndicativa;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
}
