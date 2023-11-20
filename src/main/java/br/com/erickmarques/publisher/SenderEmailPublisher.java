package br.com.erickmarques.publisher;

import br.com.erickmarques.entity.Email;
import lombok.extern.slf4j.Slf4j;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Component
@Slf4j
public class SenderEmailPublisher {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private Queue queueSenderEmail;

	public void senderEmailQueue(Email email) {
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
	
			rabbitTemplate.convertAndSend(queueSenderEmail.getName(),
										  ow.writeValueAsString(email));
		} catch (Exception e) {
			log.error("Erro ao converter payload para objeto email: {} ", e.getMessage());
		}
	}

}