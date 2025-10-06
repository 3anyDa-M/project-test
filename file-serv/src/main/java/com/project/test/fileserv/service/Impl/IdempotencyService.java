package com.project.test.fileserv.service.Impl;

public interface IdempotencyService {
    public boolean isProcessed(String idenpotensyKey);
    public void markProcessed(String idenpotensyKey);
}
