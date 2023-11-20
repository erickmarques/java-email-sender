package br.com.erickmarques.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailResponseDTO {

    private String id;
    private String to;
    private String from;
    private String body;
}
