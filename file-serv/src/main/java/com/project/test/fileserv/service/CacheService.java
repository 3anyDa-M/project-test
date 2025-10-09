package com.project.test.fileserv.service;

public interface CacheService {
    String get(String key);
    void put(String key, String value, long ttlSeconds);
    boolean exists(String key);
    void delete(String key);
}
