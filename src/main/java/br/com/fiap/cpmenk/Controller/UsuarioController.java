package br.com.fiap.cpmenk.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.cpmenk.models.Usuario;
import br.com.fiap.cpmenk.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {
    
    @Autowired
    UsuarioRepository repository;

        Logger log = LoggerFactory.getLogger(ItemController.class);

        @GetMapping
    public Page<Usuario> home(Pageable paginacao) {
        return repository.findAll(paginacao);
    }
    @GetMapping("{id}")
    public ResponseEntity<Usuario> show(@PathVariable Long id) {
        var usuario = repository.findById(id);

        if(usuario.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(usuario.get());
    }
    @PostMapping
    public ResponseEntity<Usuario> insert(@RequestBody Usuario usuario) {
        log.info("inserindo item: " + usuario);

        repository.save(usuario);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Usuario> delete(@PathVariable Long id) {
        var item = repository.findById(id);

        if(item.isEmpty()) return ResponseEntity.notFound().build();

        repository.delete(item.get());

        return ResponseEntity.noContent().build();
    }
    @PutMapping("{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        var itemEncotrado = repository.findById(id)
        ;
        if(itemEncotrado.isEmpty()) return ResponseEntity.notFound().build();
        
        usuario.setId(id);
        repository.save(usuario);

        return ResponseEntity.ok(usuario);
    }
}
