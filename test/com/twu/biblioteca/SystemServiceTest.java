package com.twu.biblioteca;

import com.twu.biblioteca.entity.*;
import com.twu.biblioteca.service.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SystemServiceTest {

    private SystemReplyMessageService systemReplyMessageService = new SystemReplyMessageService();
    private SystemReplyMessage systemReplyMessage = new SystemReplyMessage();
    private ExchangeMessage exchangeMessage = new ExchangeMessage();

    @Test
    public void showWelcomeMessageTest(){
        String expectedMessage = systemReplyMessage.getWelcomeMessage();
        exchangeMessage = systemReplyMessageService.showWelcomeMessage();
        String actualMessage = exchangeMessage.getOutputMessage();
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void showQuitMessageTest() {
        String expectedMessage = systemReplyMessage.getQuitMessage();
        exchangeMessage = systemReplyMessageService.showQuitMessage();
        String actualMessage = exchangeMessage.getOutputMessage();
        assertEquals(expectedMessage,actualMessage);
    }



    @Test
    public void showMainMenuTest() {
        String expectedMessage = systemReplyMessage.getMenuOptions();
        exchangeMessage = systemReplyMessageService.showMenuOptions();
        String actualMessage = exchangeMessage.getOutputMessage();
        assertEquals(expectedMessage,actualMessage);
    }




}
