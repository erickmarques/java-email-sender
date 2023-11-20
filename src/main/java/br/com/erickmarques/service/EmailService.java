package br.com.erickmarques.service;

import br.com.erickmarques.dto.EmailRequestDTO;
import br.com.erickmarques.dto.EmailResponseDTO;
import br.com.erickmarques.entity.Email;
import br.com.erickmarques.mapper.ModelMapperConverter;
import br.com.erickmarques.publisher.SenderEmailPublisher;
import br.com.erickmarques.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    EmailRepository repository;

    @Autowired
    SenderEmailPublisher publisher;

    public EmailResponseDTO create (EmailRequestDTO request){

        var email = repository.save(ModelMapperConverter.parseObject(request, Email.class));

        if (email.getId() != null){
            publisher.senderEmailQueue(request);
        }

        return ModelMapperConverter.parseObject(email, EmailResponseDTO.class);
    }
}
