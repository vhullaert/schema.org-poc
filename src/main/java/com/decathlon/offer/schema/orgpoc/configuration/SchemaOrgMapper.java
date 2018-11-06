package com.decathlon.offer.schema.orgpoc.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import ioinformarics.oss.jackson.module.jsonld.JsonldModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class SchemaOrgMapper {

    public ObjectMapper jsonLdObjectMapper() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // Here we register the JSON-LD serialization/deserialization module
        objectMapper.registerModule(new JsonldModule());
        return objectMapper;
    }

}
