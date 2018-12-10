package com.nolting.second;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Service
public class GenreService {

    private static Logger logger;

    static {
        try {
            logger = GenreLogger.getLogger();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Genre> genres = null;

    public List<Genre> getAllGenres() {
        try {
            Connection connection = GenreConnection.getConnection();
            genres = new ArrayList<Genre>();

            Statement sql = connection.createStatement();
            ResultSet resultSet = sql.executeQuery("SELECT * FROM GENRES");

            while (resultSet.next()) {
                String name = resultSet.getString("Name");
                String rating = resultSet.getString("Rating");

                logger.info("Added genre: " + name);
                Genre genre = new Genre();
                genre.setName(name);
                genre.setRating(rating);

                genres.add(genre);
            }
        } catch (SQLException sqle) {
            logger.warning("Something went wrong when getting list of genres");
            return null;
        }
        logger.info("genre list populated");
        return genres;
    }

    public Genre getGenre(String id) {
        //this.getAllGenres();
        if(genres.size() > 0) {
            logger.info("Checking list for genre");
            return genres.stream().filter(g -> g.getName()
                    .contains(id))
                    .findFirst()
                    .get();
        } else {
            logger.info("given genre not found");
            return null;
        }
    }

    public void addGenre(Genre genre) {
        try {
            Connection connection = GenreConnection.getConnection();
            Statement sql = connection.createStatement();
            sql.execute(
                    "INSERT INTO GENRES VALUES ('" + genre.getName() + "'," +
                            "'" + genre.getRating() + "')"
            );
            logger.info("Event: " + genre.getName() + "added to DB");
        } catch (SQLException sqle) {
            logger.warning("Failed at Add Event\n "+
                    sqle.getMessage());
        }
    }

    public void updateGenre(Genre genre, String id) {
        try {
            Connection connection = GenreConnection.getConnection();
            Statement sql = connection.createStatement();

            sql.execute("UPDATE GENRES" +
                    " Set NAME ='" + genre.getName() +
                    "', Rating='" + genre.getRating() +
                    "' WHERE Name='" + id + "'"
            );
            logger.info("Event: " + genre.getName() + " added to DB");
        } catch (SQLException sqle) {
            logger.warning("Failed at: Update Event\n" +
                    sqle.getMessage());
        }
    }

    public void deleteGenre(String id) {
        try {
            Connection connection = GenreConnection.getConnection();
            Statement sql = connection.createStatement();

            sql.execute("DELETE FROM GENRES WHERE NAME ='" +
                    id + "'");
            logger.info("Event: " + id +
                    "deleted from db.");
        } catch (SQLException sqle) {
            logger.warning("Failed At: Delete Event\n" +
                    sqle.getMessage()
            );
        }
    }
}

