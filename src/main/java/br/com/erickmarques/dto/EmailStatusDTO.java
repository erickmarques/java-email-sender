package br.com.erickmarques.dto;

import br.com.erickmarques.enumeration.EmailStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailStatusDTO {
    @NotNull(message = "{field.status.required}")
    private EmailStatus status;
}
