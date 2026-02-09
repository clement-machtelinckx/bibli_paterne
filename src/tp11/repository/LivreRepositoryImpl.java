package tp11.repository;

import java.util.LinkedHashMap;
import java.util.Map;
import tp11.Livre;

/**
 * Implémentation in-memory du repository.
 * - Map pour accès direct par id
 * - LinkedHashMap pour conserver un ordre d'insertion stable (utile pour findAll()).
 */
public class LivreRepositoryImpl implements LivreRepository {

    private final Map<Integer, Livre> livresById = new LinkedHashMap<>();

    @Override
    public void save(Livre livre) {
        if (livre == null) {
            throw new IllegalArgumentException("livre must not be null");
        }
        livresById.put(livre.getId(), livre);
    }

    @Override
    public Iterable<Livre> findAll() {
        return livresById.values();
    }

    @Override
    public Livre findById(int id) {
        return livresById.get(id);
    }
}
