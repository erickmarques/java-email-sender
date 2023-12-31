package br.com.erickmarques.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailResponseDTO {

    private Long id;
    private String to;
    private String from;
    private String subject;
    private String body;
    private String status;
}
