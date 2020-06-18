package edu.sharif.surveyBackend.utill;

import javax.inject.Inject;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;

public class MailingService {
    @Inject
    Mailer mailer;

    public void send() {
	mailer.send(Mail.withText("to@acme.org", "A simple email from quarkus",
		"This is my body").addAttachment("my-file.txt",
			"content of my file".getBytes(), "text/plain"));
    }
}
