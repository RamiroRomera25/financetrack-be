package project.financetrack.services.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.w3c.tidy.Tidy;
import project.financetrack.dtos.email.CreateEmailDTO;
import project.financetrack.dtos.user.UserDTO;
import project.financetrack.enums.EmailType;
import project.financetrack.services.EmailService;
import project.financetrack.utils.EmailVariableUtils;

import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    private final Tidy tidy;

    @Value("${spring.mail.username}")
    private String EMAIL_FROM;

    @Value("${mail.message.welcome}")
    private String EMAIL_WELCOME;


    @Override
    public void welcomeMail(UserDTO user) {
        CreateEmailDTO createEmailDTO = new CreateEmailDTO();
        createEmailDTO.setEmailType(EmailType.WELCOME);
        createEmailDTO.setRecipient(user.getEmail());

        Map<String, String> variables = new HashMap<>();

        variables.put("fullName", user.getFirstName() + user.getLastName());

        createEmailDTO.setVariables(variables);
        this.sendEmailWithTemplate(createEmailDTO);
    }

    private void sendEmailWithTemplate(CreateEmailDTO createEmailDTO) {
        String emailTemplate = switch (createEmailDTO.getEmailType()) {
            case WELCOME -> new String(Base64.getDecoder().decode(EMAIL_WELCOME), StandardCharsets.UTF_8);
            default -> throw new IllegalArgumentException("Email type not supported");
        };

        String processedBody = this.processTemplate(emailTemplate, createEmailDTO.getVariables());
        this.sendEmail(createEmailDTO.getRecipient(), EMAIL_FROM, processedBody);
    }

    private String processTemplate(String template, Map<String, String> variables) {
        Set<String> requiredKeys = EmailVariableUtils.extractPlaceholders(template);
        Set<String> providedKeys = variables.keySet();

        if (!providedKeys.containsAll(requiredKeys)) {
            throw new IllegalArgumentException("Missing required template variables: " +
                    requiredKeys.stream()
                            .filter(key -> !providedKeys.contains(key))
                            .collect(Collectors.joining(", ")));
        }

        String processedTemplate = template;
        for (String key : requiredKeys) {
            processedTemplate = processedTemplate.replace("{{" + key + "}}", variables.get(key));
        }

        return processedTemplate;
    }

    /**
     * Sends an email using the JavaMailSender.
     * @param to recipient's email address
     * @param subject subject of the email
     * @param body body of the email
     */
    @SneakyThrows
    private void sendEmail(String to, String subject, String body) {
        if (!validateHTML(body)) {
            throw new MessagingException("Error sending email, the HTML body is INVALID");
        }

        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(body, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setFrom(EMAIL_FROM);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new MessagingException("Error sending email", e);
        }
    }

    /**
     * This method validates if the html sent is valid or not.
     * @param html which represents the HTML string
     * @return if it's valid or not
     */
    private Boolean validateHTML(String html) {
        try (StringReader input = new StringReader(html);
             StringWriter output = new StringWriter()) {
            tidy.parse(input, output);
            return tidy.getParseErrors() <= 0;
        } catch (Exception e) {
            return false;
        }
    }
}
