package br.com.fiap.cpmenk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.cpmenk.models.Viagem;

public interface ViagemRepository extends JpaRepository<Viagem, Long>{
    
}
