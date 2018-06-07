package br.com.pi3.Classes;

import java.util.ArrayList;

public class Venda {
    
    private Cliente cliente;
    private ArrayList<ItemCarrinho> carrinho = new ArrayList();
    private double total;
    private String dataVenda;
    private int id;


    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ArrayList<ItemCarrinho> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(ArrayList<ItemCarrinho> carrinho) {
        this.carrinho = carrinho;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(String dataVenda) {
        this.dataVenda = dataVenda;
    }

}
