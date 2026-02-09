package tp11.state;

import tp11.Commande;

/**
 * Etat représentant la commande nouvellement créée.
 * Son état suivant est : CommandeEnCours
 */
public class CommandeNouvelle implements CommandeState {

    @Override
    public void entrerDansEtat(Commande commande) {
        commande.setStatus("Commande créée");
    }

    @Override
    public CommandeState etatSuivant() {
        return new CommandeEnCours();
    }
}
