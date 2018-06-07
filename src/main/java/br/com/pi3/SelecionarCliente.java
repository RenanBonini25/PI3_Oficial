
package br.com.pi3;

import br.com.pi3.Classes.ItemCarrinho;
import br.com.pi3.DAO.DAOCliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SelecionarCliente", urlPatterns = {"/SelecionarCliente"})
public class SelecionarCliente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idTemp = request.getParameter("id");
        int id = Integer.parseInt(idTemp);
        
        HttpSession session = request.getSession();
        session.setAttribute("cliente", DAOCliente.obterClienteIndiv(id));
        ArrayList<ItemCarrinho> carrinho = new ArrayList<>();
        session.setAttribute("carrinho", carrinho);
        
        response.sendRedirect("Vendas_ProcurarProduto.jsp");
        
//        request.setAttribute("cliente", DAOCliente.obterCliente(id));
//        RequestDispatcher rd = request.getRequestDispatcher("Vendas.jsp");
//        try {
//            rd.forward(request, response);
//        } catch (ServletException | IOException ex) {
//            Logger.getLogger(EditarCliente.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
