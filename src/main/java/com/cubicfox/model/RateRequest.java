package com.cubicfox.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Represent a rate request
 */
public class RateRequest {

    public byte rateValue;

    public byte getRateValue() {
        return rateValue;
    }

    public void setRateValue(byte rateValue) {
        this.rateValue = rateValue;
    }

    @JsonIgnore
    public boolean isValid(){
        if(rateValue > 0 && rateValue < 11)
            return true;

        return false;
    }
}

