package br.com.fiap.cpmenk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.cpmenk.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    
}
