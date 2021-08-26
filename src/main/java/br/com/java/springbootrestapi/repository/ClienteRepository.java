package br.com.java.springbootrestapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.java.springbootrestapi.model.Cliente;

public interface ClienteRepository extends JpaRepository <Cliente, Long> {
    List findByIdade(int idade);
}
