package battleship2000.ui;

import battleship2000.programlogic.GameCommands;
import battleship2000.ui.playback.Audible;
import battleship2000.ui.panes.CardNames;
import battleship2000.ui.panes.LoadingPane;
import battleship2000.ui.panes.TitlePane;
import battleship2000.ui.playback.AudioContent;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
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
    private String jarPath;
    private AudioContent audioContent;
    private final int FRAME_WIDTH = 650;
    private final int FRAME_HEIGHT = 550;
    private final int SQUARE_WIDTH = 25;
    
    public BattleshipGui() {
        URL jarLocation = this.getClass().getProtectionDomain().getCodeSource().getLocation();
        
        try {
            jarPath = URLDecoder.decode(jarLocation.getFile(), "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            alertException("Unable to load content", ex);
        }
        
        System.out.println(jarPath);
        this.audioContent = new AudioContent(this);
        new Thread(this.audioContent).start();
    }

    public String getJarPath() {
        return jarPath;
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
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        
        this.frame.setLocation((int)screenSize.getWidth() / 2 - FRAME_WIDTH / 2,
                (int)screenSize.getHeight() / 2 - FRAME_HEIGHT / 2);
        
        createContent(frame.getContentPane());
        
        this.frame.pack();
        this.frame.setVisible(true);
    }

    private void createContent(Container contentPane) {
        CardLayout cardLayout = new CardLayout();
        
        GameCommands gameCommands = new GameCommands();
        gameCommands.createANewGame();
        
        cards = new JPanel(cardLayout);
        
        JPanel titlePane = new TitlePane(this, gameCommands, cards).getTitlePage();
        JPanel loadPane = new LoadingPane(this).getPane();
        
        cards.add(titlePane, CardNames.TITLE.toString());
        cards.add(loadPane, CardNames.LOAD.toString());
        
        cardLayout.show(cards, CardNames.TITLE.toString());
        contentPane.add(cards);
    }
    
    public final void alertException(String message, Exception ex) {
        JOptionPane.showMessageDialog(null, message + "\n" + ex.toString(), ex.getClass().getName(), 
                JOptionPane.ERROR_MESSAGE);
        frame.dispose();
    }

    public Audible getExplosion() {
        return audioContent.getExplosion();
    }

    public JPanel getCards() {
        return cards;
    }

    public JFrame getFrame() {
        return frame;
    }

    public int getSQUARE_WIDTH() {
        return SQUARE_WIDTH;
    }
}
