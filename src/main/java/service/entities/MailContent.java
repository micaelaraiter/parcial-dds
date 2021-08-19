package service.entities;

public class MailContent {
    private String subject;
    private String content;
    private String emailFrom;
    private String emailTo;

    public MailContent(String subject, String content, String emailFrom, String emailTo) {
        this.subject = subject;
        this.content = content;
        this.emailFrom = emailFrom;
        this.emailTo = emailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEmailFrom() {
        return emailFrom;
    }

    public void setEmailFrom(String emailFrom) {
        this.emailFrom = emailFrom;
    }

    public String getEmailTo() {
        return emailTo;
    }

    public void setEmailTo(String emailTo) {
        this.emailTo = emailTo;
    }
}
