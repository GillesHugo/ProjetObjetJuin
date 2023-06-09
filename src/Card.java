
import javax.swing.*;
import java.awt.event.ActionListener;


 public class Card{
    private String backSide;
    private String frontSide;

    private int indexOfCard;

    private JButton btn;

    private Icon backImg;
    private Icon frontImg;

    private enum StateCard{
        stateDown,
        stateUp,
        stateOut
    }

    private StateCard state = StateCard.stateDown;

    public Card(ActionListener al, String backSide, String frontSide, int idx){
        this.backSide = backSide;
        this.frontSide = frontSide;
        backImg = new ImageIcon(backSide);
        frontImg = new ImageIcon(frontSide);
        indexOfCard = idx;
        btn = new JButton(backImg);
        btn.addActionListener(al);
    }

    public JButton getButton(){
        return btn;
    }

    public void ShowCard(){
        if(state == StateCard.stateDown){
            btn.setIcon(backImg);
        }
        else if(state == StateCard.stateUp){
            btn.setIcon(frontImg);
        }
        else if(state == StateCard.stateOut){
            btn.setVisible(false);;
        }
    }

    public boolean IsDrawn(){
        return state == StateCard.stateUp;
    }

    public int GetFront(){
        return indexOfCard;
    }

    public void RemoveCard(){
        state = StateCard.stateOut;
        btn.setVisible(false);
    }

    public void HideCard(){
        state = StateCard.stateDown;
        btn.setIcon(backImg);
    }

    public void DrawCard(){
        state = StateCard.stateUp;
        btn.setIcon(frontImg);
    }

    public void PlayerSelect(Card otherCard){
        if(state == StateCard.stateDown){
            state = StateCard.stateUp;
        }
    }
 }