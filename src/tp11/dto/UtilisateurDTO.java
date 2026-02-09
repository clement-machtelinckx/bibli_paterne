package tp11.dto;

public class UtilisateurDTO implements TransferableObject {
    private int id;
    private String nom;
    private String email;
    private boolean premium;

    public UtilisateurDTO() {
    }

    public UtilisateurDTO(int id, String nom, String email, boolean premium) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.premium = premium;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }
}
