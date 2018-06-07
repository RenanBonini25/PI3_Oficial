package br.com.pi3;

import br.com.pi3.Classes.Acessorios;
import br.com.pi3.DAO.DAOAcessorio;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "CadastrarAcessorio", urlPatterns = {"/CadastrarAcessorio"})
public class CadastrarAcessorio extends HttpServlet {

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
            double precoCompra = Double.parseDouble(request.getParameter("txtPrecoCompra"));
            double precoVenda = Double.parseDouble(request.getParameter("txtPrecoVenda"));
            String descricao = request.getParameter("txtDescricao");
            String plataforma = request.getParameter("txtPlataforma");
            String cor = request.getParameter("txtCor");
            
            Acessorios acessorio = new Acessorios (nome, quantidade, precoCompra, precoVenda, descricao, plataforma, cor);
            
        try {
            DAOAcessorio.incluir(acessorio);
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarAcessorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastrarAcessorio.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            response.sendRedirect("/pi3-1.0-SNAPSHOT/acessorios.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
