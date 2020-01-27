package com.cubicfox.service;

import com.cubicfox.entity.Rate;
import com.cubicfox.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RateService {

    @Autowired
    private RateRepository rateRepository;

    public Rate addRate(Rate rate){

        try {
            return rateRepository.save(rate);
        }catch (Exception e){
            throw e;
        }
    }
}
