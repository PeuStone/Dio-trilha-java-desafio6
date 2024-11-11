package dio.estudos.gof.service.impl;

import dio.estudos.gof.model.Produto;
import dio.estudos.gof.model.ProdutoRepository;
import dio.estudos.gof.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public Iterable<Produto> buscarTodos() {
        return produtoRepository.findAll();
    }

    @Override
    public Produto buscarPorId(int id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.get();
    }

    @Override
    public void inserir(Produto produto) {
        salvarDadosProduto(produto);
    }

    @Override
    public void atualizarProduto(int id, Produto produto) {
        Optional<Produto> produtoBd = produtoRepository.findById(id);
        if (produtoBd.isPresent()) {
            Produto produtoExistente = produtoBd.get();
            produtoExistente.setNome(produto.getNome());
            produtoExistente.setPreco(produto.getPreco());
            produtoExistente.setDescricao(produto.getDescricao());
            produtoRepository.save(produtoExistente);
        } else {
            salvarDadosProduto(produto);
        }
    }

    @Override
    public void deletar(int id) {
        produtoRepository.deleteById(id);
    }

    public void salvarDadosProduto(Produto produto) {
        int produtoId = produto.getId();
        Produto produtoExistente = produtoRepository.findById(produtoId).orElse(null);
        if (produtoExistente != null) {
            produtoExistente.setNome(produto.getNome());
            produtoExistente.setPreco(produto.getPreco());
            produtoExistente.setDescricao(produto.getDescricao());
            produtoRepository.save(produtoExistente);
        } else {
            produtoRepository.save(produto);
        }
    }
}
