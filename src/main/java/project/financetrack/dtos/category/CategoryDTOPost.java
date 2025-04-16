package project.financetrack.dtos.category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTOPost {
    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String color;

    @NotNull
    private Long projectId;
}
