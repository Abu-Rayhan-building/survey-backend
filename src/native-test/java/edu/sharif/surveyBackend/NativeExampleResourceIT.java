package edu.sharif.surveyBackend;

import io.quarkus.test.junit.NativeImageTest;
import edu.sharif.surveyBackend.api.QuestionAPITest;

@NativeImageTest
public class NativeExampleResourceIT extends QuestionAPITest {

    // Execute the same tests but in native mode.
}