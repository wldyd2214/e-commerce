package com.hhplus.commerce.spring.support.email;

public interface EmailSender {
    boolean sendEmail(String to, String subject, String content);
}
