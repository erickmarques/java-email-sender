package br.com.erickmarques.service;

import br.com.erickmarques.dto.EmailRequestDTO;
import br.com.erickmarques.dto.EmailResponseDTO;
import br.com.erickmarques.entity.Email;
import br.com.erickmarques.enumeration.EmailStatus;
import br.com.erickmarques.mapper.ModelMapperConverter;
import br.com.erickmarques.publisher.SenderEmailPublisher;
import br.com.erickmarques.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class EmailService {

    @Autowired
    EmailRepository repository;

    @Autowired
    SenderEmailPublisher publisher;

    public EmailResponseDTO create(EmailRequestDTO request){

        var email = repository.save(ModelMapperConverter.parseObject(request, Email.class));

        if (email.getId() != null){            
            publisher.senderEmailQueue(email);
        }
        return ModelMapperConverter.parseObject(email, EmailResponseDTO.class);
    }

    public EmailResponseDTO setStatus(Long id, EmailStatus status){
        
        var email = getEmail(id);
        email.setStatus(status);
        repository.save(email);
        
        return ModelMapperConverter.parseObject(email, EmailResponseDTO.class);
    }

    public EmailResponseDTO findById(Long id){
        return ModelMapperConverter.parseObject(getEmail(id), EmailResponseDTO.class);
    }

    public Email getEmail(Long id){
		return repository.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
								"Não existe e-mail para o ID informado!"));
	}
}
