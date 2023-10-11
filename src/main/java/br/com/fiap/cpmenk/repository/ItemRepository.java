package br.com.fiap.cpmenk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.cpmenk.models.Item;

public interface ItemRepository extends JpaRepository<Item, Long>{
    
}
