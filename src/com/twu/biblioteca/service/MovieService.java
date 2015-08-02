package com.twu.biblioteca.service;

import com.twu.biblioteca.entity.Movie;
import java.util.ArrayList;
import java.util.List;

public class MovieService {
    private Movie movie = new Movie();

    public List<Movie> addMoviesToList(){
        List<Movie> movieList = new ArrayList<>();
        Movie movie1 = new Movie(1,"Léon",1994,"Luc Besson",9);
        movieList.add(movie1);
        Movie movie2 = new Movie(2,"Spirited Away",2001,"Hayao Miyazaki",9);
        movieList.add(movie2);
        Movie movie3 = new Movie(3,"Big Hero 6",2014,"Don Hall/Chris Williams",8);
        movieList.add(movie3);
        return movieList;
    }

    public String getMovieList(List<Movie> movieList){
        String movieListToString = "";
        for (Movie movie:movieList) {
            movieListToString = movieListToString+movie.getId()+". "+movie.getName()+"\n";
        }
        return movieListToString;
    }

    public String getMovieDetails(List<Movie> movieList) {
        String movieDetails="";
        for (Movie movie: movieList) {
            movieDetails = movieDetails+movie.getId() + ". Movie: " + movie.getName() +
                    "; Published Year: " + movie.getYear() + "; Director: " + movie.getDirector() +
                    "; Rating: " + movie.getRating()+"\n";
        }
        return movieDetails;
    }

    public String getMovieIndex(String inputMessage,String orderMessage,List<Movie> movieList){
        int orderLength = orderMessage.length();
        String movieIndex = inputMessage.substring(orderLength);
        if (checkMovieIndexValid(movieIndex)) {
            for (Movie movie : movieList) {
                if (movie.getId() == Integer.parseInt(movieIndex)) {
                    return movieIndex;
                }
            }
        }
        return null;
    }

    public boolean checkMovieIndexValid (String inputMovieIndex) {
        char[] charArray = inputMovieIndex.toCharArray();
        for (int i=0;i<charArray.length;i++) {
            byte ascii = (byte) charArray[i];
            if (ascii < 48 || ascii > 57) {
                return false;
            }
        }
        return true;
    }

    public List<Movie> checkoutMovie(String inputMessage,String orderMessage,List<Movie> movieList) {
        String movieIndex = getMovieIndex(inputMessage, orderMessage, movieList);
        if (movieIndex != null) {
            for (Movie movie : movieList) {
                if (movie.getId() == Integer.parseInt(movieIndex)) {
                    movieList.remove(movie);
                    return movieList;
                }
            }
        }
        return null;
    }

    public List<Movie> returnMovie(String inputMessage,String orderMessage,List<Movie> movieList) {
        List<Movie> allMovieList = addMoviesToList();
        String movieIndex = getMovieIndex(inputMessage, orderMessage, allMovieList);
        if (movieIndex != null && !checkReturnMovieOnShelf(movieIndex, movieList)) {
            movie = getMovieDetailsById(movieIndex, allMovieList);
            movieList.add(movie);
            return movieList;
        }
        return null;
    }

    public boolean checkReturnMovieOnShelf(String movieIndex,List<Movie> movieList){
        for (Movie movie : movieList) {
            if (movie.getId()==Integer.parseInt(movieIndex)) {
                return true;
            }
        }
        return false;
    }

    public Movie getMovieDetailsById(String movieIndex,List<Movie> allmovieList) {
        for (Movie movie : allmovieList) {
            if (movie.getId() == Integer.parseInt(movieIndex)) {
                return movie;
            }
        }
        return null;
    }


}
