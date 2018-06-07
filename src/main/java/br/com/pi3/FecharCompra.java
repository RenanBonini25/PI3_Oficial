
package br.com.pi3;

import br.com.pi3.Classes.Cliente;
import br.com.pi3.Classes.ItemCarrinho;
import br.com.pi3.Classes.ServicoVenda;
import br.com.pi3.Classes.Venda;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "FecharCompra", urlPatterns = {"/FecharCompra"})
public class FecharCompra extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy"); 
        Venda venda = new Venda();
        HttpSession session = request.getSession();
        ArrayList<ItemCarrinho> carrinho = 
                (ArrayList<ItemCarrinho>) session.getAttribute("carrinho");
        Cliente cliente = (Cliente) session.getAttribute("cliente");
        double totalVenda = (double) session.getAttribute("totalVenda");
        venda.setCliente(cliente);
        venda.setCarrinho(carrinho);
        venda.setTotal(totalVenda);
        Date date = new Date();
        String data = df.format(date);
        venda.setDataVenda(data);
        session.setAttribute("venda", venda);
        ServicoVenda.fazerVenda(venda);
        
        response.sendRedirect("/pi3-1.0-SNAPSHOT/MostrarVenda.jsp");
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
