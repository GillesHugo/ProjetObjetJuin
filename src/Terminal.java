import java.util.Scanner;
import java.util.ArrayList;


public class Terminal {
    private java.util.Scanner sc;
    private int idxPhraseSel = -1;
    private char c;

    private Card[][] game;
    private Card cardSelected = null;
    private Card sndCardSelected = null;


    public Terminal(){
        sc = new Scanner(System.in);
        game = new Card[5][5];
        
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

        //etat.Dialoguer();
    }

    private void Dialoguer(){

        
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                game[i][j].ShowCard();
                System.out.print(" ");
            }
            System.out.print("\n");
        }


        if(cardSelected != null && sndCardSelected != null){
            System.out.println("************************");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            cardSelected.Update(sndCardSelected);
            sndCardSelected.Update(cardSelected);
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    game[i][j].ShowCard();
                    System.out.print(" ");
                }
                System.out.print("\n");
            }
            cardSelected = null;
            sndCardSelected = null;
        }

        System.out.println("Sélectionnez une ligne, appuyez sur # pour quitter.");
        int row = StringToInt(sc.nextLine());
        System.out.println("Sélectionnez une ligne, appuyez sur # pour quitter.");
        int col = StringToInt(sc.nextLine());

        game[col][row].PlayerSelect(cardSelected);
        if(cardSelected == null){
            cardSelected = game[col][row];
        }
        else{
            sndCardSelected = game[col][row];
        }
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