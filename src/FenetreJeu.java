import javax.swing.*;
import java.util.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.*;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import java.util.Random;
import java.lang.Math;

public class FenetreJeu extends JFrame implements ActionListener {

    private JPanel panelPrincipal;
    private JPanel panelScore;
    private JPanel panelCartes;
    private JLabel labelJ1;
    private JLabel labelJ2;

    private JLabel scoreJ1;
    private JLabel scoreJ2;

    //private List<Card> cards;
    private Card cards[][];
    private Player[] players;

    private int width;
    private int height;

    private String theme;
    private Timer timer;

    public FenetreJeu(int w, int h, String theme, String p1, String p2){
        this.setResizable(true);
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        build(w, h, theme,  p1, p2);
    }

    private void build(int w, int h, String theme, String p1, String p2){
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        panelScore = new JPanel();
        panelScore.setLayout(new FlowLayout());

        width = w;
        height = h;

        panelCartes = new JPanel();
        panelCartes.setLayout(new GridLayout(width, height, 10, 10));

        //cards = new ArrayList<Card>();
        players = new Player[2];
        players[0] = new Player(p1, 1);
        players[1] = new Player(p2, 2);

        this.setTitle("C'est à " + players[0].GetName() + " de jouer!");
        players[0].playerState();

        labelJ1 = new JLabel(players[0].GetName());
        labelJ2 = new JLabel(players[1].GetName());
        scoreJ1 = new JLabel(""+players[0].GetScore());
        scoreJ2 = new JLabel(""+players[1].GetScore());

        this.theme = theme;

        timer = new Timer(10, event -> {SecondPause();});

        timer.setRepeats(false);

        SetGame();

        panelScore.add(labelJ1);
        panelScore.add(scoreJ1);
        panelScore.add(labelJ2);
        panelScore.add(scoreJ2);

        panelPrincipal.add(panelScore, BorderLayout.NORTH);
        panelPrincipal.add(panelCartes, BorderLayout.CENTER);

        this.setContentPane(panelPrincipal);
    }

    private void SetGame(){
        int size = width*height;
        Random random = new Random();
        String folderPath = "img/" + theme;

        File folder = new File(folderPath);
        ArrayList<String> fileNames = new ArrayList<>();
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null && files.length > 0) {
                List<File> fileList = Arrays.asList(files);
                for (File file : fileList) {
                    String fileName = file.getName();
                    fileNames.add(folderPath + "/" + fileName);
                }
            }
        }

        cards = new Card[width][height];
        int idxImg = random.nextInt(fileNames.size());
        int numberOfIdxImg = 0;
        double presentedImgs = Math.pow(2, idxImg+1);
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int it = 0;
        boolean cardSet = false;
        for(int i = 0; i < size; i++){
            cardSet = false;
            do{
                it++;
                x = random.nextInt(width);
                y = random.nextInt(height);
                if(cards[x][y] == null){
                    cardSet = true;
                    System.out.println(fileNames.get(idxImg));
                    cards[x][y] = new Card(this, "img/0.jpg", fileNames.get(idxImg), idxImg);
                    panelCartes.add(cards[x][y].getButton());
                    numberOfIdxImg++;
                    if(numberOfIdxImg == 2){
                        idxImg = random.nextInt(fileNames.size());
                        numberOfIdxImg = 0;                    
                    }
                }
            }while(cardSet == false);
        }
        System.out.println(it);
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                panelCartes.add(cards[i][j].getButton());
            }
         }
    }

    public void actionPerformed(ActionEvent e) {
        int rPP = players[0].isPlayerPlaying() + players[1].isPlayerPlaying() - 1;
        this.setTitle("C'est à " + players[rPP].GetName() + " de jouer!");
        players[rPP].playerState();
        Draw(e, rPP);
        System.out.print("draw and ");
        players[rPP].playerState();
        if(players[rPP].hasPlayerTwoCards()) {
            timer.start();
            //SecondPause();
        }    
    }

    public void SecondPause(){
        int rPP = players[0].isPlayerPlaying() + players[1].isPlayerPlaying() - 1;
        Card[] cardsDrown = players[rPP].GetCards();
            System.out.println(cardsDrown[0].GetFront() + " + " + cardsDrown[1].GetFront());
            if(cardsDrown[0].GetFront() == cardsDrown[1].GetFront()){
                cardsDrown[0].RemoveCard();
                cardsDrown[1].RemoveCard();
                players[rPP].PickCard(null);
                players[rPP].Gain(2);
                System.out.print("win and ");
                players[rPP].playerState();
            }
            else{
                cardsDrown[0].HideCard();
                cardsDrown[1].HideCard();        
                players[rPP].PickCard(null);
                players[0].SwitchRole();
                players[1].SwitchRole();
                System.out.print("lose and ");
                players[rPP].playerState();
            }
            scoreJ1.setText(""+players[0].GetScore());
            scoreJ2.setText(""+players[1].GetScore());
        System.out.println("TIMERs");
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        rPP = players[0].isPlayerPlaying() + players[1].isPlayerPlaying() - 1;
        this.setTitle("C'est à " + players[rPP].GetName() + " de jouer!");
    }

    public void Draw(ActionEvent e, int rPP){
         for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                if(e.getSource() == cards[i][j].getButton() && !cards[i][j].IsDrawn()){
                    cards[i][j].DrawCard();
                    players[rPP].PickCard(cards[i][j]);
                }
            }
         }
    }
}
