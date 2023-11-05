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
import br.com.fiap.cpmenk.repository.ItemRepository;
import br.com.fiap.cpmenk.repository.ViagemRepository;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    
    @Autowired
    ItemRepository iRepository;
    @Autowired
    ViagemRepository vRepository;
    
    Logger log = LoggerFactory.getLogger(ItemController.class);

        @GetMapping
    public Page<Item> home(Pageable paginacao) {
        return iRepository.findAll(paginacao);
    }
    @GetMapping("{id}")
    public ResponseEntity<Item> show(@PathVariable Long id) {
        var item = iRepository.findById(id);

        if(item.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(item.get());
    }
    @PostMapping
    public ResponseEntity<Item> insert(@RequestBody Item item) {
        log.info("inserindo item: " + item);

        item.setViagem(vRepository.findById(item.getViagem().getId()).get());


        iRepository.save(item);

        return ResponseEntity.status(HttpStatus.CREATED).body(item);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Item> delete(@PathVariable Long id) {
        var item = iRepository.findById(id);

        if(item.isEmpty()) return ResponseEntity.notFound().build();

        iRepository.delete(item.get());

        return ResponseEntity.noContent().build();
    }
    @PutMapping("{id}")
    public ResponseEntity<Item> update(@PathVariable Long id, @RequestBody Item item) {
        var itemEncotrado = iRepository.findById(id)
        ;
        if(itemEncotrado.isEmpty()) return ResponseEntity.notFound().build();
        
        item.setId(id);
        iRepository.save(item);

        return ResponseEntity.ok(item);
    }
}
