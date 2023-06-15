
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class FenetreController implements ActionListener {
    
    private enum state{
        stateOptions,
        stateGame,
        stateQuit
    };

    private FenetreJeu fj;
    private FenetreParametres fp;
    private state appState;

    
    public FenetreController(){
        fp = new FenetreParametres(this);
        fp.setVisible(true);
        appState = state.stateOptions;
    }

    public int getState(){
        return (appState == state.stateQuit) ? -1 : 0;
    }

    public void actionPerformed(ActionEvent e){
        if (e.getSource() == fp.getBtnPlay()){ //The user press the button in order to play, the options window slips away & the game window occurs
            fp.setVisible(false);
            fj = new FenetreJeu(fp.getCoordinates()[0], fp.getCoordinates()[1], fp.getComboTheme(), fp.getPlayer1Name(), fp.getPlayer2Name());
            fj.setVisible(true);
        }
    }

}
