package com.air_quality.backend.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public class RequestTools {

    private static RequestTools instance = new RequestTools();

    private RequestTools() {

    }

    public static RequestTools getInstance() {
        return instance;
    }

    public String get(String url) throws IOException {
        HttpGet request = new HttpGet(url);

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            try (CloseableHttpResponse response = client.execute(request)) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity);
            }
        }
    }

    public String build(String url, Map<String, String> params) throws URISyntaxException {
        URIBuilder uriBuilder = new URIBuilder(url);

        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            uriBuilder.addParameter(key, value);
        }
        return uriBuilder.build().toString();
    }


    public String makeRequest(String url, Map<String, String> params) throws URISyntaxException, IOException {
        return get(build(url, params));

    }




}
