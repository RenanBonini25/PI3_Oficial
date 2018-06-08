/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi3.Classes;

import br.com.pi3.DAO.DAOProduto;
import br.com.pi3.DAO.DAOVenda;
import java.util.ArrayList;

/**
 *
 * @author renan
 */
public class teste {
    public static void main(String[] args) {
        ArrayList<Venda> lista = DAOVenda.gerarRelatorio("2018-06-08", "2018-06-08");
        lista = DAOVenda.gerarRelatorioItems(lista, "2018-06-08", "2018-06-08");
        for (Venda venda : lista) {
            System.out.println(venda.getCarrinho().get(0).getQuantidade());
        }
    }
}
