
package br.com.pi3.Classes;

import br.com.pi3.DAO.DAOVenda;

public class ServicoVenda {
    
    public static void fazerVenda(Venda venda){
        //verifica a integridade dos dados da venda
        //ValidadorVenda.validar(venda);
        DAOVenda dao = new DAOVenda();
        try {
            //armazena o id(chave primaria) da venda realizada
            int idVenda = dao.incluirVenda(venda);
            //solicita ao DAO a insercao dos itens de venda na venda, usando o id retornado anteriormente
                //e solicita ao DAO o decremento das quantidades vendidas dos instrumentos no estoque
            for (int i = 0; i < venda.getCarrinho().size(); i++) {
                ItemCarrinho itemCarrinho = venda.getCarrinho().get(i);
                dao.incluirItemCarrinho(itemCarrinho, idVenda);
                //dao.decrementoEstoque(venda);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
