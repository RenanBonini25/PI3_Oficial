
package br.com.pi3;

import br.com.pi3.Classes.ItemCarrinho;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "MostrarCarrinho", urlPatterns = {"/MostrarCarrinho"})
public class MostrarCarrinho extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DecimalFormat df = new DecimalFormat("#0.00");
        HttpSession session = request.getSession();
        ArrayList<ItemCarrinho> carrinho = 
                (ArrayList<ItemCarrinho>) session.getAttribute("carrinho");
        double total = 0;
        for (ItemCarrinho item : carrinho) {
            total += (item.getProduto().getPrecoVenda() * item.getQuantidade());
        }
        String totalTemp = df.format(total).replaceAll(",", ".");
        double totalFormat = Double.parseDouble(totalTemp);
        session.setAttribute("totalVenda", totalFormat);
        response.sendRedirect("Carrinho.jsp");
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
