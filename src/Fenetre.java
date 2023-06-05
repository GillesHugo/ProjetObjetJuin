import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame implements ActionListener {
    private JPanel panelParametrage;
    private JPanel panelJeu;
    private JLabel labelJ1;
    private JLabel labelJ2;
    private JLabel labelTheme;
    private JLabel labelTaille;

    private JTextField pseudoJ1;
    private JTextField pseudoJ2;
    private JComboBox<String> comboTheme;
    private JComboBox<String> comboTaille;
    private JButton boutonOK;
    private String[] themes = {"IAI", "Formes", "Autre"};
    private String[] taille = {"IAI", "Formes", "Autre"};


    public Fenetre(){
        this.setTitle("Memory");
        this.setResizable(true);
        this.setSize(1000, 1000);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        build();
    }
    private void build(){
        panelParametrage = new JPanel();
        panelParametrage.setLayout(new GridLayout(5,2,100,30));

        labelJ1 = new JLabel("Pseudo Joueur 1 : ");
        labelJ2 = new JLabel("Pseudo Joueur 2 : ");
        labelTheme = new JLabel("Thème : ");
        labelTaille = new JLabel("Taille : ");
        boutonOK = new JButton("Démarrer le jeu");

        pseudoJ1 = new JTextField();
        pseudoJ2 = new JTextField();

        comboTheme = new JComboBox<String>(themes);
        comboTaille = new JComboBox<String>(taille);

        panelParametrage.add(labelJ1);
        panelParametrage.add(pseudoJ1);
        panelParametrage.add(labelJ2);
        panelParametrage.add(pseudoJ2);
        panelParametrage.add(labelTheme);
        panelParametrage.add(comboTheme);
        panelParametrage.add(labelTaille);
        panelParametrage.add(comboTaille);
        panelParametrage.add(boutonOK);

        this.setContentPane(panelParametrage);
    }
    public void actionPerformed(ActionEvent e){

    }
}
