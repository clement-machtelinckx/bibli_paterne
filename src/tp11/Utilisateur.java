package tp11;

import tp11.dto.Entity;
import tp11.dto.UtilisateurDTO;

/******************************************************************
 * Entité Utilisateur (pattern DTO).
 *********************************************************************/
public class Utilisateur implements Entity {
    // Liste des attributs : interdiction d'en ajouter/retirer
    private int id;
    private String nom;
    private String email;
    private boolean isPremium = false;

    public Utilisateur() {
    }

    public Utilisateur(int id, String nom, String email, boolean isPremium) {
        setId(id);
        setNom(nom);
        setEmail(email);
        setPremium(isPremium);
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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (nom == null || nom.isBlank()) {
            throw new IllegalArgumentException("nom must not be blank");
        }
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("email must not be blank");
        }
        this.email = email;
    }

    public boolean isPremium() {
        return isPremium;
    }

    public void setPremium(boolean premium) {
        isPremium = premium;
    }

    @Override
    public UtilisateurDTO toDTO() {
        UtilisateurDTO dto = new UtilisateurDTO();
        dto.setId(this.id);
        dto.setNom(this.nom);
        dto.setEmail(this.email);
        dto.setPremium(this.isPremium);
        return dto;
    }
}
