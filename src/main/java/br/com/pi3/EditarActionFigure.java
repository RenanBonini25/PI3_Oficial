package br.com.pi3;

import br.com.pi3.Classes.ActionFigure;
import br.com.pi3.DAO.DAOActionFigure;
import br.com.pi3.DAO.DAOProduto;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EditarActionFigure", urlPatterns = {"/EditarActionFigure"})
public class EditarActionFigure extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String idTemp = request.getParameter("id");
        int id = Integer.parseInt(idTemp);
        request.setAttribute("actFig", DAOProduto.obter(id));
        RequestDispatcher rd = request.getRequestDispatcher("EditarActionFigure.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(EditarActionFigure.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idTemp = request.getParameter("id");
        int id = Integer.parseInt(idTemp);
        String nome = request.getParameter("txtNome");
        int quantidade = Integer.parseInt(request.getParameter("txtQuantidade"));
        double precoCompra = Double.parseDouble(request.getParameter("txtPrecoCompra"));
        double precoVenda = Double.parseDouble(request.getParameter("txtPrecoVenda"));
        String descricao = request.getParameter("txtDescricao");
        String fabricante = request.getParameter("txtFabricante");
        String tamanho = request.getParameter("txtTamanho");
        String cor = request.getParameter("txtCor");
        String tipo = "ActionFigure";
        ActionFigure action = new ActionFigure(nome, quantidade, precoCompra, precoVenda, tipo, descricao, fabricante, tamanho, cor);
        action.setId(id);
        try {
            DAOProduto.atualizar(action);
        } catch (Exception ex) {

        }

        response.sendRedirect("/pi3-1.0-SNAPSHOT/ListagemActionFigures");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
