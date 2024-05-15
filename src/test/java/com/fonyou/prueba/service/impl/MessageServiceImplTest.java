package com.fonyou.prueba.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class MessageServiceImplTest {

    @Mock
    private MessageSource messageSource;

    private MessageServiceImpl messageService;

    @BeforeEach
    void setUp() throws Exception {
        try(AutoCloseable ignored = MockitoAnnotations.openMocks(this)) {
            messageService = new MessageServiceImpl(messageSource);
        }
    }

    @Test
    void getMessage() {
        String messageKey = "test.message";
        String expectedMessage = "This is a test message";
        when(messageSource.getMessage(eq(messageKey), eq(null), any(Locale.class))).thenReturn(expectedMessage);

        String actualMessage = messageService.getMessage(messageKey);

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testGetMessageWithLocale() {
        String messageKey = "test.message";
        String expectedMessage = "This is a test message";
        Locale locale = LocaleContextHolder.getLocale();
        when(messageSource.getMessage(eq(messageKey), eq(null), eq(locale))).thenReturn(expectedMessage);

        String actualMessage = messageService.getMessage(messageKey, locale);

        assertEquals(expectedMessage, actualMessage);
    }
}