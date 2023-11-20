package br.com.erickmarques.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class Base implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    @Setter(AccessLevel.NONE)
    private Long id;

    @CreatedDate
    @Column(name = "date_created")
    @JsonIgnore
    private LocalDateTime dateCreated;
}

