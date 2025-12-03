package at.kaindorf.backend.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * The Resource class is used to save resources that are only available in a limited amount.
 * @author <b>Berger S., Großschedl S., Riedner N.</b>
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private boolean isAvailable;
    @Enumerated(EnumType.STRING)
    private ResourceType resourceType;
}
