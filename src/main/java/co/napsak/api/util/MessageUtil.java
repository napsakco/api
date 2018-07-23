package co.napsak.api.util;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageUtil {

    private final ResourceBundleMessageSource messageSource;

    public MessageUtil() {
        this.messageSource = new ResourceBundleMessageSource();
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setBasenames("messages/messages");
    }

    public String getMessage(String messageCode) {
        return messageSource.getMessage(messageCode, new String[]{}, Locale.forLanguageTag("tr"));
    }
}
