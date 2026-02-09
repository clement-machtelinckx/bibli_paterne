package tp11.state;

import tp11.Commande;

/**
 * Etat représentant la commande expédiée.
 * Etat terminal : à l'entrée, status = "Livrée"
 */
public class CommandeExpediee implements CommandeState {

    @Override
    public void entrerDansEtat(Commande commande) {
        commande.setStatus("Livrée");
    }

    @Override
    public CommandeState etatSuivant() {
        // Etat terminal : on reste "Livrée"
        return this;
    }
}
