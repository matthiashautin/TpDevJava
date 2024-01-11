import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.awt.image.*;

/**
 * This class implements a simple graphical user interface with a 
 * text entry area, a text output area and an optional image.
 * 
 * @author Michael Kolling
 * @version 1.0 (Jan 2003) DB edited (2023)
 */
public class UserInterface implements ActionListener
{
    private GameEngine aEngine;
    private JFrame     aMyFrame;
    private JTextField aEntryField;
    private JTextArea  aLog;
    private JLabel     aImage;
    private JLabel     aImageMap;

    //Btn Command
    private JButton    aBtnAudio;
    private JButton    aBtnQuit;
    private JButton    aBtnHelp;
    private JButton    aBtnEat;
    private JButton    aBtnLook;
    private JButton    aBtnBack;
    //Btn Basic Direction
    private JButton    aBtnNorth;
    private JButton    aBtnSouth;
    private JButton    aBtnEast;
    private JButton    aBtnWest;
    //Btn Other Direction
    private JButton    aBtnUp;
    private JButton    aBtnDown;
    private JButton    aBtnDownWest;
    private JButton    aBtnDownEast;
    private JButton    aBtnUpNorth;
    private JButton    aBtnUpEast;
    private JButton    aBtnUpWest;
    private JButton    aBtnDownSouth;
    
    private JButton    aBtnAttackLegere;
    private JButton    aBtnAttackLourde;

    private boolean    aBool;

    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is needed
     * @param pGameEngine  The GameEngine object implementing the game logic
     */
    public UserInterface(final GameEngine pGameEngine ) {
        this.aEngine = pGameEngine;
        this.createGUI();
        this.aBool = true;
        showMinimapImage("./Images/Map.png");
    } // UserInterface(.)

    /**
     * Print out some text into the text area
     */
    public void print(final String pText ) {
        this.aLog.append( pText );
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    } // print(.)

    /**
     * Print out some text into the text area, followed by a line break
     */
    public void println(final String pText ) {
        this.print( pText + "\n" );
    } // println(.)

