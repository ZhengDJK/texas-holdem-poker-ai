package edu.ntnu.texasai.controller;

import edu.ntnu.texasai.model.*;

import javax.inject.Inject;
import java.util.List;

public class PlayerControllerPhaseI extends PlayerController {
    private final HandPowerRanker handPowerRanker;

    @Inject
    public PlayerControllerPhaseI(final HandPowerRanker handPowerRanker) {
        this.handPowerRanker = handPowerRanker;
    }

    @Override
    public BettingDecision decidePreFlop(Player player, GameHand gameHand, List<Card> cards) {
        Card card1 = cards.get(0);
        Card card2 = cards.get(1);

        if (card1.getNumber().equals(card2.getNumber())) {
            return BettingDecision.RAISE;
        } else if (card1.getNumber().getPower() + card2.getNumber().getPower() > 16 || canCheck(gameHand, player)) {
            return BettingDecision.CALL;
        } else {
            return BettingDecision.FOLD;
        }
    }

    private boolean canCheck(GameHand gameHand, Player player) {
        BettingRound bettingRound = gameHand.getCurrentBettingRound();
        return bettingRound.getHighestBet().equals(bettingRound.getBetForPlayer(player));
    }

    @Override
    public BettingDecision decideAfterFlop(Player player, GameHand gameHand, List<Card> cards) {
        HandPower handPower = handPowerRanker.rank(cards);

        HandPowerType handPowerType = handPower.getHandPowerType();
        if (handPowerType.equals(HandPowerType.HIGH_CARD)) {
            if(canCheck(gameHand, player)){
                return BettingDecision.CALL;
            }
            return BettingDecision.FOLD;
        } else if (handPowerType.getPower() >= HandPowerType.THREE_OF_A_KIND.getPower()) {
            return BettingDecision.RAISE;
        } else {
            return BettingDecision.CALL;
        }
    }
}
