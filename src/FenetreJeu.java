import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreJeu extends JFrame implements ActionListener {

    private JPanel panelPrincipal;
    private JPanel panelScore;
    private JPanel panelCartes;
    private JLabel labelJ1;
    private JLabel labelJ2;

    private JLabel scoreJ1;
    private JLabel scoreJ2;
    private JButton[] cartes;

    public FenetreJeu(){
        this.setTitle("Jouez !");
        this.setResizable(true);
        this.setSize(1000, 800);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        build(4, 5, "Joueur 1", "Joueur 2");
    }

    private void build(int longueur, int largeur, String joueur1, String joueur2){
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

        panelScore = new JPanel();
        panelScore.setLayout(new FlowLayout());

        panelCartes = new JPanel();
        panelCartes.setLayout(new GridLayout(longueur, largeur, 10, 10));

        labelJ1 = new JLabel("Joueur 1 ");
        labelJ2 = new JLabel("Joueur 2 ");
        scoreJ1 = new JLabel("Score :1");
        scoreJ2 = new JLabel("Score :2");

        int nbCartes = longueur*largeur;

        cartes = new JButton[nbCartes];

        for(JButton bouton : cartes) {
            bouton = new JButton("test");
            panelCartes.add(bouton);
        }


        panelScore.add(labelJ1);
        panelScore.add(scoreJ1);
        panelScore.add(labelJ2);
        panelScore.add(scoreJ2);

        panelPrincipal.add(panelScore, BorderLayout.NORTH);
        panelPrincipal.add(panelCartes, BorderLayout.CENTER);

        this.setContentPane(panelPrincipal);

    }
    public void actionPerformed(ActionEvent e) {

    }
}
