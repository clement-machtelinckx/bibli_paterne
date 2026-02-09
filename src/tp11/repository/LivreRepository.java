package tp11.repository;


import tp11.Livre;
public interface LivreRepository {
    void save(Livre livre);

    Iterable<Livre> findAll();

    Livre findById(int id);
}
