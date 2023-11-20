package br.com.erickmarques.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequestDTO {

    @NotEmpty(message = "{field.to.required}")
    @Email(message = "{field.email.valid}")
    private String to;

    @NotEmpty(message = "{field.from.required}")
    @Email(message = "{field.email.valid}")
    private String from;

    @NotEmpty(message = "{field.body.required}")
    private String body;
}
