package br.com.java.springbootrestapi.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.java.springbootrestapi.model.Cliente;
import br.com.java.springbootrestapi.repository.ClienteRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class ClienteController {
    
    @Autowired
    ClienteRepository repository;
    
    // Listar Clientes
    @GetMapping("/clientes")
    public List <Cliente> todos() {
        return repository.findAll();
    }

    // Salvar cliente
    @PostMapping("/cliente")
    public Cliente salvar(@RequestBody Cliente cliente) {
        return repository.save(cliente);
    }

    // Deletar Cliente
    @DeleteMapping("/cliente/{id}")
    public void deletarCliente(@PathVariable(value = "id") Long id) {
        repository.deleteById(id);
    }
    
    // Buscar por idade
    @GetMapping("clientes/idade/{idade}")
    public List findByIdade(@PathVariable int idade) {
        List clientes = repository.findByIdade(idade);

        return clientes;
    }

    @PutMapping("/cliente/{id}")
    public ResponseEntity <Cliente> atualizarCliente(@RequestBody Cliente cliente, @PathVariable ("id") long id) {

        return repository.findById(id)
           .map(record -> {
               record.setNome(cliente.getNome());
               record.setIdade(cliente.getIdade());
               record.setAtivo(cliente.isAtivo());

               Cliente atualizarCliente = repository.save(record);

               return ResponseEntity.ok().body(atualizarCliente);
           }).orElse(ResponseEntity.notFound().build());
    }

}
