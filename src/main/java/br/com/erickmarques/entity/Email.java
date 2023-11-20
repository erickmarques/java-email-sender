package br.com.erickmarques.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "email")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Email extends Base {

	@Column(name = "to_email", updatable = false)
	private String to;

	@Column(name = "from_email", updatable = false)
	private String from;
	
	@Column(name = "body_email", updatable = false)
	private String body;
}
