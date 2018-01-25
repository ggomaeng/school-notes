//-------------------------------------------------------------------------------------------------------------//
// Code based on a tutorial by Shekhar Gulati of SparkJava at
// https://blog.openshift.com/developing-single-page-web-applications-using-java-8-spark-mongodb-and-angularjs/
//-------------------------------------------------------------------------------------------------------------//

package com.oose2017.spark146.hareandhounds;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

import static spark.Spark.*;

public class GameController {

    private static final String API_CONTEXT = "/hareandhounds/api";

    private final GameService gameService;

    private final Logger logger = LoggerFactory.getLogger(GameController.class);

    public GameController(GameService gameService) {
        this.gameService = gameService;
        setupEndpoints();
    }

    private void setupEndpoints() {
        post(API_CONTEXT + "/games", "application/json", (request, response) -> {
            try {
                response.status(201);
                return gameService.startGame(request.body());
            } catch (GameService.GameServiceException ex) {
                logger.error("Failed to create new entry");
                response.status(500);
                return Collections.EMPTY_MAP;
            }
        }, new JsonTransformer());

         put(API_CONTEXT + "/games/:gameId", "application/json", (request, response) -> {
            try {
                response.status(200);
                gameService.joinGame(request.params(":gameId"), request.body());
            } catch (GameService.GameServiceException ex) {
                logger.error("Failed to create new entry");
                response.status(500);
            }
             return Collections.EMPTY_MAP;
        }, new JsonTransformer());



    }
}
