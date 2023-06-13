import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FenetreParametres extends JFrame implements ActionListener {
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
    private String[] themes = {"flags", "IAI", "mahjong"};
    private String[] taille = {"4x3", "4x4", "5x4", "6x5", "6x6", "7x6"};

    private boolean playBtnPressed = false;

    public FenetreParametres(ActionListener listener){
        this.setTitle("Memory");
        this.setResizable(true);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        build(listener);
    }
    private void build(ActionListener listener){
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
		boutonOK.addActionListener(listener);

        this.setContentPane(panelParametrage);
    }

    public int[] getCoordinates(){
        if(comboTaille.getSelectedItem() == taille[0]) return new int[]{4, 3};
        if(comboTaille.getSelectedItem() == taille[1]) return new int[]{4, 4};
        if(comboTaille.getSelectedItem() == taille[2]) return new int[]{5, 4};
        if(comboTaille.getSelectedItem() == taille[3]) return new int[]{6, 5};
        if(comboTaille.getSelectedItem() == taille[4]) return new int[]{6, 6};
        if(comboTaille.getSelectedItem() == taille[5]) return new int[]{7, 6};
        return new int[]{4, 3};
    }
    
    public JButton getBtnPlay(){
        return boutonOK;
    }

    public String getPlayer1Name(){
        return pseudoJ1.getText();
    }

    public String getPlayer2Name(){
        return pseudoJ2.getText();
    }

    public String getComboTheme(){
        return comboTheme.getSelectedItem().toString();
    }

    public boolean oui(){
        return playBtnPressed;
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == boutonOK){
            playBtnPressed = true;
        }
    }
}
