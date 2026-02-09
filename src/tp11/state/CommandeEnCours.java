package tp11.state;

import tp11.Commande;

/**
 * Etat représentant la commande en cours.
 * Son état suivant est : Expédiée (mais le libellé attendu est "Expédiée" à l'entrée dans cet état)
 */
public class CommandeEnCours implements CommandeState {

    @Override
    public void entrerDansEtat(Commande commande) {
        // Conforme à l'attendu Main
        commande.setStatus("Expédiée");
    }

    @Override
    public CommandeState etatSuivant() {
        return new CommandeExpediee();
    }
}
