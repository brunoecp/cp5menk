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

import br.com.fiap.cpmenk.models.Item;
import br.com.fiap.cpmenk.models.Viagem;
import br.com.fiap.cpmenk.repository.UsuarioRepository;
import br.com.fiap.cpmenk.repository.ViagemRepository;

@RestController
@RequestMapping("/api/viagem")
public class ViagemController {
    
    @Autowired
    ViagemRepository vRepository;
    @Autowired
    UsuarioRepository uRepository;

        Logger log = LoggerFactory.getLogger(ItemController.class);

        @GetMapping
    public Page<Viagem> home(Pageable paginacao) {
        return vRepository.findAll(paginacao);
    }
    @GetMapping("{id}")
    public ResponseEntity<Viagem> show(@PathVariable Long id) {
        var viagem = vRepository.findById(id);

        if(viagem.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(viagem.get());
    }
    @PostMapping
    public ResponseEntity<Viagem> insert(@RequestBody Viagem viagem) {
        log.info("inserindo item: " + viagem);

        viagem.setUsuario(uRepository.findById(viagem.getUsuario().getId()).get());

        vRepository.save(viagem);

        return ResponseEntity.status(HttpStatus.CREATED).body(viagem);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Viagem> delete(@PathVariable Long id) {
        var viagem = vRepository.findById(id);

        if(viagem.isEmpty()) return ResponseEntity.notFound().build();

        vRepository.delete(viagem.get());

        return ResponseEntity.noContent().build();
    }
    @PutMapping("{id}")
    public ResponseEntity<Viagem> update(@PathVariable Long id, @RequestBody Viagem viagem) {
        var itemEncotrado = vRepository.findById(id)
        ;
        if(itemEncotrado.isEmpty()) return ResponseEntity.notFound().build();
        
        viagem.setId(id);
        vRepository.save(viagem);

        return ResponseEntity.ok(viagem);
    }
}
