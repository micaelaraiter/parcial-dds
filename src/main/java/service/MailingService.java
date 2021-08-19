package service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import service.entities.MailContent;

import java.io.IOException;

public class MailingService {
    public static void sendMail(MailContent mailContent) throws IOException {
        Email from = new Email(mailContent.getEmailFrom());
        String subject = mailContent.getSubject();
        Email to = new Email(mailContent.getEmailTo());
        Content content = new Content("text/plain", mailContent.getContent());
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw ex;
        }
    }
}
