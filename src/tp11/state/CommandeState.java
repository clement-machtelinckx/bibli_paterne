package tp11.state;

import tp11.Commande;

/**
 * Interface State.
 */
public interface CommandeState {
    /**
     * Hook appelé quand la commande "entre" dans cet état.
     * On y fixe typiquement le libellé status attendu.
     */
    void entrerDansEtat(Commande commande);

    /**
     * Retourne l'état suivant (ou lui-même si état terminal/idempotent).
     */
    CommandeState etatSuivant();
}
