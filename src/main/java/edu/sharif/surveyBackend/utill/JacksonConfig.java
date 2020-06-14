package edu.sharif.surveyBackend.utill;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ObjectNode;

import edu.sharif.surveyBackend.model.survey.question.Question;
import edu.sharif.surveyBackend.model.survey.reply.Reply;
import io.quarkus.jackson.ObjectMapperCustomizer;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Singleton;

@Singleton
public class JacksonConfig implements ObjectMapperCustomizer {

    public void customize(ObjectMapper mapper) {
	// mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
	// false);
	SimpleModule module = new SimpleModule();
	module.addDeserializer(Reply.class,
		new PresentPropertyPolymorphicDeserializer<>(Reply.class));
	module.addDeserializer(Question.class,
		new PresentPropertyPolymorphicDeserializer<>(Question.class));
	mapper.registerModule(module);
    }
}

class PresentPropertyPolymorphicDeserializer<T> extends StdDeserializer<T> {

    private final Map<String, Class<?>> propertyNameToType;

    public PresentPropertyPolymorphicDeserializer(Class<T> vc) {
	super(vc);
	this.propertyNameToType = Arrays
		.stream(vc.getAnnotation(JsonSubTypes.class).value())
		.collect(Collectors.toMap(Type::name, Type::value, (a, b) -> a,
			LinkedHashMap::new)); // LinkedHashMap to support
					      // precedence case by definition
					      // order
    }

    @Override
    public T deserialize(JsonParser p, DeserializationContext ctxt)
	    throws IOException {
	ObjectMapper objectMapper = (ObjectMapper) p.getCodec();
	ObjectNode object = objectMapper.readTree(p);
	for (String propertyName : propertyNameToType.keySet()) {
	    if (object.has(propertyName)) {
		return deserialize(objectMapper, propertyName, object);
	    }
	}

	throw new IllegalArgumentException(
		"could not infer to which class to deserialize " + object);
    }

    @SuppressWarnings("unchecked")
    private T deserialize(ObjectMapper objectMapper, String propertyName,
	    ObjectNode object) throws IOException {
	return (T) objectMapper.treeToValue(object,
		propertyNameToType.get(propertyName));
    }
}