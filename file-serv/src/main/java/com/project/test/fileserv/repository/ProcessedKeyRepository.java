package com.project.test.fileserv.repository;

import com.project.test.fileserv.model.ProcessedKeyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessedKeyRepository extends JpaRepository <ProcessedKeyEntity, String>{
    public void save(String idenpotensyKey);
}
