package sample.cafekiosk.client.mail;


import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.Comment;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class MailSendClient {
    public boolean sendEmail(String fromMail, String toMail, String subject, String content) {
        //메일 전송
      log.info("메일 전송");
      return true;
    }

    public void a(){
        log.info("a");
    }

    public void b(){
        log.info("b");
    }

    public void c(){
        log.info("c");
    }


}
