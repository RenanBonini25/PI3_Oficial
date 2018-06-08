
package br.com.pi3;

import br.com.pi3.Classes.Game;
import br.com.pi3.DAO.DAOGame;
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

@WebServlet(name = "EditarGame", urlPatterns = {"/EditarGame"})
public class EditarGame extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idTemp = request.getParameter("id");
        int id = Integer.parseInt(idTemp);
        request.setAttribute("game", DAOProduto.obter(id));
        RequestDispatcher rd = request.getRequestDispatcher("EditarGame.jsp");
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
        
        Game game = new Game();
        String nome = request.getParameter("txtNome");
        
        String desenv = request.getParameter("txtDesenvolvedora");
        String indicClass = request.getParameter("txtClassificacao");
        String categoria = request.getParameter("txtCategoria");
        String plataforma = request.getParameter("Plataforma");
        String compra = request.getParameter("txtPrecoCompra");
        double precoCompra = Double.parseDouble(compra);
        String venda = request.getParameter("txtPrecoVenda");
        double precoVenda = Double.parseDouble(venda);
        String qtd = request.getParameter("txtQuantidade");
        int quantidade = Integer.parseInt(qtd);

        game.setNome(nome);
        game.setDesenvolvedora(desenv);
        game.setPlataforma(plataforma);
        game.setClassIndicativa(indicClass);
        game.setCategoria(categoria);
        game.setPrecoCompra(precoCompra);
        game.setPrecoVenda(precoVenda);
        game.setQuantidade(quantidade);
        game.setTipo("Game");
        game.setId(id);

        try {
            DAOProduto.atualizar(game);
        } catch (Exception ex) {

        }

        response.sendRedirect("/pi3-1.0-SNAPSHOT/ListagemGames");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
