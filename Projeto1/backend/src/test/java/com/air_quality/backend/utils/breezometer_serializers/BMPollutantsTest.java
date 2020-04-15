package com.air_quality.backend.utils.breezometer_serializers;

import com.air_quality.backend.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class BMPollutantsTest {

    private BMPollutants bmPollutants;

    @BeforeEach
    void setUp() {
        this.bmPollutants = new BMPollutants();
    }

    @Test
    void getPollutant() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutant pollutant = new BMPollutant();
        assertThat(TestUtils.get(this.bmPollutants, pollutant, "pollutant", "getPollutant"), is(pollutant));
    }

    @Test
    void setPollutant() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutant pollutant = new BMPollutant();
        assertThat(TestUtils.set(this.bmPollutants, pollutant, BMPollutant.class, "pollutant", "setPollutant"), is(pollutant));
    }

    @Test
    void getNo2() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutant no2 = new BMPollutant();
        assertThat(TestUtils.get(this.bmPollutants, no2, "no2", "getNo2"), is(no2));
    }

    @Test
    void setNo2() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutant no2 = new BMPollutant();
        assertThat(TestUtils.set(this.bmPollutants, no2, BMPollutant.class, "no2", "setNo2"), is(no2));
    }

    @Test
    void getO3() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutant o3 = new BMPollutant();
        assertThat(TestUtils.get(this.bmPollutants, o3, "o3", "getO3"), is(o3));
    }

    @Test
    void setO3() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutant o3 = new BMPollutant();
        assertThat(TestUtils.set(this.bmPollutants, o3, BMPollutant.class, "o3", "setO3"), is(o3));
    }

    @Test
    void getPm10() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutant pm10 = new BMPollutant();
        assertThat(TestUtils.get(this.bmPollutants, pm10, "pm10", "getPm10"), is(pm10));
    }

    @Test
    void setPm10() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutant pm10 = new BMPollutant();
        assertThat(TestUtils.set(this.bmPollutants, pm10, BMPollutant.class, "pm10", "setPm10"), is(pm10));
    }

    @Test
    void getPm25() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutant pm25 = new BMPollutant();
        assertThat(TestUtils.get(this.bmPollutants, pm25, "pm25", "getPm25"), is(pm25));
    }

    @Test
    void setPm25() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutant pm25 = new BMPollutant();
        assertThat(TestUtils.set(this.bmPollutants, pm25, BMPollutant.class, "pm25", "setPm25"), is(pm25));
    }

    @Test
    void getSo2() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutant so2 = new BMPollutant();
        assertThat(TestUtils.get(this.bmPollutants, so2, "so2", "getSo2"), is(so2));
    }

    @Test
    void setSo2() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutant so2 = new BMPollutant();
        assertThat(TestUtils.set(this.bmPollutants, so2, BMPollutant.class, "so2", "setSo2"), is(so2));
    }

    @Test
    void getCo() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutant co = new BMPollutant();
        assertThat(TestUtils.get(this.bmPollutants, co, "co", "getCo"), is(co));
    }

    @Test
    void setCo() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutant co = new BMPollutant();
        assertThat(TestUtils.set(this.bmPollutants, co, BMPollutant.class, "co", "setCo"), is(co));
    }

    @Test
    void getC6h6() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutant c6h6 = new BMPollutant();
        assertThat(TestUtils.get(this.bmPollutants, c6h6, "c6h6", "getC6h6"), is(c6h6));
    }

    @Test
    void setC6h6() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutant c6h6 = new BMPollutant();
        assertThat(TestUtils.set(this.bmPollutants, c6h6, BMPollutant.class, "c6h6", "setC6h6"), is(c6h6));
    }

    @Test
    void getOx() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutant ox = new BMPollutant();
        assertThat(TestUtils.get(this.bmPollutants, ox, "ox", "getOx"), is(ox));
    }

    @Test
    void setOx() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutant ox = new BMPollutant();
        assertThat(TestUtils.set(this.bmPollutants, ox, BMPollutant.class, "ox", "setOx"), is(ox));
    }

    @Test
    void getNh3() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutant nh3 = new BMPollutant();
        assertThat(TestUtils.get(this.bmPollutants, nh3, "nh3", "getNh3"), is(nh3));
    }

    @Test
    void setNh3() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutant nh3 = new BMPollutant();
        assertThat(TestUtils.set(this.bmPollutants, nh3, BMPollutant.class, "nh3", "setNh3"), is(nh3));
    }

    @Test
    void getNmhc() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutant nmhc = new BMPollutant();
        assertThat(TestUtils.get(this.bmPollutants, nmhc, "nmhc", "getNmhc"), is(nmhc));
    }

    @Test
    void setNmhc() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutant nmhc = new BMPollutant();
        assertThat(TestUtils.set(this.bmPollutants, nmhc, BMPollutant.class, "nmhc", "setNmhc"), is(nmhc));
    }

    @Test
    void getNo() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutant no = new BMPollutant();
        assertThat(TestUtils.get(this.bmPollutants, no, "no", "getNo"), is(no));
    }

    @Test
    void setNo() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutant no = new BMPollutant();
        assertThat(TestUtils.set(this.bmPollutants, no, BMPollutant.class, "no", "setNo"), is(no));
    }

    @Test
    void getNox() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutant nox = new BMPollutant();
        assertThat(TestUtils.get(this.bmPollutants, nox, "nox", "getNox"), is(nox));
    }

    @Test
    void setNox() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutant nox = new BMPollutant();
        assertThat(TestUtils.set(this.bmPollutants, nox, BMPollutant.class, "nox", "setNox"), is(nox));
    }

    @Test
    void getTrs() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutant trs = new BMPollutant();
        assertThat(TestUtils.get(this.bmPollutants, trs, "trs", "getTrs"), is(trs));
    }

    @Test
    void setTrs() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        BMPollutant trs = new BMPollutant();
        assertThat(TestUtils.set(this.bmPollutants, trs, BMPollutant.class, "trs", "setTrs"), is(trs));
    }

    @Test
    void getPollutantByName() {
        this.bmPollutants.setCo(new BMPollutant());
        assertThat(this.bmPollutants.getPollutantByName("co"), is(this.bmPollutants.getCo()));
        assertThat(this.bmPollutants.getPollutantByName("trs"), is(nullValue()));
        assertThat(this.bmPollutants.getPollutantByName("invalid"), is(nullValue()));


    }
}