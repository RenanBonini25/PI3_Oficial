package br.com.pi3;

import br.com.pi3.Classes.Console;
import br.com.pi3.Classes.ServicoProduto;
import br.com.pi3.DAO.DAOConsole;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CadastrarConsole", urlPatterns = {"/CadastrarConsole"})
public class CadastrarConsole extends HttpServlet {

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
        String fornecedor = request.getParameter("txtFornecedor");
        String cor = request.getParameter("txtCor");
        String tipo = "Console";

        Console console = new Console(nome, quantidade, precoCompra, precoVenda, tipo, descricao, fornecedor, cor);

        //DAOConsole.incluir(console);
        try {
            ServicoProduto.cadastrarProduto(console);
        } catch (Exception ex) {

        }

        response.sendRedirect("/pi3-1.0-SNAPSHOT/ListagemConsoles");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
