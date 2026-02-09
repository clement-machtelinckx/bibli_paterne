package tp11.strategy;

import tp11.Commande;
import tp11.Livre;

import java.util.List;

/*****
 * Stratégie correspondant au mode d'expédition : Colissimo
 */
public class FraisPortColissimoStrategy implements FraisPortStrategy {

    private final Commande contexte;

    public FraisPortColissimoStrategy(Commande contexte) {
        this.contexte = contexte;
    }

    /***
     * Regles de calcul :
     * - si le poids total de la commande est inférieur ou égal à 1kg, fdp = 3€
     * - si le poids total de la commande est compris entre 1kg et 3kg, fdp = 5€
     * - au delà de 3kg, fdp = 15€
     */
    @Override
    public double calculerFraisPort() {
        double poidsTotal = 0.0;
        List<Livre> livres = contexte.getLivresCharges();
        for (Livre l : livres) {
            poidsTotal += l.getPoids();
        }

        if (poidsTotal <= 1.0) {
            return 3.0;
        }
        if (poidsTotal <= 3.0) {
            return 5.0;
        }
        return 15.0;
    }
}
s