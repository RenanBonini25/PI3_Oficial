package br.com.pi3;

import br.com.pi3.Classes.Game;
import br.com.pi3.Classes.ItemCarrinho;
import br.com.pi3.Classes.Produto;
import br.com.pi3.Classes.ServicoGame;
import br.com.pi3.Classes.ServicoProduto;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "AdicionarProduto", urlPatterns = {"/AdicionarProduto"})
public class AdicionarProduto extends HttpServlet {

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
        DecimalFormat df = new DecimalFormat("#0.00");
        String idTemp = request.getParameter("id");
        int id = Integer.parseInt(idTemp);
        Produto produto = ServicoProduto.obter(id);
        String qtdTemp = request.getParameter("qtdProduto");
        int qtd = Integer.parseInt(qtdTemp);
        int estoque = produto.getQuantidade();
        if (qtd <= estoque) {
            double subtotal = produto.getPrecoVenda() * qtd;
            String sub = df.format(subtotal).replaceAll(",", ".");
            double subAtual = Double.parseDouble(sub);

            ItemCarrinho itemCarrinho = new ItemCarrinho();
            itemCarrinho.setProduto(produto);
            itemCarrinho.setQuantidade(qtd);
            itemCarrinho.setSubtotal(subAtual);
            HttpSession session = request.getSession();
            ArrayList<ItemCarrinho> carrinho
                    = (ArrayList<ItemCarrinho>) session.getAttribute("carrinho");

            carrinho.add(itemCarrinho);
            response.sendRedirect("/pi3-1.0-SNAPSHOT/MostrarCarrinho");
        } else {
            response.sendRedirect("/pi3-1.0-SNAPSHOT/ErroEstoque.jsp");
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
