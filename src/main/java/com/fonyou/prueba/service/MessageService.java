package com.fonyou.prueba.service;

import com.fonyou.prueba.config.Messages;

import java.util.Locale;

public interface MessageService extends Messages {
    public String getMessage(String message);
    public String getMessage(String message, Locale locale);
}
