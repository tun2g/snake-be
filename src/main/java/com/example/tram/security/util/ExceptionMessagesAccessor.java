package com.example.tram.security.util;

import com.example.tram.util.ProjectConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Objects;

@Service
public class ExceptionMessagesAccessor {
    private final MessageSource messageSource;

    ExceptionMessagesAccessor(@Qualifier("exceptionMessageSource") MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String getMessage(Locale locale, String key, Object... parameter) {

        if (Objects.isNull(locale)) {
            return messageSource.getMessage(key, parameter, ProjectConstants.DEFAUL_LOCALE);
        }

        return messageSource.getMessage(key, parameter, locale);
    }
}
