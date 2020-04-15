package com.air_quality.backend.utils.breezometer_serializers;

import com.air_quality.backend.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class BMHealthRecommendationsTest {

    private BMHealthRecommendations bmHealthRecommendations;

    @BeforeEach
    void setUp() {
        bmHealthRecommendations = new BMHealthRecommendations();
    }

    @Test
    void getGeneralPopulation() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String generalPopulation = "generalPopulation";
        assertThat(TestUtils.get(this.bmHealthRecommendations, generalPopulation, "generalPopulation", "getGeneralPopulation"), is(generalPopulation));
    }

    @Test
    void setGeneralPopulation() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String generalPopulation = "generalPopulation";
        assertThat(TestUtils.set(this.bmHealthRecommendations, generalPopulation, String.class, "generalPopulation", "setGeneralPopulation"), is(generalPopulation));

    }

    @Test
    void getElderly() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String elderly = "elderly";
        assertThat(TestUtils.get(this.bmHealthRecommendations, elderly, "elderly", "getElderly"), is(elderly));

    }

    @Test
    void setElderly() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String elderly = "elderly";
        assertThat(TestUtils.set(this.bmHealthRecommendations, elderly, String.class, "elderly", "setElderly"), is(elderly));
    }

    @Test
    void getLungDiseases() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String lungDiseases = "lungDiseases";
        assertThat(TestUtils.get(this.bmHealthRecommendations, lungDiseases, "lungDiseases", "getLungDiseases"), is(lungDiseases));
    }

    @Test
    void setLungDiseases() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String lungDiseases = "lungDiseases";
        assertThat(TestUtils.set(this.bmHealthRecommendations, lungDiseases, String.class, "lungDiseases", "setLungDiseases"), is(lungDiseases));

    }

    @Test
    void getHeartDiseases() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String heartDiseases = "heartDiseases";
        assertThat(TestUtils.get(this.bmHealthRecommendations, heartDiseases, "heartDiseases", "getHeartDiseases"), is(heartDiseases));
    }

    @Test
    void setHeartDiseases() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String heartDiseases = "heartDiseases";
        assertThat(TestUtils.set(this.bmHealthRecommendations, heartDiseases, String.class, "heartDiseases", "setHeartDiseases"), is(heartDiseases));

    }

    @Test
    void getActive() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String active = "active";
        assertThat(TestUtils.get(this.bmHealthRecommendations, active, "active", "getActive"), is(active));
    }

    @Test
    void setActive() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String active = "active";
        assertThat(TestUtils.set(this.bmHealthRecommendations, active, String.class, "active", "setActive"), is(active));
    }

    @Test
    void getPregnantWomen() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String pregnantWomen = "pregnantWomen";
        assertThat(TestUtils.get(this.bmHealthRecommendations, pregnantWomen, "pregnantWomen", "getPregnantWomen"), is(pregnantWomen));
    }

    @Test
    void setPregnantWomen() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String pregnantWomen = "pregnantWomen";
        assertThat(TestUtils.set(this.bmHealthRecommendations, pregnantWomen, String.class, "pregnantWomen", "setPregnantWomen"), is(pregnantWomen));

    }

    @Test
    void getChildren() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String children = "children";
        assertThat(TestUtils.get(this.bmHealthRecommendations, children, "children", "getChildren"), is(children));
    }

    @Test
    void setChildren() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, NoSuchFieldException {
        String children = "children";
        assertThat(TestUtils.set(this.bmHealthRecommendations, children, String.class, "children", "setChildren"), is(children));
    }
}