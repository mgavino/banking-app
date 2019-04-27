package com.mgavino.bankingrest.core;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Map;

public abstract class GenericControllerTests {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;

    protected <T> ResultActions post(String url, T requestObject) throws Exception {

        return mockMvc.perform(
                MockMvcRequestBuilders.post( url )
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestObject)));

    }

    protected <T> ResultActions get(String url) throws Exception {
        return get(url, null);
    }

    protected <T> ResultActions get(String url, Map<String, String> params) throws Exception {

        MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get( url )
                .contentType(MediaType.APPLICATION_JSON);

        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> param : params.entrySet()) {
                builder.param(param.getKey(), param.getValue());
            }
        }

        return mockMvc.perform(builder);

    }

    protected String checkStatus(ResultActions result, HttpStatus status) throws Exception{
        // check status
        return result.andExpect(MockMvcResultMatchers.status().is(status.value()))
                .andReturn().getResponse().getContentAsString();

    }

    protected <T> T checkStatusReturnObj(ResultActions result, HttpStatus status, Class<T> returnType) throws Exception {

        String contentResponse = checkStatus(result, status);
        return objectMapper.reader().forType(returnType)
                    .readValue(contentResponse);

    }

    protected <T> List<T> checkStatusReturnList(ResultActions result, HttpStatus status, Class<T> returnType) throws Exception {

        String contentResponse = checkStatus(result, status);
        return objectMapper.readValue(contentResponse, new TypeReference<List<T>>(){});

    }

}
