package com.twu.biblioteca;

import com.twu.biblioteca.entity.*;
import com.twu.biblioteca.service.*;
import org.junit.Test;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class MovieServiceTest {
    private MovieService movieService = new MovieService();
    private List<Movie> allMovieList = movieService.addMoviesToList();
    private SystemReplyMessage systemReplyMessage = new SystemReplyMessage();
    private UserInputMessage userInputMessage = new UserInputMessage();
    private ExchangeMessage exchangeMessage = new ExchangeMessage();
    private SystemReplyMessageService systemReplyMessageService = new SystemReplyMessageService();

    @Test
    public void addMoviesToListTest(){
        String expectedMovie = "Big Hero 6";
        String expectedDirector = "Don Hall/Chris Williams";
        int expectedPublishedYear = 2014;
        int expectedRating = 8;
        assertEquals(expectedMovie, allMovieList.get(2).getName());
        assertEquals(expectedDirector, allMovieList.get(2).getDirector());
        assertEquals(expectedPublishedYear, allMovieList.get(2).getYear());
        assertEquals(expectedRating,allMovieList.get(2).getRating());
    }

    @Test
    public void showMovieListTest(){
        String expectedMessage = "1. Léon\n2. Spirited Away\n3. Big Hero 6\n";
        String actualMessage = movieService.getMovieList(allMovieList);
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void getMovieDetailsTest(){
        String expectedMessage = "1. Movie: Léon; Published Year: 1994; Director: Luc Besson; Rating: 9\n"+
                "2. Movie: Spirited Away; Published Year: 2001; Director: Hayao Miyazaki; Rating: 9\n"+
                "3. Movie: Big Hero 6; Published Year: 2014; Director: Don Hall/Chris Williams; Rating: 8\n";
        String actualMessage = movieService.getMovieDetails(allMovieList);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void validCheckoutMovieTest(){
        String expectedMessage = systemReplyMessage.getSuccessfulCheckoutMovieMessage();
        String inputMessage = userInputMessage.getCheckoutMovieMessage()+1;
        exchangeMessage = systemReplyMessageService.checkoutMovie(inputMessage, allMovieList);
        String actualMessage = exchangeMessage.getOutputMessage();
        List<Movie> actualMovieList = exchangeMessage.getMovieList();
        assertEquals(expectedMessage,actualMessage);
        assertEquals(2, actualMovieList.size());
    }

    @Test
    public void invalidCheckoutMovieTest() {
        String inputMessage = userInputMessage.getCheckoutMovieMessage()+"124";
        String expectedMessage = systemReplyMessage.getInvalidCheckoutMovieMessage();
        exchangeMessage = systemReplyMessageService.checkoutMovie(inputMessage, allMovieList);
        String actualMessage = exchangeMessage.getOutputMessage();
        assertEquals(expectedMessage,actualMessage);
    }

    @Test
    public void validReturnMovieTest(){
        String checkoutMessage = userInputMessage.getCheckoutMovieMessage()+1;
        List<Movie> checkoutMovieList = systemReplyMessageService.checkoutMovie(checkoutMessage, allMovieList).getMovieList();
        String inputMessage = userInputMessage.getReturnMovieMessage()+1;
        exchangeMessage = systemReplyMessageService.returnMovie(inputMessage, checkoutMovieList);
        List<Movie> returnMovieList = exchangeMessage.getMovieList();
        assertEquals(allMovieList, returnMovieList);
    }

    @Test
    public void invalidReturnMovieTest(){
        String expectedMessage = systemReplyMessage.getInvalidReturnMovieMessage();
        String inputMessage = userInputMessage.getReturnMovieMessage()+"a";
        exchangeMessage = systemReplyMessageService.returnMovie(inputMessage, allMovieList);
        String actualMessage = exchangeMessage.getOutputMessage();
        assertEquals(expectedMessage,actualMessage);
    }


}
