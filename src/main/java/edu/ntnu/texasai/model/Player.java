package edu.ntnu.texasai.model;

import edu.ntnu.texasai.controller.PlayerController;
import edu.ntnu.texasai.model.cards.Card;
import edu.ntnu.texasai.utils.MySocket;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class Player {
    private final int number;
    private  PlayerController playerController;
    private int money;
    private List<Card> holeCards;
    private MySocket socket;

    public Player(int number, int initialMoney,
            PlayerController playerController) {
        this.number = number;
        this.money = initialMoney;
        this.playerController = playerController;
    }

    public Player(int number,int initialMoney,Socket socket) throws IOException {
        this.number=number;
        this.money=initialMoney;
        this.socket=new MySocket(socket);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Player)) {
            return false;
        }

        Player otherPlayer = (Player) o;

        return number == otherPlayer.number;
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Player #");
        stringBuilder.append(getNumber());

        if (holeCards != null) {
            stringBuilder.append(holeCards.toString());
        }

        return stringBuilder.toString();
    }

    public BettingDecision decide(GameHand gameHand) {
        return playerController.decide(this, gameHand);
    }

    public int getNumber() {
        return number;
    }

    public int getMoney() {
        return money;
    }

    public void removeMoney(int amount) {
        money -= amount;
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public void setHoleCards(Card hole1, Card hole2) {
        holeCards = Arrays.asList(hole1, hole2);
    }

    public List<Card> getHoleCards() {
        return holeCards;
    }

    public PlayerController getPlayerController() {
        return playerController;
    }
}
