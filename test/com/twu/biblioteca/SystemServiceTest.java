package com.twu.biblioteca;

import com.twu.biblioteca.entity.*;
import com.twu.biblioteca.service.*;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class SystemServiceTest {

    private SystemReplyMessageService systemReplyMessageService = new SystemReplyMessageService();
    private SystemReplyMessage systemReplyMessage = new SystemReplyMessage();
    private ExchangeMessage exchangeMessage = new ExchangeMessage();
    private UserService userService = new UserService();
    private List<UserAccount> allUserAccountList = userService.addUserAccount();

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

    @Test
    public void userLoginTest(){
        String expectedMessage = systemReplyMessage.getWelcomeMessage();
        String inputMessage = "123-4567 vivi";
        exchangeMessage = systemReplyMessageService.loginService(inputMessage,allUserAccountList);
        String actualMessage = exchangeMessage.getOutputMessage();
        assertEquals(expectedMessage,actualMessage);
    }


}
