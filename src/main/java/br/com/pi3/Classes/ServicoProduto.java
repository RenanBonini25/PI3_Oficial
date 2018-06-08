package br.com.pi3.Classes;

import br.com.pi3.DAO.DAOProduto;

public class ServicoProduto {

    public static void cadastrarProduto(Produto produto) {
        DAOProduto dao = new DAOProduto();
        try {
            int idProduto = dao.incluir(produto);
        } catch (Exception ex) {

        }
    }

    public static void atualizarProduto(Produto produto) {
        DAOProduto dao = new DAOProduto();
        try {
            dao.atualizar(produto);
        } catch (Exception ex) {

        }
    }

    public static Produto obter(int id) {
        DAOProduto dao = new DAOProduto();
        Produto produto;

        produto = dao.obterIndiv(id);

        return produto;
    }

}
