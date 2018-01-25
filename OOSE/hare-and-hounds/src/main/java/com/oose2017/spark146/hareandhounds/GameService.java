//-------------------------------------------------------------------------------------------------------------//
// Code based on a tutorial by Shekhar Gulati of SparkJava at
// https://blog.openshift.com/developing-single-page-web-applications-using-java-8-spark-mongodb-and-angularjs/
//-------------------------------------------------------------------------------------------------------------//

package com.oose2017.spark146.hareandhounds;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import javax.sql.DataSource;
import java.util.List;

public class GameService {

    private Sql2o db;

    private final Logger logger = LoggerFactory.getLogger(GameService.class);

    /**
     * Construct the model with a pre-defined datasource. The current implementation
     * also ensures that the DB schema is created if necessary.
     *
     * @param dataSource
     */
    public GameService(DataSource dataSource) throws GameServiceException {
        db = new Sql2o(dataSource);

        //Create the schema for the database if necessary. This allows this
        //program to mostly self-contained. But this is not always what you want;
        //sometimes you want to create the schema externally via a script.
        try (Connection conn = db.open()) {
            String sql = "CREATE TABLE IF NOT EXISTS game (gameId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "                                 playerId TEXT, pieceType TEXT)" ;
            conn.createQuery(sql).executeUpdate();
        } catch(Sql2oException ex) {
            logger.error("Failed to create schema at startup", ex);
            throw new GameServiceException("Failed to create schema at startup", ex);
        }
    }

    public Game startGame(String body) throws GameServiceException {
        Game game = new Gson().fromJson(body, Game.class);

        String sql = "INSERT INTO game (gameId, playerId, pieceType) " +
                "             VALUES (:gameId, :playerId, :pieceType)" ;

        try (Connection conn = db.open()) {
            conn.createQuery(sql)
                    .bind(game)
                    .executeUpdate();
        } catch(Sql2oException ex) {
            logger.error("GameService.startGame: Failed to start a new game", ex);
            throw new GameServiceException("GameService.startGame: Failed to start a new game", ex);
        }

        return game;
    }


    public void joinGame(String gameId, String body) throws GameServiceException {
        Game game = new Gson().fromJson(body, Game.class);

        String sql = "INSERT INTO game (gameId, playerId, pieceType) " +
                "             VALUES (:gameId, :playerId, :pieceType)" ;

        try (Connection conn = db.open()) {
            conn.createQuery(sql)
                    .bind(game)
                    .executeUpdate();
        } catch(Sql2oException ex) {
            logger.error("GameService.startGame: Failed to start a new game", ex);
            throw new GameServiceException("GameService.startGame: Failed to start a new game", ex);
        }
    }





    //-----------------------------------------------------------------------------//
    // Helper Classes and Methods
    //-----------------------------------------------------------------------------//

    public static class GameServiceException extends Exception {
        public GameServiceException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    /**
     * This Sqlite specific method returns the number of rows changed by the most recent
     * INSERT, UPDATE, DELETE operation. Note that you MUST use the same connection to get
     * this information
     */
    private int getChangedRows(Connection conn) throws Sql2oException {
        return conn.createQuery("SELECT changes()").executeScalar(Integer.class);
    }
}
