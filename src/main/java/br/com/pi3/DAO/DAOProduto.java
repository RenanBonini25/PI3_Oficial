
package br.com.pi3.DAO;

import br.com.pi3.Classes.Acessorios;
import br.com.pi3.Classes.ActionFigure;
import br.com.pi3.Classes.Console;
import br.com.pi3.Classes.Game;
import br.com.pi3.Classes.Produto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOProduto {
    
    private static Connection obterConexao() throws ClassNotFoundException, SQLException {
        //1A) Registrar drive JDBC
        Class.forName("com.mysql.jdbc.Driver");

        //1B) Abrir conexão com o BD
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/pi3", "root", "");
    }
    
    public static int incluir(Produto produto) {
        String queryGames = "INSERT INTO produto (NOME, QUANTIDADE, PRECOCOMPRA, PRECOVENDA, PLATAFORMA, DESENVOLVEDORA, CLASSIFICACAO, "
                + "CATEGORIA, TIPO, ATIVO)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String queryConsole = "INSERT INTO produto (NOME, QUANTIDADE, PRECOCOMPRA, PRECOVENDA, DESCRICAO, FORNECEDOR, COR, TIPO, ATIVO)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String queryActFig = "INSERT INTO produto (NOME, QUANTIDADE, PRECOCOMPRA, PRECOVENDA, DESCRICAO, FABRICANTE, TAMANHO, COR, TIPO, ATIVO)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String queryAcessorios = "INSERT INTO produto (NOME, QUANTIDADE, PRECOCOMPRA, PRECOVENDA, DESCRICAO, PLATAFORMA, COR, TIPO, ATIVO)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        int idProduto = 0;

        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);

            if (produto instanceof Game) {
                Game game = (Game) produto;
                try (PreparedStatement stmt = conn.prepareStatement(queryGames, Statement.RETURN_GENERATED_KEYS)) {

                    stmt.setString(1, game.getNome());
                    stmt.setInt(2, game.getQuantidade());
                    stmt.setDouble(3, game.getPrecoCompra());
                    stmt.setDouble(4, game.getPrecoVenda());
                    stmt.setString(5, game.getPlataforma());
                    stmt.setString(6, game.getDesenvolvedora());
                    stmt.setString(7, game.getClassIndicativa());
                    stmt.setString(8, game.getCategoria());
                    stmt.setString(9, game.getTipo());
                    stmt.setBoolean(10, true);

                    stmt.executeUpdate();

                    try (ResultSet chave = stmt.getGeneratedKeys()) {
                        if (chave.next()) {
                            idProduto = chave.getInt(1);
                        }
                    }
                } catch (SQLException e) {
                    conn.rollback();
                    throw e;
                }
            } else if (produto instanceof Console) {
                Console console = (Console) produto;
                try (PreparedStatement stmt = conn.prepareStatement(queryConsole, Statement.RETURN_GENERATED_KEYS)) {

                    stmt.setString(1, console.getNome());
                    stmt.setInt(2, console.getQuantidade());
                    stmt.setDouble(3, console.getPrecoCompra());
                    stmt.setDouble(4, console.getPrecoVenda());
                    stmt.setString(5, console.getDescricao());
                    stmt.setString(6, console.getFornecedor());
                    stmt.setString(7, console.getCor());
                    stmt.setString(8, console.getTipo());
                    stmt.setBoolean(9, true);

                    stmt.executeUpdate();

                    try (ResultSet chave = stmt.getGeneratedKeys()) {
                        if (chave.next()) {
                            idProduto = chave.getInt(1);
                        }
                    }
                } catch (SQLException e) {
                    conn.rollback();
                    throw e;
                }
            } else if (produto instanceof ActionFigure) {
                ActionFigure actFig = (ActionFigure) produto;
                try (PreparedStatement stmt = conn.prepareStatement(queryActFig, Statement.RETURN_GENERATED_KEYS)) {

                    stmt.setString(1, actFig.getNome());
                    stmt.setInt(2, actFig.getQuantidade());
                    stmt.setDouble(3, actFig.getPrecoCompra());
                    stmt.setDouble(4, actFig.getPrecoVenda());
                    stmt.setString(5, actFig.getDescricao());
                    stmt.setString(6, actFig.getFabricante());
                    stmt.setString(7, actFig.getTamanho());
                    stmt.setString(8, actFig.getCor());
                    stmt.setString(9, actFig.getTipo());
                    stmt.setBoolean(10, true);

                    stmt.executeUpdate();

                    try (ResultSet chave = stmt.getGeneratedKeys()) {
                        if (chave.next()) {
                            idProduto = chave.getInt(1);
                        }
                    }
                } catch (SQLException e) {
                    conn.rollback();
                    throw e;
                }
            } else if (produto instanceof Acessorios) {
                Acessorios acessorio = (Acessorios) produto;
                try (PreparedStatement stmt = conn.prepareStatement(queryAcessorios, Statement.RETURN_GENERATED_KEYS)) {

                    stmt.setString(1, acessorio.getNome());
                    stmt.setInt(2, acessorio.getQuantidade());
                    stmt.setDouble(3, acessorio.getPrecoCompra());
                    stmt.setDouble(4, acessorio.getPrecoVenda());
                    stmt.setString(5, acessorio.getDescricao());
                    stmt.setString(6, acessorio.getPlataforma());
                    stmt.setString(7, acessorio.getCor());
                    stmt.setString(8, acessorio.getTipo());
                    stmt.setBoolean(9, true);

                    stmt.executeUpdate();

                    try (ResultSet chave = stmt.getGeneratedKeys()) {
                        if (chave.next()) {
                            idProduto = chave.getInt(1);
                        }
                    }
                } catch (SQLException e) {
                    conn.rollback();
                    throw e;
                }
            }
            conn.commit();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idProduto;
    }
    
    public static ArrayList<Produto> procurar(String valor) {
        ArrayList<Produto> listaProdutos = new ArrayList<>();
        String query = "SELECT * FROM produto WHERE Nome LIKE ? AND Ativo = ?";
        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, "%" + valor + "%");
                stmt.setBoolean(2, true);
                try (ResultSet resultados = stmt.executeQuery()) {
                    while (resultados.next()) {
                        if (resultados.getString("TIPO").equals("Game")) {
                            Game game = new Game();
                            game.setId(resultados.getInt("ID_PRODUTO"));
                            game.setNome(resultados.getString("NOME"));
                            game.setQuantidade(resultados.getInt("QUANTIDADE"));
                            game.setPrecoCompra(resultados.getDouble("PRECOCOMPRA"));
                            game.setPrecoVenda(resultados.getDouble("PRECOVENDA"));
                            game.setPlataforma(resultados.getString("PLATAFORMA"));
                            game.setDesenvolvedora(resultados.getString("DESENVOLVEDORA"));
                            game.setClassIndicativa(resultados.getString("CLASSIFICACAO"));
                            game.setCategoria(resultados.getString("CATEGORIA"));
                            game.setTipo(resultados.getString("TIPO"));
                            listaProdutos.add(game);
                        } else if (resultados.getString("TIPO").equals("Console")) {
                            Console console = new Console();
                            console.setId(resultados.getInt("ID_PRODUTO"));
                            console.setNome(resultados.getString("NOME"));
                            console.setQuantidade(resultados.getInt("QUANTIDADE"));
                            console.setPrecoCompra(resultados.getDouble("PRECOCOMPRA"));
                            console.setPrecoVenda(resultados.getDouble("PRECOVENDA"));
                            console.setDescricao(resultados.getString("DESCRICAO"));
                            console.setCor(resultados.getString("COR"));
                            console.setFornecedor(resultados.getString("FORNECEDOR"));
                            console.setTipo(resultados.getString("TIPO"));
                            listaProdutos.add(console);
                        } else if (resultados.getString("TIPO").equals("ActionFigure")) {
                            ActionFigure actFig = new ActionFigure();
                            actFig.setId(resultados.getInt("ID_PRODUTO"));
                            actFig.setNome(resultados.getString("NOME"));
                            actFig.setQuantidade(resultados.getInt("QUANTIDADE"));
                            actFig.setPrecoCompra(resultados.getDouble("PRECOCOMPRA"));
                            actFig.setPrecoVenda(resultados.getDouble("PRECOVENDA"));
                            actFig.setDescricao(resultados.getString("DESCRICAO"));
                            actFig.setCor(resultados.getString("COR"));
                            actFig.setFabricante(resultados.getString("FABRICANTE"));
                            actFig.setTamanho(resultados.getString("TAMANHO"));
                            actFig.setTipo(resultados.getString("TIPO"));
                            listaProdutos.add(actFig);
                        } else if (resultados.getString("TIPO").equals("Acessorio")) {
                            Acessorios acessorio = new Acessorios();
                            acessorio.setId(resultados.getInt("ID_PRODUTO"));
                            acessorio.setNome(resultados.getString("NOME"));
                            acessorio.setQuantidade(resultados.getInt("QUANTIDADE"));
                            acessorio.setPrecoCompra(resultados.getDouble("PRECOCOMPRA"));
                            acessorio.setPrecoVenda(resultados.getDouble("PRECOVENDA"));
                            acessorio.setDescricao(resultados.getString("DESCRICAO"));
                            acessorio.setCor(resultados.getString("COR"));
                            acessorio.setPlataforma(resultados.getString("PLATAFORMA"));
                            acessorio.setTipo(resultados.getString("TIPO"));
                            listaProdutos.add(acessorio);
                        }
                    }
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaProdutos;
    }
    
    public static ArrayList<Produto> listar() {
        ArrayList<Produto> listaProdutos = new ArrayList<>();
        String query = "SELECT * FROM produto WHERE ativo = ?";
        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setBoolean(1, true);
                try (ResultSet resultados = stmt.executeQuery()) {
                    while (resultados.next()) {
                        if (resultados.getString("TIPO").equals("Game")) {
                            Game game = new Game();
                            game.setId(resultados.getInt("ID_PRODUTO"));
                            game.setNome(resultados.getString("NOME"));
                            game.setQuantidade(resultados.getInt("QUANTIDADE"));
                            game.setPrecoCompra(resultados.getDouble("PRECOCOMPRA"));
                            game.setPrecoVenda(resultados.getDouble("PRECOVENDA"));
                            game.setPlataforma(resultados.getString("PLATAFORMA"));
                            game.setDesenvolvedora(resultados.getString("DESENVOLVEDORA"));
                            game.setClassIndicativa(resultados.getString("CLASSIFICACAO"));
                            game.setCategoria(resultados.getString("CATEGORIA"));
                            game.setTipo(resultados.getString("TIPO"));
                            listaProdutos.add(game);
                        } else if (resultados.getString("TIPO").equals("Console")) {
                            Console console = new Console();
                            console.setId(resultados.getInt("ID_PRODUTO"));
                            console.setNome(resultados.getString("NOME"));
                            console.setQuantidade(resultados.getInt("QUANTIDADE"));
                            console.setPrecoCompra(resultados.getDouble("PRECOCOMPRA"));
                            console.setPrecoVenda(resultados.getDouble("PRECOVENDA"));
                            console.setDescricao(resultados.getString("DESCRICAO"));
                            console.setCor(resultados.getString("COR"));
                            console.setFornecedor(resultados.getString("FORNECEDOR"));
                            console.setTipo(resultados.getString("TIPO"));
                            listaProdutos.add(console);
                        } else if (resultados.getString("TIPO").equals("ActionFigure")) {
                            ActionFigure actFig = new ActionFigure();
                            actFig.setId(resultados.getInt("ID_PRODUTO"));
                            actFig.setNome(resultados.getString("NOME"));
                            actFig.setQuantidade(resultados.getInt("QUANTIDADE"));
                            actFig.setPrecoCompra(resultados.getDouble("PRECOCOMPRA"));
                            actFig.setPrecoVenda(resultados.getDouble("PRECOVENDA"));
                            actFig.setDescricao(resultados.getString("DESCRICAO"));
                            actFig.setCor(resultados.getString("COR"));
                            actFig.setFabricante(resultados.getString("FABRICANTE"));
                            actFig.setTamanho(resultados.getString("TAMANHO"));
                            actFig.setTipo(resultados.getString("TIPO"));
                            listaProdutos.add(actFig);
                        } else if (resultados.getString("TIPO").equals("Acessorio")) {
                            Acessorios acessorio = new Acessorios();
                            acessorio.setId(resultados.getInt("ID_PRODUTO"));
                            acessorio.setNome(resultados.getString("NOME"));
                            acessorio.setQuantidade(resultados.getInt("QUANTIDADE"));
                            acessorio.setPrecoCompra(resultados.getDouble("PRECOCOMPRA"));
                            acessorio.setPrecoVenda(resultados.getDouble("PRECOVENDA"));
                            acessorio.setDescricao(resultados.getString("DESCRICAO"));
                            acessorio.setCor(resultados.getString("COR"));
                            acessorio.setPlataforma(resultados.getString("PLATAFORMA"));
                            acessorio.setTipo(resultados.getString("TIPO"));
                            listaProdutos.add(acessorio);
                        }
                    }
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaProdutos;
    }
    
    public static void atualizar(Produto produto) {
        String queryGames = "UPDATE produto SET NOME=?, QUANTIDADE=?, PRECOCOMPRA=?, PRECOVENDA=?, PLATAFORMA=?, DESENVOLVEDORA=?, CLASSIFICACAO=?, "
                + "CATEGORIA=?, TIPO=? WHERE ID_PRODUTO=?";
        String queryConsole = "UPDATE produto SET NOME=?, QUANTIDADE=?, PRECOCOMPRA=?, PRECOVENDA=?, DESCRICAO=?, COR=?, FORNECEDOR=?, "
                + "TIPO=? WHERE ID_PRODUTO=?";
        String queryActionFigure = "UPDATE produto SET NOME=?, QUANTIDADE=?, PRECOCOMPRA=?, PRECOVENDA=?, DESCRICAO=?, COR=?, FABRICANTE=?, "
                + "TAMANHO=?, TIPO=? WHERE ID_PRODUTO=?";
        String queryAcessorios = "UPDATE produto SET NOME=?, QUANTIDADE=?, PRECOCOMPRA=?, PRECOVENDA=?, DESCRICAO=?, COR=?, PLATAFORMA=?, "
                + "TIPO=? WHERE ID_PRODUTO=?";

        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);

            if (produto instanceof Game) {
                Game game = (Game) produto;
                try (PreparedStatement stmt = conn.prepareStatement(queryGames, Statement.RETURN_GENERATED_KEYS)) {

                    stmt.setString(1, game.getNome());
                    stmt.setInt(2, game.getQuantidade());
                    stmt.setDouble(3, game.getPrecoCompra());
                    stmt.setDouble(4, game.getPrecoVenda());
                    stmt.setString(5, game.getPlataforma());
                    stmt.setString(6, game.getDesenvolvedora());
                    stmt.setString(7, game.getClassIndicativa());
                    stmt.setString(8, game.getCategoria());
                    stmt.setString(9, game.getTipo());
                    stmt.setInt(10, game.getId());

                    stmt.executeUpdate();

                } catch (SQLException e) {
                    conn.rollback();
                    throw e;
                }
            } else if (produto instanceof Console) {
                Console console = (Console) produto;
                try (PreparedStatement stmt = conn.prepareStatement(queryConsole, Statement.RETURN_GENERATED_KEYS)) {

                    stmt.setString(1, console.getNome());
                    stmt.setInt(2, console.getQuantidade());
                    stmt.setDouble(3, console.getPrecoCompra());
                    stmt.setDouble(4, console.getPrecoVenda());
                    stmt.setString(5, console.getDescricao());
                    stmt.setString(6, console.getFornecedor());
                    stmt.setString(7, console.getCor());
                    stmt.setString(8, console.getTipo());
                    stmt.setInt(9, console.getId());

                    stmt.executeUpdate();
                    
                } catch (SQLException e) {
                    conn.rollback();
                    throw e;
                }
            } else if (produto instanceof ActionFigure) {
                ActionFigure actFig = (ActionFigure) produto;
                try (PreparedStatement stmt = conn.prepareStatement(queryActionFigure, Statement.RETURN_GENERATED_KEYS)) {

                    stmt.setString(1, actFig.getNome());
                    stmt.setInt(2, actFig.getQuantidade());
                    stmt.setDouble(3, actFig.getPrecoCompra());
                    stmt.setDouble(4, actFig.getPrecoVenda());
                    stmt.setString(5, actFig.getDescricao());
                    stmt.setString(6, actFig.getFabricante());
                    stmt.setString(7, actFig.getTamanho());
                    stmt.setString(8, actFig.getCor());
                    stmt.setString(9, actFig.getTipo());
                    stmt.setInt(10, actFig.getId());

                    stmt.executeUpdate();

                } catch (SQLException e) {
                    conn.rollback();
                    throw e;
                }
            } else if (produto instanceof Acessorios) {
                Acessorios acessorio = (Acessorios) produto;
                try (PreparedStatement stmt = conn.prepareStatement(queryAcessorios, Statement.RETURN_GENERATED_KEYS)) {

                    stmt.setString(1, acessorio.getNome());
                    stmt.setInt(2, acessorio.getQuantidade());
                    stmt.setDouble(3, acessorio.getPrecoCompra());
                    stmt.setDouble(4, acessorio.getPrecoVenda());
                    stmt.setString(5, acessorio.getDescricao());
                    stmt.setString(6, acessorio.getPlataforma());
                    stmt.setString(7, acessorio.getCor());
                    stmt.setString(8, acessorio.getTipo());
                    stmt.setInt(9, acessorio.getId());

                    stmt.executeUpdate();

                } catch (SQLException e) {
                    conn.rollback();
                    throw e;
                }
            }
            conn.commit();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void excluir(int id) {
        String query = "UPDATE produto SET Ativo = ? WHERE(ID_PRODUTO = ?)";
        try (Connection conn = obterConexao()) {
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setBoolean(1, false);
                stmt.setInt(2, id);
                stmt.executeUpdate();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Produto obterIndiv(int id) {
        String query = "SELECT * FROM produto WHERE ID_PRODUTO = ? AND ATIVO = ?";
        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                stmt.setBoolean(2, true);
                try (ResultSet resultados = stmt.executeQuery()) {
                    while (resultados.next()) {
                        if (resultados.getString("TIPO").equals("Game")) {
                            Game game = new Game();
                            game.setId(resultados.getInt("ID_PRODUTO"));
                            game.setNome(resultados.getString("NOME"));
                            game.setQuantidade(resultados.getInt("QUANTIDADE"));
                            game.setPrecoCompra(resultados.getDouble("PRECOCOMPRA"));
                            game.setPrecoVenda(resultados.getDouble("PRECOVENDA"));
                            game.setPlataforma(resultados.getString("PLATAFORMA"));
                            game.setDesenvolvedora(resultados.getString("DESENVOLVEDORA"));
                            game.setClassIndicativa(resultados.getString("CLASSIFICACAO"));
                            game.setCategoria(resultados.getString("CATEGORIA"));
                            game.setTipo(resultados.getString("TIPO"));
                            return game;
                        } else if (resultados.getString("TIPO").equals("Console")) {
                            Console console = new Console();
                            console.setId(resultados.getInt("ID_PRODUTO"));
                            console.setNome(resultados.getString("NOME"));
                            console.setQuantidade(resultados.getInt("QUANTIDADE"));
                            console.setPrecoCompra(resultados.getDouble("PRECOCOMPRA"));
                            console.setPrecoVenda(resultados.getDouble("PRECOVENDA"));
                            console.setDescricao(resultados.getString("DESCRICAO"));
                            console.setCor(resultados.getString("COR"));
                            console.setFornecedor(resultados.getString("FORNECEDOR"));
                            console.setTipo(resultados.getString("TIPO"));
                            return console;
                        } else if (resultados.getString("TIPO").equals("ActionFigure")) {
                            ActionFigure actFig = new ActionFigure();
                            actFig.setId(resultados.getInt("ID_PRODUTO"));
                            actFig.setNome(resultados.getString("NOME"));
                            actFig.setQuantidade(resultados.getInt("QUANTIDADE"));
                            actFig.setPrecoCompra(resultados.getDouble("PRECOCOMPRA"));
                            actFig.setPrecoVenda(resultados.getDouble("PRECOVENDA"));
                            actFig.setDescricao(resultados.getString("DESCRICAO"));
                            actFig.setCor(resultados.getString("COR"));
                            actFig.setFabricante(resultados.getString("FABRICANTE"));
                            actFig.setTamanho(resultados.getString("TAMANHO"));
                            actFig.setTipo(resultados.getString("TIPO"));
                            return actFig;
                        } else if (resultados.getString("TIPO").equals("Acessorio")) {
                            Acessorios acessorio = new Acessorios();
                            acessorio.setId(resultados.getInt("ID_PRODUTO"));
                            acessorio.setNome(resultados.getString("NOME"));
                            acessorio.setQuantidade(resultados.getInt("QUANTIDADE"));
                            acessorio.setPrecoCompra(resultados.getDouble("PRECOCOMPRA"));
                            acessorio.setPrecoVenda(resultados.getDouble("PRECOVENDA"));
                            acessorio.setDescricao(resultados.getString("DESCRICAO"));
                            acessorio.setCor(resultados.getString("COR"));
                            acessorio.setPlataforma(resultados.getString("PLATAFORMA"));
                            acessorio.setTipo(resultados.getString("TIPO"));
                            return acessorio;
                        }
                    }
                }

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public static ArrayList<Produto> obter(int id) {
        ArrayList<Produto> listaProduto = new ArrayList<>();
        String query = "SELECT * FROM produto WHERE ID_PRODUTO = ? AND ATIVO = ?";
        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                stmt.setBoolean(2, true);
                try (ResultSet resultados = stmt.executeQuery()) {
                    while (resultados.next()) {
                        if (resultados.getString("TIPO").equals("Game")) {
                            Game game = new Game();
                            game.setId(resultados.getInt("ID_PRODUTO"));
                            game.setNome(resultados.getString("NOME"));
                            game.setQuantidade(resultados.getInt("QUANTIDADE"));
                            game.setPrecoCompra(resultados.getDouble("PRECOCOMPRA"));
                            game.setPrecoVenda(resultados.getDouble("PRECOVENDA"));
                            game.setPlataforma(resultados.getString("PLATAFORMA"));
                            game.setDesenvolvedora(resultados.getString("DESENVOLVEDORA"));
                            game.setClassIndicativa(resultados.getString("CLASSIFICACAO"));
                            game.setCategoria(resultados.getString("CATEGORIA"));
                            game.setTipo(resultados.getString("TIPO"));
                            listaProduto.add(game);
                            return listaProduto;
                        } else if (resultados.getString("TIPO").equals("Console")) {
                            Console console = new Console();
                            console.setId(resultados.getInt("ID_PRODUTO"));
                            console.setNome(resultados.getString("NOME"));
                            console.setQuantidade(resultados.getInt("QUANTIDADE"));
                            console.setPrecoCompra(resultados.getDouble("PRECOCOMPRA"));
                            console.setPrecoVenda(resultados.getDouble("PRECOVENDA"));
                            console.setDescricao(resultados.getString("DESCRICAO"));
                            console.setCor(resultados.getString("COR"));
                            console.setFornecedor(resultados.getString("FORNECEDOR"));
                            console.setTipo(resultados.getString("TIPO"));
                            listaProduto.add(console);
                            return listaProduto;
                        } else if (resultados.getString("TIPO").equals("ActionFigure")) {
                            ActionFigure actFig = new ActionFigure();
                            actFig.setId(resultados.getInt("ID_PRODUTO"));
                            actFig.setNome(resultados.getString("NOME"));
                            actFig.setQuantidade(resultados.getInt("QUANTIDADE"));
                            actFig.setPrecoCompra(resultados.getDouble("PRECOCOMPRA"));
                            actFig.setPrecoVenda(resultados.getDouble("PRECOVENDA"));
                            actFig.setDescricao(resultados.getString("DESCRICAO"));
                            actFig.setCor(resultados.getString("COR"));
                            actFig.setFabricante(resultados.getString("FABRICANTE"));
                            actFig.setTamanho(resultados.getString("TAMANHO"));
                            actFig.setTipo(resultados.getString("TIPO"));
                            listaProduto.add(actFig);
                            return listaProduto;
                        } else if (resultados.getString("TIPO").equals("Acessorio")) {
                            Acessorios acessorio = new Acessorios();
                            acessorio.setId(resultados.getInt("ID_PRODUTO"));
                            acessorio.setNome(resultados.getString("NOME"));
                            acessorio.setQuantidade(resultados.getInt("QUANTIDADE"));
                            acessorio.setPrecoCompra(resultados.getDouble("PRECOCOMPRA"));
                            acessorio.setPrecoVenda(resultados.getDouble("PRECOVENDA"));
                            acessorio.setDescricao(resultados.getString("DESCRICAO"));
                            acessorio.setCor(resultados.getString("COR"));
                            acessorio.setPlataforma(resultados.getString("PLATAFORMA"));
                            acessorio.setTipo(resultados.getString("TIPO"));
                            listaProduto.add(acessorio);
                            return listaProduto;
                        }
                    }
                }

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