    /**
     * Show an image file in the interface.
     */
    public void showImage(final String pImageName ) {
        String vImagePath = "" + pImageName; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource( vImagePath );
        if ( vImageURL == null )
            System.out.println( "Image not found : " + vImagePath );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL );
            this.aImage.setIcon( vIcon );
            this.aMyFrame.pack();
        }
    } // showImage(.)

    public void showMinimapImage(String imagePath) {
        URL imageURL = this.getClass().getClassLoader().getResource(imagePath);
        if (imageURL == null) {
            System.out.println("Image not found: " + imagePath);
        } else {
            ImageIcon icon = new ImageIcon(imageURL);
            this.aImageMap.setIcon(icon);
        }
    }

    /**
     * Enable or disable input in the entry field
     */
    public void enable(final boolean pOnOff ) {
        this.aEntryField.setEditable( pOnOff ); // enable/disable
        if ( pOnOff ) { // enable
            this.aEntryField.getCaret().setBlinkRate( 500 ); // cursor blink
            this.aEntryField.addActionListener( this ); // reacts to entry
        }
        else { // disable
            this.aEntryField.getCaret().setBlinkRate( 0 ); // cursor won't blink
            this.aEntryField.removeActionListener( this ); // won't react to entry
        }
    } // enable(.)

    /**
     * Set up graphical user interface
     */
    private void createGUI() {
        this.aMyFrame = new JFrame( "Crash ship" ); // change the title !
        this.aEntryField = new JTextField( 34 );
        
        this.aMyFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        this.aLog = new JTextArea();
        this.aLog.setEditable( false );
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(450, 450) );
        vListScroller.setMinimumSize( new Dimension(200,200) );

        //JButton
        this.aBtnAudio = new JButton("play audio");
        this.aBtnAudio.setPreferredSize(new Dimension(100,40));

        this.aBtnQuit = new JButton("quit");
        this.aBtnQuit.setPreferredSize(new Dimension(100,40));

        this.aBtnHelp = new JButton("help");
        this.aBtnHelp.setPreferredSize(new Dimension(100,40));

        this.aBtnEat = new JButton("eat");
        this.aBtnEat.setPreferredSize(new Dimension(100,40));

        this.aBtnLook = new JButton("look");
        this.aBtnLook.setPreferredSize(new Dimension(100,40));

        this.aBtnBack = new JButton("back");
        this.aBtnBack.setPreferredSize(new Dimension(100,40));

        this.aBtnNorth = new JButton("North");
        this.aBtnNorth.setPreferredSize(new Dimension(100,40));

        this.aBtnSouth = new JButton("South");
        this.aBtnSouth.setPreferredSize(new Dimension(100,40));

        this.aBtnEast = new JButton("East");
        this.aBtnEast.setPreferredSize(new Dimension(100,40));

        this.aBtnWest = new JButton("West");
        this.aBtnWest.setPreferredSize(new Dimension(100,40));

        this.aBtnUp = new JButton("Up");
        this.aBtnUp.setPreferredSize(new Dimension(100,40));

        this.aBtnDown = new JButton("Down");
        this.aBtnDown.setPreferredSize(new Dimension(100,40));

        this.aBtnDownWest = new JButton("DownWest");
        this.aBtnDownWest.setPreferredSize(new Dimension(100,40));

        this.aBtnDownEast = new JButton("DownEast");
        this.aBtnDownEast.setPreferredSize(new Dimension(100,40));

        this.aBtnUpNorth = new JButton("UpNorth");
        this.aBtnUpNorth.setPreferredSize(new Dimension(100,40));

        this.aBtnUpEast = new JButton("UpEast");
        this.aBtnUpEast.setPreferredSize(new Dimension(100,40));

        this.aBtnUpWest = new JButton("UpWest");
        this.aBtnUpWest.setPreferredSize(new Dimension(100,40));

        this.aBtnDownSouth = new JButton("DownSouth");
        this.aBtnDownSouth.setPreferredSize(new Dimension(100,40));
        
        this.aBtnAttackLegere = new JButton("ArmeLegere");
        this.aBtnAttackLegere.setPreferredSize(new Dimension(120,40));     
        
        this.aBtnAttackLourde = new JButton("ArmeLourde");
        this.aBtnAttackLourde.setPreferredSize(new Dimension(120,40));      

        //JLabel
        this.aImage = new JLabel();

        // Créez un nouveau JLabel pour la minimap
        this.aImageMap = new JLabel();
        this.aImageMap.setPreferredSize(new Dimension(469, 350));

        //temporraire je change la size de l'emplacement de l'image 
        //this.aImage.setPreferredSize(new Dimension(200, 200));

        JPanel vPaneCommand = new JPanel();   
        vPaneCommand.setLayout(new GridLayout(2, 7) );
        vPaneCommand.add(this.aBtnAudio, BorderLayout.CENTER );
        vPaneCommand.add(this.aBtnQuit,BorderLayout.CENTER );
        vPaneCommand.add(this.aBtnHelp,BorderLayout.CENTER );
        vPaneCommand.add(this.aBtnEat,BorderLayout.CENTER );
        vPaneCommand.add(this.aBtnLook,BorderLayout.CENTER );
        vPaneCommand.add(this.aBtnBack,BorderLayout.CENTER );
        
        vPaneCommand.add(this.aBtnNorth, BorderLayout.NORTH );
        vPaneCommand.add(this.aBtnSouth,BorderLayout.SOUTH );
        vPaneCommand.add(this.aBtnEast,BorderLayout.EAST );
        vPaneCommand.add(this.aBtnWest,BorderLayout.WEST );

        vPaneCommand.add(this.aBtnUp,BorderLayout.NORTH );
        vPaneCommand.add(this.aBtnDown,BorderLayout.SOUTH );
        vPaneCommand.add(this.aBtnDownWest,BorderLayout.WEST );
        vPaneCommand.add(this.aBtnDownEast,BorderLayout.EAST );
        vPaneCommand.add(this.aBtnUpNorth,BorderLayout.NORTH );
        vPaneCommand.add(this.aBtnUpEast,BorderLayout.EAST );
        vPaneCommand.add(this.aBtnUpWest,BorderLayout.WEST );
        vPaneCommand.add(this.aBtnDownSouth,BorderLayout.SOUTH );
        
        vPaneCommand.add(this.aBtnAttackLegere,BorderLayout.WEST );
        vPaneCommand.add(this.aBtnAttackLourde,BorderLayout.SOUTH );
        
        /*
        JPanel vMainPanel = new JPanel();      
        vMainPanel.setLayout( new BorderLayout()); // ==> only five places
        vMainPanel.add( this.aImage, BorderLayout.NORTH );
        vMainPanel.add( vListScroller, BorderLayout.CENTER );
        vMainPanel.add( this.aEntryField, BorderLayout.SOUTH );
        vMainPanel.add( vPaneCommand, BorderLayout.EAST);//le placer à l'est
        //vMainPanel.add( vPaneDirection, BorderLayout.WEST);//le placer à l'est
        vMainPanel.add(this.aImageMap, BorderLayout.WEST); // LINE_END est l'équivalent de EAST
         */

        JPanel vMainPanel = new JPanel();
        vMainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // this.aImage en haut à gauche
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        vMainPanel.add(this.aImage, gbc);

        // vListScroller au milieu
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        vMainPanel.add(vListScroller, gbc);

        // vMinimapContainer en haut à droite
        JPanel vMinimapContainer = new JPanel();
        vMinimapContainer.add(this.aImageMap, BorderLayout.CENTER);
        vMinimapContainer.setPreferredSize(new Dimension(200, 200));  
        Insets marginMinimap = new Insets(10, 0, 0, 0); 
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        gbc.insets = marginMinimap; 
        vMainPanel.add(vMinimapContainer, gbc);

        // vPaneCommand en bas à droite
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        Insets marginPaneCommand = new Insets(0, 0, 10, 0); 
        gbc.insets = marginPaneCommand;
        vMainPanel.add(vPaneCommand, gbc);

        // this.aEntryField en bas de la page
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        Insets marginEntryField = new Insets(0, 0, 10, 0); 
        gbc.insets = marginEntryField;
        vMainPanel.add(this.aEntryField, gbc);

        // Ajoutez le panneau principal au contenu du cadre
        this.aMyFrame.getContentPane().add(vMainPanel);

        // Ajoutez le label de la minimap au panneau principal
        //this.aMyFrame.getContentPane().add( vMainPanel, BorderLayout.CENTER );

        // add some event listeners to some components
        this.aEntryField.addActionListener( this );

        this.aBtnAudio.addActionListener( this );
        this.aBtnQuit.addActionListener( this );
        this.aBtnHelp.addActionListener( this );
        this.aBtnEat.addActionListener( this );
        this.aBtnLook.addActionListener( this );
        this.aBtnBack.addActionListener(this );

        this.aBtnNorth.addActionListener( this );
        this.aBtnSouth.addActionListener( this );
        this.aBtnEast.addActionListener( this );
        this.aBtnWest.addActionListener( this );

        this.aBtnUp.addActionListener( this );
        this.aBtnDown.addActionListener( this );
        this.aBtnDownWest.addActionListener( this );
        this.aBtnDownEast.addActionListener( this );
        this.aBtnUpNorth.addActionListener( this );
        this.aBtnUpEast.addActionListener( this );
        this.aBtnUpWest.addActionListener( this );
        this.aBtnDownSouth.addActionListener( this );
        this.aBtnAttackLegere.addActionListener( this );
        this.aBtnAttackLourde.addActionListener( this );

        // to end program when window is closed
        this.aMyFrame.addWindowListener(
            new WindowAdapter() { // anonymous class
                @Override public void windowClosing(final WindowEvent pE)
                {
                    System.exit(0);
                }
            } );

        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();
    } // createGUI()

    /**
     * Actionlistener interface for entry textfield
     */
    @Override public void actionPerformed( final ActionEvent pE ) {
        if (pE.getSource() == this.aBtnAudio) {
            this.aBool =! this.aBool;
            if(aBool == false) {
                this.aBtnAudio.setText("stop audio");
                this.aEngine.toggleAudio();
            }else {
                this.aBtnAudio.setText("play audio");
                this.aEngine.toggleAudio();
            }
        } else if(pE.getSource() == this.aBtnQuit ){
            this.aEngine.interpretCommand(this.aBtnQuit.getActionCommand());   

        } else if(pE.getSource() == this.aBtnHelp ){
            this.aEngine.interpretCommand(this.aBtnHelp.getActionCommand()); 

        }else if(pE.getSource() == this.aBtnEat ){
            this.aEngine.interpretCommand(this.aBtnEat.getActionCommand());

        }else if(pE.getSource() == this.aBtnLook ){
            this.aEngine.interpretCommand(this.aBtnLook.getActionCommand());

        }else if(pE.getSource() == this.aBtnBack ){
            this.aEngine.interpretCommand(this.aBtnBack.getActionCommand());

        }else if(pE.getSource() == this.aBtnNorth ){
            this.aEngine.interpretCommand("go north");

        }else if(pE.getSource() == this.aBtnSouth ){
            this.aEngine.interpretCommand("go south");

        }else if(pE.getSource() == this.aBtnEast ){
            this.aEngine.interpretCommand("go east");

        }else if(pE.getSource() == this.aBtnWest ){
            this.aEngine.interpretCommand("go west");

        }else if(pE.getSource() == this.aBtnUp ){
            this.aEngine.interpretCommand("go up");

        }else if(pE.getSource() == this.aBtnDown ){
            this.aEngine.interpretCommand("go down");

        }else if(pE.getSource() == this.aBtnDownWest ){
            this.aEngine.interpretCommand("go downwest");

        }else if(pE.getSource() == this.aBtnDownEast ){
            this.aEngine.interpretCommand("go downeast");

        }else if(pE.getSource() == this.aBtnUpNorth ){
            this.aEngine.interpretCommand("go upnorth");

        }else if(pE.getSource() == this.aBtnUpEast ){
            this.aEngine.interpretCommand("go upeast");

        }else if(pE.getSource() == this.aBtnUpWest ){
            this.aEngine.interpretCommand("go upwest");

        }else if(pE.getSource() == this.aBtnDownSouth ){
            this.aEngine.interpretCommand("go downsouth");
            
        }else if(pE.getSource() == this.aBtnAttackLegere ){
            this.aEngine.interpretCommand("attack ArmeLegere");
            
        }else if(pE.getSource() == this.aBtnAttackLourde ){
            this.aEngine.interpretCommand("attack ArmeLourde");

        }else 
            this.processCommand(); // never suppress this line
    } // actionPerformed(.)

    /**
     * A command has been entered in the entry field
     * Read the command and do whatever is necessary to process it
     */
    private void processCommand(){
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "\n" );

        this.aEngine.interpretCommand( vInput );
    } // processCommand()

    /**
     * Ferme la fenêtre de l'interface graphique, si elle est ouverte
     */
    public void closeFrame() {
        if (this.aMyFrame != null) {
            this.aMyFrame.dispose();
        }
    } //closeFrame()

} // UserInterface 