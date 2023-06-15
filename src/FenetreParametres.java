import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class FenetreParametres extends JFrame {
    private JPanel panelParametrage;
    private JLabel labelJ1;
    private JLabel labelJ2;
    private JLabel labelTheme;
    private JLabel labelTaille;

    private JTextField pseudoJ1;
    private JTextField pseudoJ2;
    private JComboBox<String> comboTheme;
    private JComboBox<String> comboTaille;
    private JButton boutonOK;
    private String[] themes;
    private String[] taille;

    public FenetreParametres(ActionListener listener){
        this.setTitle("Memory");
        this.setResizable(true);
        this.setSize(500, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        build(listener);
    }
    private void build(ActionListener listener){
        String [][] elements = parseConfigFile("conf/config.ini");
        panelParametrage = new JPanel();
        panelParametrage.setLayout(new GridLayout(5,2,100,30));

        themes = elements[0];
        taille = elements[1];

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
        String str = (String)comboTaille.getSelectedItem();
        return new int[]{str.charAt(0) - 48, str.charAt(2) - 48};
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

    private String[][] parseConfigFile(String fileName) {
        String[][] elements = new String[2][];
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(fileName));
            String line;

            for (int i = 0; i < 2; i++) {
                if ((line = reader.readLine()) != null) {
                    String[] lineElements = line.split(";");
                    elements[i] = new String[lineElements.length - 1];
                    System.arraycopy(lineElements, 1, elements[i], 0, elements[i].length);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return elements;
    }
}
