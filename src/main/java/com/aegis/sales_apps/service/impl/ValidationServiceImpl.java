package com.aegis.sales_apps.service.impl;



import com.aegis.sales_apps.service.ValidationService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ValidationServiceImpl implements ValidationService {
    @Autowired
    private Validator validator;

    public void validate(Object request){
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(request);

        //check validasi
        if (constraintViolations.size() != 0){
            throw new ConstraintViolationException(constraintViolations);
        }
    }
}
