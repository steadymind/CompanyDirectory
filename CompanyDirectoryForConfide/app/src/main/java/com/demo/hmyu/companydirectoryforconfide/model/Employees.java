package com.demo.hmyu.companydirectoryforconfide.model;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.IOException;
import java.util.List;

/**
 * Created by Hsiang-Min on 12/15/14.
 */
public class Employees {

    public List<Employee> employees;


    public static Employees parse(String result){
        if(result == null){
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(result, Employees.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
