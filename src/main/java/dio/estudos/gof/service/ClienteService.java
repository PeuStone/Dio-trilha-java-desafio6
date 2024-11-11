package dio.estudos.gof.service;

import dio.estudos.gof.model.Cliente;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio de cliente. Com
 * isso, se necessário, podemos ter multiplas implementações dessa mesma
 * interface.
 */

public interface ClienteService {

    Iterable<Cliente> buscarTodos();
    Cliente buscarPorId(int id);
    void inserir(Cliente cliente);
    void atualizar(int id, Cliente cliente);
    void deletar(int id);
}
