package edu.sharif.surveyBackend.cli;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

//@QuarkusMain(name = "addCourse")
public class addCourse implements QuarkusApplication {

    public static void main(String[] args) {
	Quarkus.run(addCourse.class, args);

    }

    @Override
    public int run(String... args) throws Exception {
	// TODO Auto-generated method stub
	return 0;
    }

}
