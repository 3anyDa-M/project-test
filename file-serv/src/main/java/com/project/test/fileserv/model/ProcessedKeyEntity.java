package com.project.test.fileserv.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "processed_keys")
public class ProcessedKeyEntity {
    @Id
    private String id;

    public ProcessedKeyEntity() {}

    public ProcessedKeyEntity(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}