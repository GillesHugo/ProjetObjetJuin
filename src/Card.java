
import java.util.concurrent.*;
 public class Card{
    private char backSide = '*';
    private char frontSide;

    private StateCard stateDown = new StateCardDown();
    private StateCard stateUp = new StateCardUp();
    private StateCard stateOut = new StateCardOut();

    private StateCard state = stateDown;

    public Card(char frontSide){
        this.frontSide = frontSide;
    }

    public void ShowCard(){
        if(state == stateDown){
            state.ShowCard(backSide);
        }
        else if(state == stateUp){
            state.ShowCard(frontSide);
        }
        else if(state == stateOut){
            state.ShowCard(' ');
        }
    }

    public boolean IsDrawn(){
        return state == stateUp;
    }

    public char getFront(){
        return frontSide;
    }

    public void RemoveCard(){
        state = stateOut;
    }

    public void HideCard(){
        state = stateDown;
    }

    public void PlayerSelect(Card otherCard){

        if(state == stateDown){
            state = stateUp;
        }
        else if(state == stateUp){
            System.out.println("Card already used");
        }
        else if(state == stateOut){
            System.out.println("Card is out");
        }
    }

    public void DrawCard(){
        state = stateUp;
    }

    public void Update(Card otherCard){
        if(state == stateUp){
            if(otherCard!=null){
                if(otherCard.getFront() == this.frontSide){
                    RemoveCard();
                }
                else{
                    HideCard();
                }
            }
        }
    }
 }