package com.project.test.fileserv.model;

import com.project.test.fileserv.model.enums.DocumentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * информация о файле
 */
@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "documents")
public class Document {

    /**
     * ID генерируется тут
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * Имя документа
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * Дата и время создания
     */
    @Column(name = "creation_datetime", nullable = false)
    private LocalDateTime creationDatetime;

    /**
     * Дата и время удаления
     */
    @Column(name = "expiration_datetime")
    private LocalDateTime expirationDatetime;

    /**
     * тип документа
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "document_type", nullable = false)
    private DocumentType documentType;
}