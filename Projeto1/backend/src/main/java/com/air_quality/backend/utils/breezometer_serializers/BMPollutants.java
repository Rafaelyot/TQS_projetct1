package com.air_quality.backend.utils.breezometer_serializers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BMPollutants {
    private BMPollutant pollutant;
    private BMPollutant no2;
    private BMPollutant o3;
    private BMPollutant pm10;
    private BMPollutant pm25;
    private BMPollutant so2;
    private BMPollutant co;
    private BMPollutant c6h6;
    private BMPollutant ox;
    private BMPollutant nh3;
    private BMPollutant nmhc;
    private BMPollutant no;
    private BMPollutant nox;
    private BMPollutant trs;

    Logger logger = Logger.getLogger(BMPollutants.class.getName());

    public BMPollutant getPollutant() {
        return pollutant;
    }

    public void setPollutant(BMPollutant value) {
        this.pollutant = value;
    }

    public BMPollutant getNo2() {
        return no2;
    }

    public void setNo2(BMPollutant value) {
        this.no2 = value;
    }

    public BMPollutant getO3() {
        return o3;
    }

    public void setO3(BMPollutant value) {
        this.o3 = value;
    }

    public BMPollutant getPm10() {
        return pm10;
    }

    public void setPm10(BMPollutant value) {
        this.pm10 = value;
    }

    public BMPollutant getPm25() {
        return pm25;
    }

    public void setPm25(BMPollutant value) {
        this.pm25 = value;
    }

    public BMPollutant getSo2() {
        return so2;
    }

    public void setSo2(BMPollutant value) {
        this.so2 = value;
    }

    public BMPollutant getCo() {
        return co;
    }

    public void setCo(BMPollutant co) {
        this.co = co;
    }

    public BMPollutant getC6h6() {
        return c6h6;
    }

    public void setC6h6(BMPollutant c6h6) {
        this.c6h6 = c6h6;
    }

    public BMPollutant getOx() {
        return ox;
    }

    public void setOx(BMPollutant ox) {
        this.ox = ox;
    }

    public BMPollutant getNh3() {
        return nh3;
    }

    public void setNh3(BMPollutant nh3) {
        this.nh3 = nh3;
    }

    public BMPollutant getNmhc() {
        return nmhc;
    }

    public void setNmhc(BMPollutant nmhc) {
        this.nmhc = nmhc;
    }

    public BMPollutant getNo() {
        return no;
    }

    public void setNo(BMPollutant no) {
        this.no = no;
    }

    public BMPollutant getNox() {
        return nox;
    }

    public void setNox(BMPollutant nox) {
        this.nox = nox;
    }

    public BMPollutant getTrs() {
        return trs;
    }

    public void setTrs(BMPollutant trs) {
        this.trs = trs;
    }

    public BMPollutant getPollutantByName(String code) {
        String codeCap = code.substring(0, 1).toUpperCase() + code.substring(1);
        try {
            Method pollutantGetter = this.getClass().getMethod("get" + codeCap);
            return (BMPollutant) pollutantGetter.invoke(this);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            logger.log(Level.WARNING, "Error on <{0}> : {1}", new Object[]{new Throwable().getStackTrace()[0].getMethodName(), e});
        }
        return null;
    }
}
