package dio.estudos.gof.controller;

import dio.estudos.gof.model.Produto;
import dio.estudos.gof.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("produtos")
public class ProdutoRestController {
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<Iterable<Produto>> buscarTodos() {
        return ResponseEntity.ok(produtoService.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscaPorId(@PathVariable int id) {
        return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto produto) {
        produtoService.inserir(produto);
        return ResponseEntity.ok(produto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable int id, @RequestBody Produto produto) {
        produtoService.atualizarProduto(id, produto);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Produto> excluirProduto(@PathVariable int id) {
        produtoService.deletar(id);
        return ResponseEntity.ok().build();
    }
}
