package br.com.pi3.DAO;

import br.com.pi3.Classes.Console;
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

public class DAOConsole {

    private static Connection obterConexao() throws ClassNotFoundException, SQLException {
        //1A) Registrar drive JDBC
        Class.forName("com.mysql.jdbc.Driver");

        //1B) Abrir conexão com o BD
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/pi3", "root", "");
    }

    public static int incluir(Console console) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO CONSOLE (NOME, QUANTIDADE, PRECOCOMPRA,"
                + "PRECOVENDA, DESCRICAO, FORNECEDOR, COR)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?) ";
        int idConsole = 0;

        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, console.getNome());
                stmt.setInt(2, console.getQuantidade());
                stmt.setDouble(3, console.getPrecoCompra());
                stmt.setDouble(4, console.getPrecoVenda());
                stmt.setString(5, console.getDescricao());
                stmt.setString(6, console.getFornecedor());
                stmt.setString(7, console.getCor());
                stmt.executeUpdate();

                try (ResultSet chave = stmt.getGeneratedKeys()) {
                    if (chave.next()) {
                        idConsole = chave.getInt(1);
                    }
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
        return idConsole;
    }

    public static ArrayList<Console> listar() throws ClassNotFoundException, ParseException, SQLException{
        String query = "SELECT * FROM CONSOLE";
        ArrayList<Console> listaConsole = new ArrayList<>();
        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                try (ResultSet resultados = stmt.executeQuery()) {
                    while (resultados.next()) {
                        Console console = new Console();
                        console.setId(resultados.getInt("ID"));
                        console.setNome(resultados.getString("NOME"));
                        console.setDescricao(resultados.getString("DESCRICAO"));
                        console.setFornecedor(resultados.getString("FORNECEDOR"));
                        console.setQuantidade(resultados.getInt("QUANTIDADE"));
                        console.setPrecoCompra(resultados.getDouble("PRECOCOMPRA"));
                        console.setPrecoVenda(resultados.getDouble("PRECOVENDA"));
                        listaConsole.add(console);
                    }
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
        return listaConsole;
    }

    public static ArrayList<Console> obterConsole(int id) throws ClassNotFoundException {
        ArrayList<Console> listaConsoles = new ArrayList<Console>();
        String query = "SELECT * FROM CONSOLE WHERE ID = ?";
        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                try (ResultSet resultados = stmt.executeQuery()) {
                    while (resultados.next()) {
                        Console console = new Console();
                        console.setId(resultados.getInt("ID"));
                        console.setNome(resultados.getString("NOME"));
                        console.setQuantidade(resultados.getInt("QUANTIDADE"));
                        console.setPrecoCompra(resultados.getDouble("PRECOCOMPRA"));
                        console.setPrecoVenda(resultados.getDouble("PRECOVENDA"));
                        console.setDescricao(resultados.getString("DESCRICAO"));
                        console.setFornecedor(resultados.getString("FORNECEDOR"));
                        console.setCor(resultados.getString("COR"));
                        listaConsoles.add(console);
                        return listaConsoles;
                    }
                }

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOConsole.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOConsole.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static void atualizarConsole(Console console) {
        String query = "UPDATE CONSOLE "
                + "SET NOME=?, QUANTIDADE=?, PRECOCOMPRA=?, PRECOVENDA=?, DESCRICAO=?, FORNECEDOR=?, COR=? "
                + "WHERE ID=?";
        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, console.getNome());
                stmt.setInt(2, console.getQuantidade());
                stmt.setDouble(3, console.getPrecoCompra());
                stmt.setDouble(4, console.getPrecoVenda());
                stmt.setString(5, console.getDescricao());
                stmt.setString(6, console.getFornecedor());
                stmt.setString(7, console.getCor());
                stmt.setInt(8, console.getId());
                stmt.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOConsole.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOConsole.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void excluirConsole(int id) {
        String query = "DELETE FROM CONSOLE WHERE ID = ?";
        try (Connection conn = obterConexao()) {
            try (PreparedStatement stmtCategoria = conn.prepareStatement(query)) {
                stmtCategoria.setInt(1, id);
                stmtCategoria.executeUpdate();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOConsole.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(DAOConsole.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
