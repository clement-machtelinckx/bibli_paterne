package tp11.dto;

import java.util.ArrayList;
import java.util.List;

public class CommandeDTO implements TransferableObject {
    private int id;
    private UtilisateurDTO utilisateur;
    private String status;
    private List<LivreDTO> livres = new ArrayList<>();
    private double fraisDePort;

    public CommandeDTO() {
    }

    public CommandeDTO(int id, UtilisateurDTO utilisateur, String status, List<LivreDTO> livres, double fraisDePort) {
        this.id = id;
        this.utilisateur = utilisateur;
        this.status = status;
        if (livres != null) {
            this.livres = livres;
        }
        this.fraisDePort = fraisDePort;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public UtilisateurDTO getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(UtilisateurDTO utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<LivreDTO> getLivres() {
        return livres;
    }

    public void setLivres(List<LivreDTO> livres) {
        this.livres = (livres == null) ? new ArrayList<>() : livres;
    }

    public double getFraisDePort() {
        return fraisDePort;
    }

    public void setFraisDePort(double fraisDePort) {
        this.fraisDePort = fraisDePort;
    }
}
