package at.kaindorf.backend.dto;

import at.kaindorf.backend.model.ResourceType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResourceDTO {
    private String name;
    private ResourceType typ;
    private boolean isAvailable;
    private int inventoryNum;
}
