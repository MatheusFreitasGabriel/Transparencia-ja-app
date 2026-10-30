package br.com.fiap.transparenciaja.api.repository;
import br.com.fiap.transparenciaja.api.model.Iniciativa;
import org.springframework.stereotype.Repository;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Repository
public class IniciativaRepository {
    private final Map<Long, Iniciativa> dados = new ConcurrentHashMap<>();
    private final AtomicLong sequencia = new AtomicLong(0);
    public List<Iniciativa> listar() { return dados.values().stream().sorted(Comparator.comparing(Iniciativa::id)).toList(); }
    public Optional<Iniciativa> buscar(Long id) { return Optional.ofNullable(dados.get(id)); }
    public Iniciativa criar(Iniciativa entrada) {
        long id = sequencia.incrementAndGet();
        Iniciativa criada = new Iniciativa(id, entrada.nome(), entrada.descricao(), entrada.pilarEsg(), entrada.quantidadeRecicladaKg());
        dados.put(id, criada); return criada;
    }
    public Optional<Iniciativa> atualizar(Long id, Iniciativa entrada) {
        if (!dados.containsKey(id)) return Optional.empty();
        Iniciativa atualizada = new Iniciativa(id, entrada.nome(), entrada.descricao(), entrada.pilarEsg(), entrada.quantidadeRecicladaKg());
        dados.put(id, atualizada); return Optional.of(atualizada);
    }
    public boolean excluir(Long id) { return dados.remove(id) != null; }
}
