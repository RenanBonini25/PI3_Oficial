package br.com.pi3;

import br.com.pi3.Classes.Acessorios;
import br.com.pi3.DAO.DAOAcessorio;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EditarAcessorio", urlPatterns = {"/EditarAcessorio"})
public class EditarAcessorio extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String idTemp = request.getParameter("id");
        int id = Integer.parseInt(idTemp);
        Acessorios acessorio = new Acessorios();
        try {
            request.setAttribute("obterAcessorio", DAOAcessorio.obterAcessorio(id));
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(EditarAcessorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher rd = request.getRequestDispatcher("EditarAcessorio.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(EditarCliente.class.getName()).log(Level.SEVERE, null, ex);
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
        String plataforma = request.getParameter("txtPlataforma");
        String cor = request.getParameter("txtCor");
        Acessorios acessorio = new Acessorios(nome, quantidade, precoCompra, precoVenda, descricao, plataforma, cor);
        acessorio.setId(id);
        try {
            DAOAcessorio.atualizarAcessorio(acessorio);
        } catch (Exception ex) {

        }

        response.sendRedirect("/pi3-1.0-SNAPSHOT/ListagemAcessorios");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
