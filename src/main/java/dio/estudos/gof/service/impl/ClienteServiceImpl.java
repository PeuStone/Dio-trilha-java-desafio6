package dio.estudos.gof.service.impl;

import dio.estudos.gof.model.Cliente;
import dio.estudos.gof.model.ClienteRepository;
import dio.estudos.gof.model.Endereco;
import dio.estudos.gof.model.EnderecoRepository;
import dio.estudos.gof.service.ClienteService;
import dio.estudos.gof.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos(){
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(int id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()){
            return cliente.get();
        }
        return cliente.orElseThrow();
    }

    @Override
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }


    @Override
    public void atualizar(int id, Cliente cliente) {
        Optional<Cliente> clienteBd = clienteRepository.findById(id);
        if (clienteBd.isPresent()) {
            Cliente clienteExistente = clienteBd.get();
            clienteExistente.setNome(cliente.getNome());
            clienteExistente.setEndereco(cliente.getEndereco());
            salvarClienteComCep(clienteExistente);
        }
    }

    @Override
    public void deletar(int id) {
        clienteRepository.deleteById(id);
    }

    private void salvarClienteComCep(Cliente cliente) {
        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            enderecoRepository.save(novoEndereco);
            return novoEndereco;
        });
        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

}
