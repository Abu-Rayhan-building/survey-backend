package edu.sharif.surveyBackend.cli;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;

//@QuarkusMain(name = "addCourse")
public class addCourse implements QuarkusApplication {

    public static void main(String[] args) {
	Quarkus.run(addCourse.class, args);

    }

    @Override
    public int run(String... args) throws Exception {
	return 0;
    }

}
