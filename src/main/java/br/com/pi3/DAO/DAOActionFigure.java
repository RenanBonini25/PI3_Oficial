package br.com.pi3.DAO;

import br.com.pi3.Classes.ActionFigure;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOActionFigure {

    private static Connection obterConexao() throws ClassNotFoundException, SQLException {
        //1A) Registrar drive JDBC
        Class.forName("com.mysql.jdbc.Driver");

        //1B) Abrir conexão com o BD
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/pi3", "root", "");
    }

    public static int incluir(ActionFigure action) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO ACTIONFIGURE (NOME, QUANTIDADE, PRECOCOMPRA,"
                + "PRECOVENDA, DESCRICAO, FABRICANTE, TAMANHO, COR)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";
        int idAction = 0;

        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, action.getNome());
                stmt.setInt(2, action.getQuantidade());
                stmt.setDouble(3, action.getPrecoCompra());
                stmt.setDouble(4, action.getPrecoVenda());
                stmt.setString(5, action.getDescricao());
                stmt.setString(6, action.getFabricante());
                stmt.setString(7, action.getTamanho());
                stmt.setString(8, action.getCor());
                stmt.executeUpdate();

                try (ResultSet chave = stmt.getGeneratedKeys()) {
                    if (chave.next()) {
                        idAction = chave.getInt(1);
                    }
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
        return idAction;
    }

    public static ArrayList<ActionFigure> listar() throws ClassNotFoundException, ParseException, SQLException{
        String query = "SELECT * FROM ACTIONFIGURE";
        ArrayList<ActionFigure> listaActions = new ArrayList<>();
        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                try (ResultSet resultados = stmt.executeQuery()) {
                    while (resultados.next()) {
                        ActionFigure action = new ActionFigure();
                        action.setId(resultados.getInt("ID"));
                        action.setNome(resultados.getString("NOME"));
                        action.setDescricao(resultados.getString("DESCRICAO"));
                        action.setFabricante(resultados.getString("FABRICANTE"));
                        action.setTamanho(resultados.getString("TAMANHO"));
                        action.setQuantidade(resultados.getInt("QUANTIDADE"));
                        action.setPrecoCompra(resultados.getDouble("PRECOCOMPRA"));
                        action.setPrecoVenda(resultados.getDouble("PRECOVENDA"));
                        listaActions.add(action);
                    }
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
        return listaActions;
    }

    public static ArrayList<ActionFigure> obterActionFigure(int id) throws ClassNotFoundException {
        ArrayList<ActionFigure> listaActions = new ArrayList<ActionFigure>();
        String query = "SELECT * FROM ACTIONFIGURE WHERE ID=?;";
        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                try (ResultSet resultados = stmt.executeQuery()) {
                    while (resultados.next()) {
                        ActionFigure action = new ActionFigure();
                        action.setId(resultados.getInt("ID"));
                        action.setNome(resultados.getString("NOME"));
                        action.setDescricao(resultados.getString("DESCRICAO"));
                        action.setFabricante(resultados.getString("FABRICANTE"));
                        action.setTamanho(resultados.getString("TAMANHO"));
                        action.setCor(resultados.getString("COR"));
                        action.setQuantidade(resultados.getInt("QUANTIDADE"));
                        action.setPrecoCompra(resultados.getDouble("PRECOCOMPRA"));
                        action.setPrecoVenda(resultados.getDouble("PRECOVENDA"));
                        listaActions.add(action);
                        return listaActions;
                    }
                }

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOActionFigure.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOActionFigure.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static void atualizarActionFigure(ActionFigure action) {
        String query = "UPDATE ACTIONFIGURE "
                + "SET NOME=?, QUANTIDADE=?, PRECOCOMPRA=?, PRECOVENDA=?, DESCRICAO=?, FABRICANTE=?, TAMANHO=?, COR=? "
                + "WHERE ID=?";
        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, action.getNome());
                stmt.setInt(2, action.getQuantidade());
                stmt.setDouble(3, action.getPrecoCompra());
                stmt.setDouble(4, action.getPrecoVenda());
                stmt.setString(5, action.getDescricao());
                stmt.setString(6, action.getFabricante());
                stmt.setString(7, action.getTamanho());
                stmt.setString(8, action.getCor());
                stmt.setInt(9, action.getId());
                stmt.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOActionFigure.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOActionFigure.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void excluirActionFigure(int id) {
        String query = "DELETE FROM ACTIONFIGURE WHERE ID = ?";
        try (Connection conn = obterConexao()) {
            try (PreparedStatement stmtCategoria = conn.prepareStatement(query)) {
                stmtCategoria.setInt(1, id);
                stmtCategoria.executeUpdate();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOActionFigure.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(DAOActionFigure.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
