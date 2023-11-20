package br.com.erickmarques.publisher;

import br.com.erickmarques.dto.EmailRequestDTO;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Component
public class SenderEmailPublisher {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private Queue queueSenderEmail;

	public void senderEmailQueue(EmailRequestDTO emailRequestDTO) {
		try {
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
	
			rabbitTemplate.convertAndSend(queueSenderEmail.getName(),
										  ow.writeValueAsString(emailRequestDTO));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}