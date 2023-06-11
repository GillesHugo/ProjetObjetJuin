
import java.util.concurrent.*;

public class Player{
    private String name;
    private int rank;
    private int score;
    private Card[] cardsDrown;

    private StatePlayer state;

    private StatePlayer statePlaying = new StatePlayerPlaying();
    private StatePlayer stateWaiting = new StatePlayerWaiting();
    private StatePlayer stateOneCardDrown = new StatePlayerOneCardDrown();
    private StatePlayer stateTwoCardsDrown = new StatePlayerTwoCardsDrown();


    public Player(String n, int r){
        name = n;
        score = 0;
        cardsDrown = new Card[2];
        rank = r;
        state = (rank % 2 == 0) ? stateWaiting : statePlaying;
    }

    public int isPlayerPlaying(){
        return (state != stateWaiting) ? rank : 0;
    }

    public StatePlayer GetState(){
        return state;
    }

    public void SwitchRole(){
        state = (state == stateWaiting) ? statePlaying : stateWaiting;
    }

    public Card[] GetCards(){
        return cardsDrown;
    }

    public String GetName(){
        return name;
    }

    public int GetScore(){
        return score;
    }

    public void Gain(int pts){
        score += pts;
        state = statePlaying;
    }

    public boolean IsWinning(){
        return cardsDrown[0].GetFront() == cardsDrown[1].GetFront();
    }

    public void PickCard(Card c){
        if(cardsDrown[0] == null){
            cardsDrown[0] = c;
            state = stateOneCardDrown;
            System.out.println("2 CARDS");
        }
        else if(cardsDrown[1] == null){
            cardsDrown[1] = c;
            state = stateTwoCardsDrown;
            System.out.println("1 CARD");
        }
        if(c == null){
            cardsDrown[0] = null;
            cardsDrown[1] = null;
            state = statePlaying;
            System.out.println("PLAY");
        }
    }
}