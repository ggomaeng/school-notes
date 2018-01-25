package com.oose2017.spark146.hareandhounds;

public class Player {

    public static final String HARE = "HARE";
    public static final String HOUND = "HOUND";

    private String playerId;
    private String pieceType;

    public Player(String playerId, String pieceType) {
        this.playerId = playerId;
        this.pieceType = pieceType;
    }
}
