/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi3.Classes;

import br.com.pi3.DAO.DAOProduto;
import java.util.ArrayList;

/**
 *
 * @author renan
 */
public class teste {
    public static void main(String[] args) {
        ArrayList<Produto> lista = DAOProduto.listar();
        for (Produto produto : lista) {
            System.out.println(produto.getNome());
        }
    }
}
