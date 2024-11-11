package dio.estudos.gof.service;

import dio.estudos.gof.model.Produto;

public interface ProdutoService {

    Iterable<Produto> buscarTodos();
    Produto buscarPorId(int id);
    void inserir(Produto produto);
    void atualizarProduto(int id, Produto produto);
    void deletar(int id);
}
