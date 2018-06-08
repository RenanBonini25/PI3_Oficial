package br.com.pi3;

import br.com.pi3.Classes.Game;
import br.com.pi3.Classes.Produto;
import br.com.pi3.DAO.DAOGame;
import br.com.pi3.DAO.DAOProduto;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ListagemGames", urlPatterns = {"/ListagemGames"})
public class ListagemGames extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ArrayList<Game> listaGames = new ArrayList<>();
        ArrayList<Produto> listaProdutos = DAOProduto.listar();
        
        for (Produto produto : listaProdutos) {
            if (produto instanceof Game) {
                Game game = (Game) produto;
                listaGames.add(game);
            }
        }

        request.setAttribute("Listagem", listaGames);
        RequestDispatcher rd = request.getRequestDispatcher("ListagemGames.jsp");
        rd.forward(request, response);

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
