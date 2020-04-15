package com.air_quality.backend.utils.cities_search_serializers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CitySearchResult {

    private String href;

    @JsonProperty("matching_full_name")
    private String matchingFullName;

    @JsonProperty("_links")
    private void setLink(Map<String, Map<String, String>> links) {
        this.href = links.get("city:item").get("href");
    }

    public String getHref() {
        return href;
    }

    public String getMatchingFullName() {
        return matchingFullName;
    }
}
