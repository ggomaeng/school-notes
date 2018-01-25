//-------------------------------------------------------------------------------------------------------------//
// Code based on a tutorial by Shekhar Gulati of SparkJava at
// https://blog.openshift.com/developing-single-page-web-applications-using-java-8-spark-mongodb-and-angularjs/
//-------------------------------------------------------------------------------------------------------------//

package com.oose2017.spark146.hareandhounds;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Game {

    private String gameId;

    private List<Player> players = new ArrayList<>();

    public Game(String pieceType) {

    }


    public String getGameId() {
        return gameId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Game game = (Game) o;

        if (gameId != null ? !gameId.equals(game.gameId) : game.gameId != null) return false;

//        if (playerId != null ? !playerId.equals(game.playerId) : game.playerId != null) return false;

//        if (pieceType != null ? !pieceType.equals(game.pieceType) : game.pieceType != null) return false;
//        return !(createdOn != null ? !createdOn.equals(todo.createdOn) : todo.createdOn != null);

        return false;
    }

    @Override
    public int hashCode() {
        int result = gameId != null ? gameId.hashCode() : 0;
//        result = 31 * result + (playerId != null ? playerId.hashCode() : 0);
//        result = 31 * result + (pieceType != null ? pieceType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId='" + gameId + '\'' +
//                ", playerId='" + playerId + '\'' +
//                ", pieceType='" + pieceType + '\'' +
                '}';
    }




}
