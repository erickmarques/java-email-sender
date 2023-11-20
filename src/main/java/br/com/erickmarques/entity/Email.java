package br.com.erickmarques.entity;

import br.com.erickmarques.enumeration.EmailStatus;
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

	@Column(name = "to_email", updatable = false, length = 255)
	private String to;

	@Column(name = "from_email", updatable = false, length = 255)
	private String from;
	
	@Column(name = "subject_email", updatable = false, length = 255)
	private String subject;

	@Column(name = "body_email", updatable = false, length = 4000)
	private String body;

	@Column(name = "status_email", length = 20)
	private EmailStatus status = EmailStatus.WAITING;
}
