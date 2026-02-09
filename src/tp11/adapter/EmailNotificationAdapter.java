package tp11.adapter;

import tp11.dto.CommandeDTO;
import tp11.dto.LivreDTO;
import tp11.dto.UtilisateurDTO;

/*****************************************************************************
 * Adaptateur : transforme CommandeDTO en paramètres String pour EmailService.
 */
public class EmailNotificationAdapter implements NotificationService {
    private EmailService emailService;

    public EmailNotificationAdapter(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void envoyerNotification(CommandeDTO commande) {
        if (commande == null) {
            throw new IllegalArgumentException("commande must not be null");
        }

        UtilisateurDTO user = commande.getUtilisateur();
        String to = (user == null) ? "" : user.getEmail();
        String subject = "Notification de commande";

        StringBuilder body = new StringBuilder();
        body.append("Votre commande ")
            .append(commande.getId())
            .append(" vient de passer dans l'état : ")
            .append(commande.getStatus())
            .append("\n");
        body.append("Récapitulatif de la commande :\n");

        if (commande.getLivres() != null) {
            for (LivreDTO livre : commande.getLivres()) {
                body.append("• ")
                    .append(livre.getTitre())
                    .append(" de ")
                    .append(livre.getAuteur())
                    .append("\n");
            }
        }

        body.append("Frais de port : ")
            .append(commande.getFraisDePort())
            .append("€");

        emailService.envoyerEmail(to, subject, body.toString());
    }
}
