package com.air_quality.backend.utils;

import com.air_quality.backend.TestUtils;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

class RequestToolsTest {

    private static final String URL = "https://jsonplaceholder.typicode.com/users";
    private RequestTools requestTools = RequestTools.getInstance();

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    void testGet() throws Exception {
        JSONAssert.assertEquals(requestTools.get(URL), TestUtils.readJsonAsString("src/test/java/com/air_quality/backend/json_files/RequestToolsTestJson.json"), false);
    }

    @Test
    void testBuild() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("search", "ansiao");
        String expected = "https://jsonplaceholder.typicode.com/users?search=ansiao";
        assertThat(requestTools.build(URL, params), is(expected));
    }

    @Test
    void testBuild_ThrowsURISyntaxException() {
        Map<String, String> params = new HashMap<>();
        String invalidChar = "^";
        assertThrows(URISyntaxException.class, () -> {
            requestTools.build(URL + invalidChar, params);
        });
    }

    @Test
    void testMakeRequest() throws Exception {
        Map<String, String> params = new HashMap<>();
        //params.put("search", "ansiao");
        JSONAssert.assertEquals(requestTools.makeRequest(URL, params), TestUtils.readJsonAsString("src/test/java/com/air_quality/backend/json_files/RequestToolsTestJson.json"), false);
    }

}
