import java.util.Scanner;
import java.sql.Time;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;


public class Terminal {
    private java.util.Scanner sc;
    private int idxPhraseSel = -1;
    private char c;

    private Card[][] game;

    private Player[] players;
    private int rPP; //Rank Player Playing


    public Terminal(){
        sc = new Scanner(System.in);
        game = new Card[5][5];
        players = new Player[2];
        players[0] = new Player("Manon", 1);
        players[1] = new Player("Hugo", 2);
        rPP = 0;

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                if(j%3 == 0){
                    game[i][j] = new Card('a');
                }
                else if(j%3 == 1){
                    game[i][j] = new Card('b');
                }
                else if(j%3 == 2){
                    game[i][j] = new Card('c');
                }
            }
        }
    }


    private void DisplayGame(){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                game[i][j].ShowCard();
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    private void Dialoguer(){

        
        DisplayGame();


        int rPP = players[0].isPlayerPlaying() + players[1].isPlayerPlaying();

        if(players[rPP-1].GetState() instanceof StatePlayerTwoCardsDrown){
            Card[] cardsDrown = players[rPP-1].GetCards();
            if(cardsDrown[0].getFront() == cardsDrown[1].getFront()){
                players[rPP-1].Gain(2);
                players[rPP-1].PickCard(null);

                for(int i = 0; i < 5; i++){
                    for(int j = 0; j < 5; j++){
                        if(game[i][j].IsDrawn()){
                            game[i][j].RemoveCard();
                        }
                    }
                }
            }
            else{
                players[0].SwitchRole();
                players[1].SwitchRole();

                for(int i = 0; i < 5; i++){
                    for(int j = 0; j < 5; j++){
                        if(game[i][j].IsDrawn()){
                            game[i][j].HideCard();
                        }
                    }
                }
                rPP = players[0].isPlayerPlaying() + players[1].isPlayerPlaying();
            }

            
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            DisplayGame();
        }

        System.out.println("C'est à " + players[rPP-1].GetName() + " de jouer, " + " score = " + players[rPP-1].GetScore() + ",   Sélectionnez une ligne, appuyez sur # pour quitter.");
        int row = StringToInt(sc.nextLine());
        System.out.println("Sélectionnez une ligne, appuyez sur # pour quitter.");
        int col = StringToInt(sc.nextLine());

        game[row][col].DrawCard();
        players[rPP-1].PickCard(game[row][col]);


    }

    private int StringToInt(String str){
        int num = 0;
        try {
            num = Integer.parseInt(str);
            //System.out.println("Le nombre est : " + num);
        } 
        catch (NumberFormatException e) {
        }
        return num;
    }

    private boolean FinApplication(){
        return c == '#';
    }

    public static void main(String args[])
    {
        Terminal terminal = new Terminal();
        while(!terminal.FinApplication()){
            terminal.Dialoguer();
        }
    }
}