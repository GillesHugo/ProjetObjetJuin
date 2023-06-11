
import java.util.concurrent.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


 public class Card implements ActionListener{
    private String backSide;
    private String frontSide;

    private JButton btn;

    private Icon backImg;
    private Icon frontImg;

    private StateCard stateDown = new StateCardDown();
    private StateCard stateUp = new StateCardUp();
    private StateCard stateOut = new StateCardOut();

    private StateCard state = stateDown;

    public Card(ActionListener al, String backSide, String frontSide){
        this.backSide = backSide;
        this.frontSide = frontSide;
        backImg = new ImageIcon(backSide);
        frontImg = new ImageIcon(frontSide);

        btn = new JButton(backImg);
        btn.addActionListener(al);
        btn.addActionListener(this);

    }

    public JButton getButton(){
        return btn;
    }

    public void ShowCard(){
        if(state == stateDown){
            btn.setIcon(backImg);
        }
        else if(state == stateUp){
            btn.setIcon(frontImg);
        }
        else if(state == stateOut){
            btn.setVisible(false);;
        }
    }

    public boolean IsDrawn(){
        return state == stateUp;
    }

    public String GetFront(){
        return frontSide;
    }

    public void RemoveCard(){
        state = stateOut;
        btn.setVisible(false);
    }

    public void HideCard(){
        state = stateDown;
        btn.setIcon(backImg);
        btn.setPreferredSize(new Dimension(backImg.getIconWidth(), backImg.getIconHeight()));
    }

    public void DrawCard(){
        state = stateUp;
        btn.setIcon(frontImg);
        btn.setPreferredSize(new Dimension(frontImg.getIconWidth(), frontImg.getIconHeight()));
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



    public void Update(Card otherCard){
        if(state == stateUp){
            if(otherCard!=null){
                if(otherCard.GetFront() == this.frontSide){
                    RemoveCard();
                }
                else{
                    HideCard();
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e){

    }
 }