package br.com.fiap.transparenciaja.api.controller;
import br.com.fiap.transparenciaja.api.model.Iniciativa;
import br.com.fiap.transparenciaja.api.repository.IniciativaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;
@RestController
@RequestMapping("/api/iniciativas")
public class IniciativaController {
    private final IniciativaRepository repository;
    public IniciativaController(IniciativaRepository repository) { this.repository = repository; }
    @GetMapping public List<Iniciativa> listar() { return repository.listar(); }
    @GetMapping("/{id}") public ResponseEntity<?> buscar(@PathVariable Long id) {
        return repository.buscar(id).<ResponseEntity<?>>map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("erro", "Iniciativa não encontrada")));
    }
    @PostMapping public ResponseEntity<?> criar(@RequestBody Iniciativa entrada) {
        String erro = validar(entrada);
        if (erro != null) return ResponseEntity.badRequest().body(Map.of("erro", erro));
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.criar(entrada));
    }
    @PutMapping("/{id}") public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Iniciativa entrada) {
        String erro = validar(entrada);
        if (erro != null) return ResponseEntity.badRequest().body(Map.of("erro", erro));
        return repository.atualizar(id, entrada).<ResponseEntity<?>>map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("erro", "Iniciativa não encontrada")));
    }
    @DeleteMapping("/{id}") public ResponseEntity<?> excluir(@PathVariable Long id) {
        if (!repository.excluir(id)) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("erro", "Iniciativa não encontrada"));
        return ResponseEntity.noContent().build();
    }
    private String validar(Iniciativa i) {
        if (i == null || i.nome() == null || i.nome().isBlank()) return "O nome é obrigatório";
        if (i.descricao() == null || i.descricao().isBlank()) return "A descrição é obrigatória";
        if (i.pilarEsg() == null || !Set.of("Ambiental", "Social", "Governança").contains(i.pilarEsg())) return "O pilar ESG deve ser Ambiental, Social ou Governança";
        if (i.quantidadeRecicladaKg() < 0) return "A quantidade reciclada não pode ser negativa";
        return null;
    }
}
