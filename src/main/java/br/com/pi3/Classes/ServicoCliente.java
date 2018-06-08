
package br.com.pi3.Classes;

import br.com.pi3.DAO.DAOCliente;

public class ServicoCliente {
    
    public static Cliente obterCliente(int id) {
        Cliente cliente = DAOCliente.obterClienteIndiv(id);
        return cliente;
    }
    
}
