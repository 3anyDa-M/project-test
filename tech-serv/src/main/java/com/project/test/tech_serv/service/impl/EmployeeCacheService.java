//package com.project.test.tech_serv.service.impl;
//
//import com.project.test.tech_serv.DTO.contract.GetFullDataEmployeeResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;

//@Service
//@RequiredArgsConstructor
//public class EmployeeCacheService {
//
//    private final RedisTemplate<String, Object> redisTemplate;
//    private static final String KEY_PREFIX = "employee_full:";
//
//    public void seveFullEmployee(GetFullDataEmployeeResponse response) {
//        if (response == null) return;
//        String key = KEY_PREFIX + response.employeeDTO().id();
//        redisTemplate.opsForValue().set(key, response);
//
//    }
//
//    public GetFullDataEmployeeResponse getFullEmployee(Long id) {
//        String key = KEY_PREFIX + id;
//        return (GetFullDataEmployeeResponse) redisTemplate.opsForValue().get(key);
//    }
//
//    public void deleteFullEmployee(Long id) {
//        redisTemplate.delete(KEY_PREFIX + id);
//    }
//}

