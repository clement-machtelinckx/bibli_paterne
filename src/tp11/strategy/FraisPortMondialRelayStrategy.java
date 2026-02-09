package tp11.strategy;

import tp11.Commande;
import tp11.Livre;

import java.util.List;

/*****
 * Stratégie correspondant au mode d'expédition : Mondial Relay
 */
public class FraisPortMondialRelayStrategy implements FraisPortStrategy {

    private final Commande contexte;

    public FraisPortMondialRelayStrategy(Commande contexte) {
        this.contexte = contexte;
    }

    /***
     * Regles de calcul :
     * - si le poids total de la commande est inférieur ou égal à 3kg, fdp = 4€
     * - au delà de 3kg, fdp = 8€
     */
    @Override
    public double calculerFraisPort() {
        double poidsTotal = 0.0;
        List<Livre> livres = contexte.getLivresCharges();
        for (Livre l : livres) {
            poidsTotal += l.getPoids();
        }

        if (poidsTotal <= 3.0) {
            return 4.0;
        }
        return 8.0;
    }
}
