package br.com.pi3;

import br.com.pi3.Classes.ActionFigure;
import br.com.pi3.Classes.ServicoProduto;
import br.com.pi3.DAO.DAOActionFigure;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "CadastrarActionFigure", urlPatterns = {"/CadastrarActionFigure"})
public class CadastrarActionFigure extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
          
            String nome = request.getParameter("txtNome");
            int quantidade = Integer.parseInt(request.getParameter("txtQuantidade"));
            double precoCompra = Double.parseDouble(request.getParameter("txtPrecoCompra").replaceAll(",", "."));
            double precoVenda = Double.parseDouble(request.getParameter("txtPrecoVenda").replaceAll(",", "."));
            String descricao = request.getParameter("txtDescricao");
            String fabricante = request.getParameter("txtFabricante");
            String tamanho = request.getParameter("txtTamanho");
            String cor = request.getParameter("txtCor");
            String tipo = "ActionFigure";
            
            ActionFigure action = new ActionFigure (nome, quantidade, precoCompra, precoVenda, tipo, descricao, fabricante, tamanho, cor);
            
        try {
            //DAOActionFigure.incluir(action);
            ServicoProduto.cadastrarProduto(action);
        } catch (Exception ex) {
            
        }
            
            
            response.sendRedirect("/pi3-1.0-SNAPSHOT/ListagemActionFigures");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
