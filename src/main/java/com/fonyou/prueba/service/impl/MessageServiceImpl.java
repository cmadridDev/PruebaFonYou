package com.fonyou.prueba.service.impl;

import com.fonyou.prueba.config.Messages;
import com.fonyou.prueba.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@AllArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageSource messageSource;

    public String getMessage(String message) {
        return messageSource.getMessage(message, null, LocaleContextHolder.getLocale());
    }

    public String getMessage(String message, Locale locale) {
        return messageSource.getMessage(message, null, locale);
    }
}
