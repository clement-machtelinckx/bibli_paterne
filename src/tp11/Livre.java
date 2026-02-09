package tp11;

import tp11.dto.Entity;
import tp11.dto.LivreDTO;

/******************************************************************
 * Entité Livre (pattern DTO).
 *********************************************************************/
public class Livre implements Entity {
    // Liste des attributs : interdiction d'en ajouter/retirer (selon l'énoncé)
    private int id;
    private String titre;
    private String auteur;
    private double poids;

    public Livre() {
    }

    public Livre(int id, String titre, String auteur, double poids) {
        setId(id);
        setTitre(titre);
        setAuteur(auteur);
        setPoids(poids);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("id must be > 0");
        }
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        if (titre == null || titre.isBlank()) {
            throw new IllegalArgumentException("titre must not be blank");
        }
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        if (auteur == null || auteur.isBlank()) {
            throw new IllegalArgumentException("auteur must not be blank");
        }
        this.auteur = auteur;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        if (poids < 0) {
            throw new IllegalArgumentException("poids must be >= 0");
        }
        this.poids = poids;
    }

    @Override
    public LivreDTO toDTO() {
        LivreDTO dto = new LivreDTO();
        dto.setId(this.id);
        dto.setTitre(this.titre);
        dto.setAuteur(this.auteur);
        dto.setPoids(this.poids);
        return dto;
    }
}
