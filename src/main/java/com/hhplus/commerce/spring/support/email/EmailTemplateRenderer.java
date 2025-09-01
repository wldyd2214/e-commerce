package com.hhplus.commerce.spring.support.email;

import org.thymeleaf.context.Context;

public interface EmailTemplateRenderer {
    String renderWelcomeEmailTemplate(Context context);
}
