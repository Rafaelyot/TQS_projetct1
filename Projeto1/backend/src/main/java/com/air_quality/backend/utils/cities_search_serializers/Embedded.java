package com.air_quality.backend.utils.cities_search_serializers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Embedded {

    @JsonProperty("city:search-results")
    private List<CitySearchResult> citySearchResults;

    public List<CitySearchResult> getCitySearchResults() {
        return citySearchResults;
    }
}
