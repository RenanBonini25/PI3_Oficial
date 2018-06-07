package br.com.pi3.DAO;

import br.com.pi3.Classes.Acessorios;
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

public class DAOAcessorio {

    private static Connection obterConexao() throws ClassNotFoundException, SQLException {
        //1A) Registrar drive JDBC
        Class.forName("com.mysql.jdbc.Driver");

        //1B) Abrir conexão com o BD
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/pi3", "root", "");
    }

    public static int incluir(Acessorios acessorio) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO ACESSORIO (NOME, QUANTIDADE, PRECOCOMPRA,"
                + "PRECOVENDA, DESCRICAO, PLATAFORMA, COR)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?) ";
        int idAcessorio = 0;

        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, acessorio.getNome());
                stmt.setInt(2, acessorio.getQuantidade());
                stmt.setDouble(3, acessorio.getPrecoCompra());
                stmt.setDouble(4, acessorio.getPrecoVenda());
                stmt.setString(5, acessorio.getDescricao());
                stmt.setString(6, acessorio.getPlataforma());
                stmt.setString(7, acessorio.getCor());
                stmt.executeUpdate();

                try (ResultSet chave = stmt.getGeneratedKeys()) {
                    if (chave.next()) {
                        idAcessorio = chave.getInt(1);
                    }
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
        return idAcessorio;
    }

    public static ArrayList<Acessorios> listar() throws ClassNotFoundException, ParseException, SQLException{
        String query = "SELECT * FROM ACESSORIO";
        ArrayList<Acessorios> listaAcessorios = new ArrayList<>();
        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                try (ResultSet resultados = stmt.executeQuery()) {
                    while (resultados.next()) {
                        Acessorios acessorio = new Acessorios();
                        acessorio.setId(resultados.getInt("ID"));
                        acessorio.setNome(resultados.getString("NOME"));
                        acessorio.setDescricao(resultados.getString("DESCRICAO"));
                        acessorio.setPlataforma(resultados.getString("PLATAFORMA"));
                        acessorio.setQuantidade(resultados.getInt("QUANTIDADE"));
                        acessorio.setPrecoCompra(resultados.getDouble("PRECOCOMPRA"));
                        acessorio.setPrecoVenda(resultados.getDouble("PRECOVENDA"));
                        listaAcessorios.add(acessorio);
                    }
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
        return listaAcessorios;
    }

    public static ArrayList<Acessorios> obterAcessorio(int id) throws ClassNotFoundException {
        ArrayList<Acessorios> listaAcessorios = new ArrayList<Acessorios>();
        String query = "SELECT * FROM ACESSORIO WHERE ID = ?";
        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                try (ResultSet resultados = stmt.executeQuery()) {
                    while (resultados.next()) {
                        Acessorios acessorio = new Acessorios();
                        acessorio.setId(resultados.getInt("ID"));
                        acessorio.setNome(resultados.getString("NOME"));
                        acessorio.setQuantidade(resultados.getInt("QUANTIDADE"));
                        acessorio.setPrecoCompra(resultados.getDouble("PRECOCOMPRA"));
                        acessorio.setPrecoVenda(resultados.getDouble("PRECOVENDA"));
                        acessorio.setDescricao(resultados.getString("DESCRICAO"));
                        acessorio.setPlataforma(resultados.getString("PLATAFORMA"));
                        acessorio.setCor(resultados.getString("COR"));
                        listaAcessorios.add(acessorio);
                        return listaAcessorios;
                    }
                }

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAcessorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOAcessorio.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public static void atualizarAcessorio(Acessorios acessorio) {
        String query = "UPDATE ACESSORIO "
                + "SET NOME=?, QUANTIDADE=?, PRECOCOMPRA=?, PRECOVENDA=?, DESCRICAO=?, PLATAFORMA=?, COR=? "
                + "WHERE ID=?";
        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, acessorio.getNome());
                stmt.setInt(2, acessorio.getQuantidade());
                stmt.setDouble(3, acessorio.getPrecoCompra());
                stmt.setDouble(4, acessorio.getPrecoVenda());
                stmt.setString(5, acessorio.getDescricao());
                stmt.setString(6, acessorio.getPlataforma());
                stmt.setString(7, acessorio.getCor());
                stmt.setInt(8, acessorio.getId());
                stmt.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOAcessorio.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOAcessorio.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void excluirAcessorio(int id) {
        String query = "DELETE FROM ACESSORIO WHERE ID = ?";
        try (Connection conn = obterConexao()) {
            try (PreparedStatement stmtCategoria = conn.prepareStatement(query)) {
                stmtCategoria.setInt(1, id);
                stmtCategoria.executeUpdate();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOAcessorio.class
                    .getName()).log(Level.SEVERE, null, ex);

        } catch (SQLException ex) {
            Logger.getLogger(DAOAcessorio.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
