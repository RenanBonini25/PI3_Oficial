
package br.com.pi3;

import br.com.pi3.Classes.Venda;
import br.com.pi3.DAO.DAOVenda;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GerarRelatorios", urlPatterns = {"/GerarRelatorios"})
public class GerarRelatorios extends HttpServlet {

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
        String dataInicio = request.getParameter("dataInicio");
        String dataTermino = request.getParameter("dataTermino");
        ArrayList<Venda> listaVendas = DAOVenda.gerarRelatorio(dataInicio, dataTermino);
        listaVendas = DAOVenda.gerarRelatorioItems(listaVendas, dataInicio, dataTermino);
        request.setAttribute("Listagem", listaVendas);
        RequestDispatcher rd = request.getRequestDispatcher("MostrarRelatorio.jsp");
        rd.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
