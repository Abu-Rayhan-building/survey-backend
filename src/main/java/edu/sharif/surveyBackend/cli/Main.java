package edu.sharif.surveyBackend.cli;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

// wtf? this doesn't work
@QuarkusMain
public class Main implements QuarkusApplication {

    public static void main(String[] args) {
	Quarkus.run(Main.class, args);
	Quarkus.waitForExit();

    }

    @Override
    public int run(String... args) throws Exception {
	// TODO Auto-generated method stub
	System.out.println("kk");
	return 0;
    }
}
