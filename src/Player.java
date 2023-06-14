

public class Player{
    private String name;
    private int rank;
    private int score;
    private Card[] cardsDrown;

    private enum StatePlayer{
        statePlaying,
        stateWaiting,
        stateOneCardDrown,
        stateTwoCardsDrown,
    }

    private StatePlayer state;

    public Player(String n, int r){
        name = n;
        score = 0;
        cardsDrown = new Card[2];
        rank = r;
        state = (rank % 2 == 0) ? StatePlayer.stateWaiting : StatePlayer.statePlaying;
    }

    public int isPlayerPlaying(){
        return (state != StatePlayer.stateWaiting) ? rank : 0;
    }

    public boolean hasPlayerTwoCards(){
        return state == StatePlayer.stateTwoCardsDrown;
    }

    public StatePlayer GetState(){
        return state;
    }

    public void SwitchRole(){
        state = (state == StatePlayer.stateWaiting) ? StatePlayer.statePlaying : StatePlayer.stateWaiting;
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
        state = StatePlayer.statePlaying;
    }

    public boolean IsWinning(){
        return cardsDrown[0].GetFront() == cardsDrown[1].GetFront();
    }

    public void PickCard(Card c){
        if(cardsDrown[0] == null){
            cardsDrown[0] = c;
            state = StatePlayer.stateOneCardDrown;
        }
        else if(cardsDrown[1] == null){
            cardsDrown[1] = c;
            state = StatePlayer.stateTwoCardsDrown;
        }
        if(c == null){
            cardsDrown[0] = null;
            cardsDrown[1] = null;
            state = StatePlayer.statePlaying;
        }
    }
}