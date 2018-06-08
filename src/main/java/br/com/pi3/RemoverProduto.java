
package br.com.pi3;

import br.com.pi3.Classes.ItemCarrinho;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "RemoverProduto", urlPatterns = {"/RemoverProduto"})
public class RemoverProduto extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idTemp = request.getParameter("id");
        int idProduto = Integer.parseInt(idTemp);
        HttpSession session = request.getSession();
        ArrayList<ItemCarrinho> carrinho = 
                (ArrayList<ItemCarrinho>) session.getAttribute("carrinho");
        for (int i = 0; i < carrinho.size(); i++) {
            if (carrinho.get(i).getProduto().getId() == idProduto) {
                carrinho.remove(i);
            }
        }
        response.sendRedirect("/pi3-1.0-SNAPSHOT/MostrarCarrinho");
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
