package br.com.pi3;

import br.com.pi3.Classes.CategoriaGame;
import br.com.pi3.Classes.Game;
import br.com.pi3.Classes.ServicoGame;
import br.com.pi3.Classes.ServicoProduto;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CadastrarGame", urlPatterns = {"/CadastrarGame"})
public class CadastrarGame extends HttpServlet {

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
        Game game = new Game();

        String nome = request.getParameter("txtNome");
        String desenv = request.getParameter("txtDesenvolvedora");
        String indicClass = request.getParameter("txtClassificacao");
        String plataforma = request.getParameter("Plataforma");
        String compra = request.getParameter("txtPrecoCompra");
        String categoria = request.getParameter("txtCategoria");
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

        try {
            ServicoProduto.cadastrarProduto(game);
            //ServicoGame.cadastrarGame(game, categorias);
        } catch (Exception ex) {

        }

        response.sendRedirect("/pi3-1.0-SNAPSHOT/ListagemGames");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
