package battleship2000.ui;

import battleship2000.programlogic.GameCommands;
import battleship2000.ui.playback.Audible;
import battleship2000.ui.playback.AudioClip;
import battleship2000.ui.control.CreatePanes;
import battleship2000.ui.panes.GamePane;
import battleship2000.ui.panes.TitlePane;
import battleship2000.ui.playback.AudioContent;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

/**
 * A Swing-based framework for creating an instance of a graphical user interface.
 *
 * @author Petri Kallio
 */
public class BattleshipGui implements Runnable {
    private JFrame frame;
    private JPanel cards; 
    private Audible explosion;
    private AudioContent audioContent;
    private final int FRAME_WIDTH = 650;
    private final int FRAME_HEIGHT = 550;
    
    public BattleshipGui() {
        this.audioContent = new AudioContent(this);
    }

    public AudioContent getAudioContent() {
        return audioContent;
    }

    public int getFRAME_HEIGHT() {
        return FRAME_HEIGHT;
    }

    public int getFRAME_WIDTH() {
        return FRAME_WIDTH;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }
    
    @Override
    public void run() {
        this.frame = new JFrame("Battleship 2000");
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
//        this.frame.setResizable(false);
        
//        CreatePanes createMenus = new CreatePanes(this.frame.getContentPane());
//        createMenus.execute();
        
        createContentAlt(frame.getContentPane());
        
        this.frame.pack();
        this.frame.setVisible(true);
        
        loadAudio();
    }

    private void createContentAlt(Container contentPane) {
        CardLayout cardLayout = new CardLayout();
        
        contentPane.setLayout(cardLayout);
        
        GameCommands gameCommands = new GameCommands();
        gameCommands.createANewGame();
        
        
        cards = new JPanel(cardLayout);
        
        JPanel gamePane = new GamePane(gameCommands, 25, this);
        JPanel titlePane = new TitlePane(this, gameCommands, cards).getTitlePage();
        
        cards.add(titlePane, "TITLE");
        cards.add(gamePane, "GAME");
        
        contentPane.add(cards);
    }
    
    private void createContent(Container contentPane) {
        CardLayout cardLayout = new CardLayout();
        
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        
        contentPane.setLayout(layout);
        int[] layoutWidths = {50, 550, 50};
        int[] layoutHeights = {50, 400, 50};
        
        layout.columnWidths = layoutWidths;
        layout.rowHeights = layoutHeights;
        
        GameCommands gameCommands = new GameCommands();
        gameCommands.createANewGame();
        JPanel gamePane = new GamePane(gameCommands, 25, this);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        
        layout.setConstraints(gamePane, gbc);
        contentPane.add(gamePane);
    }

    public void alertException(String message, Exception ex) {
        JOptionPane.showMessageDialog(null, message + "\n" + ex.toString(), ex.getClass().getName(), 
                JOptionPane.ERROR_MESSAGE);
        frame.dispose();
    }

    private void loadAudio() {
        this.explosion = new AudioClip("src/main/java/battleship2000/media/audio/explosion.wav", this);
    }

    public Audible getExplosion() {
        return audioContent.getExplosion();
    }

    public JPanel getCards() {
        return cards;
    }
}
