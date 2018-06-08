package br.com.pi3.DAO;

import br.com.pi3.Classes.Cliente;
import br.com.pi3.Classes.ItemCarrinho;
import br.com.pi3.Classes.Produto;
import br.com.pi3.Classes.ServicoCliente;
import br.com.pi3.Classes.ServicoProduto;
import br.com.pi3.Classes.Venda;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOVenda {

    private static Connection obterConexao() throws ClassNotFoundException, SQLException {
        //1A) Registrar drive JDBC
        Class.forName("com.mysql.jdbc.Driver");

        //1B) Abrir conexão com o BD
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/pi3", "root", "");
    }

    public int incluirVenda(Venda venda) {
        String query = "INSERT INTO venda (ID_CLIENTE, DATA_VENDA, VALOR_VENDA)"
                + "VALUES (?, ?, ?) ";
        int idVenda = 1;

        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, venda.getCliente().getId());
                stmt.setString(2, venda.getDataVenda());
                stmt.setDouble(3, venda.getTotal());

                stmt.execute();

                try (ResultSet chave = stmt.getGeneratedKeys()) {
                    while (chave.next()) {
                        idVenda = chave.getInt(1);
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
        return idVenda;
    }

    public void incluirItemCarrinho(ItemCarrinho itemCarrinho, int idVenda) {
        String query = "INSERT INTO itemcarrinho (ID_PRODUTO, ID_VENDA, QUANTIDADE, SUB_TOTAL)"
                + "VALUES (?, ?, ?, ?);";

        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, itemCarrinho.getProduto().getId());
                stmt.setInt(2, idVenda);
                stmt.setInt(3, itemCarrinho.getQuantidade());
                stmt.setDouble(4, itemCarrinho.getSubtotal());

                stmt.execute();

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
    }

    public void decrementoEstoque(Venda venda) {
        String query = "UPDATE produto SET QUANTIDADE = ? "
                + "WHERE ID_PRODUTO = ?";
        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                for (ItemCarrinho c : venda.getCarrinho()) {
                    stmt.setInt(1, c.getProduto().getQuantidade());
                    stmt.setInt(2, c.getProduto().getId());
                    stmt.executeUpdate();
                }

                stmt.execute();
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
    }

    public static ArrayList<Venda> gerarRelatorio(String dataInicio, String dataTermino) {
        ArrayList<Venda> listaVendas = new ArrayList<>();
//        String inicio = new SimpleDateFormat("dd/MM/yyyy").format(dataInicio);
//        String termino = new SimpleDateFormat("dd/MM/yyyy").format(dataTermino);
        String query = "SELECT Cliente.Id, Venda.ID_VENDA, Venda.Id_Cliente,Venda.Data_Venda, Venda.Valor_Venda FROM Venda\n"
                + "INNER JOIN Cliente ON Cliente.Id = Venda.Id_Cliente\n"
                + "WHERE Venda.Data_Venda BETWEEN '" + dataInicio + "' AND '" + dataTermino + "'";
        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                try (ResultSet resultados = stmt.executeQuery()) {
                    while (resultados.next()) {
                        Venda venda = new Venda();
                        int idCliente = resultados.getInt("ID_CLIENTE");
                        Cliente cliente = ServicoCliente.obterCliente(idCliente);
                        venda.setCliente(cliente);
                        venda.setDataVenda(resultados.getString("DATA_VENDA"));
                        venda.setId(resultados.getInt("ID_VENDA"));
                        venda.setTotal(resultados.getDouble("VALOR_VENDA"));
                        listaVendas.add(venda);
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
        return listaVendas;
    }

    public static ArrayList<Venda> gerarRelatorioItems(ArrayList<Venda> vendas, String dataInicio, String dataTermino) {

        String query = "SELECT ItemCarrinho.Sub_Total, ItemCarrinho.Id_Venda, ItemCarrinho.Id_Produto, Produto.Nome, "
                + "ItemCarrinho.Quantidade, Produto.tipo\n"
                + "FROM ItemCarrinho INNER JOIN Venda ON ItemCarrinho.Id_Venda = Venda.Id_Venda\n"
                + "INNER JOIN Produto ON Produto.ID_Produto = ItemCarrinho.Id_Produto\n"
                + "WHERE Venda.Data_Venda BETWEEN '" + dataInicio + "' AND '" + dataTermino + "'\n"
                + "ORDER BY Venda.Id_Venda";
        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                try (ResultSet resultados = stmt.executeQuery()) {
                    while (resultados.next()) {
                        for (int i = 0; i < vendas.size(); i++) {
                            int idItemVenda = resultados.getInt("ItemCarrinho.Id_Venda");
                            if (idItemVenda == vendas.get(i).getId()) {
                                int idProduto = resultados.getInt("ItemCarrinho.Id_Produto");
                                ItemCarrinho item = new ItemCarrinho();
                                Produto produto = ServicoProduto.obter(idProduto);
                                item.setProduto(produto);
                                item.setQuantidade(resultados.getInt("ItemCarrinho.Quantidade"));
                                item.setSubtotal(resultados.getDouble("ItemCarrinho.Sub_Total"));
                                vendas.get(i).getCarrinho().add(item);
                            }
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
        return vendas;
    }

}
