package edu.sharif.surveyBackend.utill;

import javax.ws.rs.core.Application;

import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;

@OpenAPIDefinition(info = @Info(title = "Survey API", version = "0.0.1", contact = @Contact(name = "hossein & sobhan & hamidreza & mohhamadhossein", url = "https://github.com/Abu-Rayhan-building", email = "hosseinmp76@hotmail.com"), description = "GG, WP"))
public class OpenApiConfig extends Application {
}