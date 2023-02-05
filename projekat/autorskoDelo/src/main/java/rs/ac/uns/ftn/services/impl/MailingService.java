package rs.ac.uns.ftn.services.impl;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MailingService {

    @Autowired
    private JavaMailSender mailSender;

    private final Path templatesLocation;

    @Autowired
    public MailingService() {
        templatesLocation = Paths.get(".\\src\\main\\resources\\email_templates");
    }

    @Async
    public void sendEmailResenje(Map<String, String> data, File attachment) throws IOException {
        String content = renderTemplate("resenje.html",
                "name", data.get("name"),
                "resenjeType", data.get("resenjeType"),
                "zahtevNumber", data.get("zahtevNumber")
                );

        sendMail(data.get("email"), "Resenje - zahtev za autorsko delo", content, attachment);
    }

    private void sendMail(String to, String subject, String body, File attachment) throws IOException {
        try {
          MimeMessage message = mailSender.createMimeMessage();
          MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
          helper.setText(body, true);
          helper.setTo(to);
          helper.setSubject(subject);
          helper.addAttachment("resenje.pdf", attachment);
          mailSender.send(message);
        } catch (MessagingException e) {
        	e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private String renderTemplate(String templateName, String... variables) {
        Map<String, String> variableMap = new HashMap<>();

        List<String> keyValueList = Arrays.stream(variables).collect(Collectors.toList());

        if (keyValueList.size() % 2 != 0)
            throw new IllegalArgumentException();

        for (int i = 0; i < keyValueList.size(); i += 2) {
            variableMap.put(keyValueList.get(i), keyValueList.get(i + 1));
        }

        return renderTemplate(templateName, variableMap);
    }

    private String renderTemplate(String templateName, Map<String, String> variables) {
        File file = templatesLocation.resolve(templateName).toFile();
        String message = null;
        try {
            message = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String target, renderedValue;
        for (Map.Entry<String, String> entry : variables.entrySet()) {
			 target = "\\{\\{ " + entry.getKey() + " \\}\\}";
			 renderedValue = entry.getValue();
			 message = message.replaceAll(target, renderedValue);
		}
        return message;
    }


}
