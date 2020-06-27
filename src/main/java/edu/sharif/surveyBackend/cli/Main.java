package edu.sharif.surveyBackend.cli;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;

// wtf? this doesn't work
//@QuarkusMain(name = "")
public class Main {

    public static void main(String[] args) {
	Quarkus.run(Test.class, args);
	Quarkus.waitForExit();

    }

}

@QuarkusMain(name = "wtf")
class Test implements QuarkusApplication {
    @Override
    public int run(String... args) throws Exception {
	// TODO Auto-generated method stub
	System.out.println("kk");
	return 0;
    }
}
