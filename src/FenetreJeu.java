import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

public class FenetreJeu extends JFrame implements ActionListener {

    private JPanel panelPrincipal;
    private JPanel panelScore;
    private JPanel panelCartes;
    private JLabel labelJ1;
    private JLabel labelJ2;

    private JLabel scoreJ1;
    private JLabel scoreJ2;

    private List<Card> cards;
    private Player[] players;

    private int width;
    private int height;

    public FenetreJeu(int w, int h, String p1, String p2){
        this.setResizable(true);
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        build(w, h, p1, p2);
    }

    private void build(int w, int h, String p1, String p2){
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        panelScore = new JPanel();
        panelScore.setLayout(new FlowLayout());

        width = w;
        height = h;

        panelCartes = new JPanel();
        panelCartes.setLayout(new GridLayout(width, height, 10, 10));

        cards = new ArrayList<Card>();
        players = new Player[2];
        players[0] = new Player(p1, 1);
        players[1] = new Player(p2, 2);

        this.setTitle("C'est à " + players[0].GetName() + " de jouer!");

        labelJ1 = new JLabel(players[0].GetName());
        labelJ2 = new JLabel(players[1].GetName());
        scoreJ1 = new JLabel(""+players[0].GetScore());
        scoreJ2 = new JLabel(""+players[1].GetScore());

        int j = width*height;

        String[] flags = new String[]{"urss", "france", "allemagne", "vanuatu"};

        for(int i = 0; i < j; i++){
                cards.add(new Card(this, flags[0]+".jpg", flags[i%3+1]+".jpg"));
                panelCartes.add(cards.get(cards.size()-1).getButton());
        }

        Collections.shuffle(cards);


        panelScore.add(labelJ1);
        panelScore.add(scoreJ1);
        panelScore.add(labelJ2);
        panelScore.add(scoreJ2);

        panelPrincipal.add(panelScore, BorderLayout.NORTH);
        panelPrincipal.add(panelCartes, BorderLayout.CENTER);

        this.setContentPane(panelPrincipal);
    }

    public void actionPerformed(ActionEvent e) {
        
        int rPP = players[0].isPlayerPlaying() + players[1].isPlayerPlaying();
        Draw(e, rPP-1);
        System.out.println("DRAW");
        if(players[rPP-1].GetState() instanceof StatePlayerTwoCardsDrown){
            if(players[rPP-1].IsWinning()){
                System.out.println("WIN");
                players[rPP-1].Gain(2);

                for(int i = 0; i < cards.size(); i++){
                    if(cards.get(i).IsDrawn()){
                        cards.get(i).RemoveCard();
                    }
                }
            }
            else{
                System.out.println("LOSE");
                players[0].SwitchRole();
                players[1].SwitchRole();

                for(int i = 0; i < cards.size(); i++){
                    if(cards.get(i).IsDrawn()){
                        cards.get(i).HideCard();
                    }
                }
                rPP = players[0].isPlayerPlaying() + players[1].isPlayerPlaying();
                players[rPP-1].PickCard(null);
            }
            
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        this.setTitle("C'est à " + players[rPP-1].GetName() + " de jouer!");
        scoreJ1.setText("" + players[0].GetScore());
        scoreJ2.setText("" + players[1].GetScore());

    }

    public void Draw(ActionEvent e, int rPP){
        for(int i = 0; i < cards.size(); i++){
            if(e.getSource() == cards.get(i).getButton()){
                cards.get(i).DrawCard();
                players[rPP].PickCard(cards.get(i));
                System.out.println("PICK");
            }
        }
    }
}
