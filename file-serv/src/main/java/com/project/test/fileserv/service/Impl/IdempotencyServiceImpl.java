package com.project.test.fileserv.service.Impl;

import com.project.test.fileserv.model.ProcessedKeyEntity;
import com.project.test.fileserv.repository.ProcessedKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IdempotencyServiceImpl implements IdempotencyService {
    ProcessedKeyRepository processedKeyRepository;

    public boolean isProcessed(String idenpotensyKey) {
        return processedKeyRepository.existsById(idenpotensyKey);
    }

    public void markProcessed(String idenpotensyKey) {
        processedKeyRepository.save(new ProcessedKeyEntity(idenpotensyKey));
    }
}
