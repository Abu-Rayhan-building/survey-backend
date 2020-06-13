package edu.sharif.surveyBackend.utill;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.jackson.ObjectMapperCustomizer;
import javax.inject.Singleton;

@Singleton
public class RegisterCustomModuleCustomizer implements ObjectMapperCustomizer {

    public void customize(ObjectMapper mapper) {
//        mapper.registerModule(new CustomModule());
    }
}