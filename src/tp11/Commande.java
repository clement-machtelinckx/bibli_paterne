package tp11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import tp11.dto.CommandeDTO;
import tp11.dto.Entity;
import tp11.dto.LivreDTO;
import tp11.dto.UtilisateurDTO;
import tp11.repository.LivreRepository;
import tp11.state.CommandeNouvelle;
import tp11.state.CommandeState;
import tp11.strategy.FraisPortStrategy;

/******************************************************************
 * Entité Commande (pattern DTO) + Lazy Loading + Strategy + State.
 *********************************************************************/
public class Commande implements Entity {
    // Liste des attributs : interdiction d'en ajouter/retirer
    private int id;
    private Utilisateur utilisateur;
    private String status;
    private CommandeState currentState = null; // état actuel de la commande
    private double fraisDePort;

    // Attributs liés au LazyLoading
    private List<Integer> livreIds = new ArrayList<>();
    private transient List<Livre> livres; // Chargé à la demande (cache)

    public Commande() {
        // Main s'attend à "Commande créée" dès le début (avant tout etatSuivant()).
        this.currentState = new CommandeNouvelle();
        this.currentState.entrerDansEtat(this);
        this.fraisDePort = 0.0;
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

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        if (utilisateur == null) {
            throw new IllegalArgumentException("utilisateur must not be null");
        }
        this.utilisateur = utilisateur;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CommandeState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(CommandeState currentState) {
        this.currentState = currentState;
    }

    public double getFraisDePort() {
        return fraisDePort;
    }

    public void setFraisDePort(double fraisDePort) {
        if (fraisDePort < 0) {
            throw new IllegalArgumentException("fraisDePort must be >= 0");
        }
        this.fraisDePort = fraisDePort;
    }

    /**
     * Exposition contrôlée : les strategies n'ont pas de repository,
     * elles doivent utiliser les livres déjà chargés par le lazy loading.
     */
    public List<Livre> getLivresCharges() {
        return (livres == null) ? Collections.emptyList() : livres;
    }

    /**
     * Ajoute l'ID du livre et invalide le cache lazy.
     */
    public void addLivreId(int livreId) {
        livreIds.add(livreId);
        // Invalidation du cache
        livres = null;
    }

    /**
     * Lazy loading : charge une seule fois depuis le repository, puis met en cache.
     */
    public List<Livre> getLivres(LivreRepository repo) {
        if (livres != null) {
            return livres;
        }
        List<Livre> loaded = new ArrayList<>();
        for (Integer idLivre : livreIds) {
            if (idLivre == null) continue;
            Livre l = repo.findById(idLivre);
            if (l != null) {
                loaded.add(l);
            }
        }
        livres = loaded;
        return livres;
    }

    // Pattern Strategy
    public double calculerFraisDePort(FraisPortStrategy strategy) {
        double result = strategy.calculerFraisPort();
        setFraisDePort(result);
        return result;
    }

    // Pattern State
    public void etatSuivant() {
        if (currentState == null) {
            currentState = new CommandeNouvelle();
            currentState.entrerDansEtat(this);
            return;
        }

        CommandeState next = currentState.etatSuivant();
        if (next == null) {
            // Par sécurité, on reste dans l'état courant
            return;
        }
        currentState = next;
        currentState.entrerDansEtat(this);
    }

    @Override
    public CommandeDTO toDTO() {
        CommandeDTO dto = new CommandeDTO();
        dto.setId(this.id);
        dto.setStatus(this.status);
        dto.setFraisDePort(this.fraisDePort);

        UtilisateurDTO uDto = (this.utilisateur == null) ? null : this.utilisateur.toDTO();
        dto.setUtilisateur(uDto);

        // Si les livres ne sont pas chargés, on n'essaie pas de déclencher le lazy (pas de repo ici).
        List<LivreDTO> livresDto = new ArrayList<>();
        if (this.livres != null) {
            for (Livre l : this.livres) {
                livresDto.add(l.toDTO());
            }
        }
        dto.setLivres(livresDto);

        return dto;
    }
}
