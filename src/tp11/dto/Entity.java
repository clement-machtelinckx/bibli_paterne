package tp11.dto;

/**
 * Contract for entities that can be converted to a DTO.
 */
public interface Entity {
    TransferableObject toDTO();
}
