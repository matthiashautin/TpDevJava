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
    //Btn Command
    private JButton    aBtnAudio;
    private JButton    aBtnQuit;
    private JButton    aBtnHelp;
    private JButton    aBtnEat;
    private JButton    aBtnLook;
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
    
    private boolean    aBool;
    
    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param gameEngine  The GameEngine object implementing the game logic.
     */
    public UserInterface( final GameEngine pGameEngine )
    {
        this.aEngine = pGameEngine;
        this.createGUI();
        this.aBool = true;
    } // UserInterface(.)

    /**
     * Print out some text into the text area.
     */
    public void print( final String pText )
    {
        this.aLog.append( pText );
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    } // print(.)

    /**
     * Print out some text into the text area, followed by a line break.
     */
    public void println( final String pText )
    {
        this.print( pText + "\n" );
    } // println(.)

    /**
     * Show an image file in the interface.
     */
    public void showImage( final String pImageName )
    {
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

    /**
     * Enable or disable input in the entry field.
     */
    public void enable( final boolean pOnOff )
    {
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
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        this.aMyFrame = new JFrame( "Crash ship" ); // change the title !
        this.aEntryField = new JTextField( 34 );

        this.aLog = new JTextArea();
        this.aLog.setEditable( false );
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(350, 350) );
        vListScroller.setMinimumSize( new Dimension(100,100) );
        
        //JButton
        this.aBtnAudio = new JButton("play audio");
        this.aBtnAudio.setPreferredSize(new Dimension(100,25));
        
        this.aBtnQuit = new JButton("quit");
        this.aBtnQuit.setPreferredSize(new Dimension(100,25));
    
        this.aBtnHelp = new JButton("help");
        this.aBtnHelp.setPreferredSize(new Dimension(100,25));
        
        this.aBtnEat = new JButton("eat");
        this.aBtnEat.setPreferredSize(new Dimension(100,25));
        
        this.aBtnLook = new JButton("look");
        this.aBtnLook.setPreferredSize(new Dimension(100,25));
        
        
        this.aBtnNorth = new JButton("North");
        this.aBtnNorth.setPreferredSize(new Dimension(100,25));
        
        this.aBtnSouth = new JButton("South");
        this.aBtnSouth.setPreferredSize(new Dimension(100,25));

        this.aBtnEast = new JButton("East");
        this.aBtnEast.setPreferredSize(new Dimension(100,25));

        this.aBtnWest = new JButton("West");
        this.aBtnWest.setPreferredSize(new Dimension(100,25));
        
        
        this.aBtnUp = new JButton("Up");
        this.aBtnUp.setPreferredSize(new Dimension(100,25));
        
        this.aBtnDown = new JButton("Down");
        this.aBtnDown.setPreferredSize(new Dimension(100,25));
        
        this.aBtnDownWest = new JButton("DownWest");
        this.aBtnDownWest.setPreferredSize(new Dimension(100,25));
        
        this.aBtnDownEast = new JButton("DownEast");
        this.aBtnDownEast.setPreferredSize(new Dimension(100,25));
        
        this.aBtnUpNorth = new JButton("UpNorth");
        this.aBtnUpNorth.setPreferredSize(new Dimension(100,25));
        
        this.aBtnUpEast = new JButton("UpEast");
        this.aBtnUpEast.setPreferredSize(new Dimension(100,25));
        
        this.aBtnUpWest = new JButton("UpWest");
        this.aBtnUpWest.setPreferredSize(new Dimension(100,25));
        
        this.aBtnDownSouth = new JButton("DownSouth");
        this.aBtnDownSouth.setPreferredSize(new Dimension(100,25));
        
        
        //JLabel
        this.aImage = new JLabel();

        //temporraire je change la size de l'emplacement de l'image 
        //this.aImage.setPreferredSize(new Dimension(500, 400));
        
        JPanel vPaneCommand = new JPanel();   
        vPaneCommand.setLayout(new GridLayout(3, 2) );
        vPaneCommand.add(this.aBtnAudio, BorderLayout.CENTER );
        vPaneCommand.add(this.aBtnQuit,BorderLayout.CENTER );
        vPaneCommand.add(this.aBtnHelp,BorderLayout.CENTER );
        vPaneCommand.add(this.aBtnEat,BorderLayout.CENTER );
        vPaneCommand.add(this.aBtnLook,BorderLayout.CENTER );

        JPanel vPaneDirection = new JPanel();
        vPaneDirection.setLayout(new GridLayout(3, 4) );
        vPaneDirection.add(this.aBtnNorth, BorderLayout.NORTH );
        vPaneDirection.add(this.aBtnSouth,BorderLayout.SOUTH );
        vPaneDirection.add(this.aBtnEast,BorderLayout.EAST );
        vPaneDirection.add(this.aBtnWest,BorderLayout.WEST );
        
        vPaneDirection.add(this.aBtnUp,BorderLayout.NORTH );
        vPaneDirection.add(this.aBtnDown,BorderLayout.SOUTH );
        vPaneDirection.add(this.aBtnDownWest,BorderLayout.WEST );
        vPaneDirection.add(this.aBtnDownEast,BorderLayout.EAST );
        vPaneDirection.add(this.aBtnUpNorth,BorderLayout.NORTH );
        vPaneDirection.add(this.aBtnUpEast,BorderLayout.EAST );
        vPaneDirection.add(this.aBtnUpWest,BorderLayout.WEST );
        vPaneDirection.add(this.aBtnDownSouth,BorderLayout.SOUTH );
        
        
        JPanel vMainPanel = new JPanel();      
        vMainPanel.setLayout( new BorderLayout()); // ==> only five places
        vMainPanel.add( this.aImage, BorderLayout.NORTH );
        vMainPanel.add( vListScroller, BorderLayout.CENTER );
        vMainPanel.add( this.aEntryField, BorderLayout.SOUTH );
        vMainPanel.add( vPaneCommand, BorderLayout.EAST);//le placer à l'est
        vMainPanel.add( vPaneDirection, BorderLayout.WEST);//le placer à l'est
        
        this.aMyFrame.getContentPane().add( vMainPanel, BorderLayout.CENTER );

        // add some event listeners to some components
        this.aEntryField.addActionListener( this );
        
        this.aBtnAudio.addActionListener( this );
        this.aBtnQuit.addActionListener( this );
        this.aBtnHelp.addActionListener( this );
        this.aBtnEat.addActionListener( this );
        this.aBtnLook.addActionListener( this );
        
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
     * Actionlistener interface for entry textfield.
     */
    @Override public void actionPerformed( final ActionEvent pE ) 
    {
    String vGo = "go";
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
        
    }else if(pE.getSource() == this.aBtnNorth ){
        this.aEngine.goRoom(new Command(vGo, "north"));
            
    }else if(pE.getSource() == this.aBtnSouth ){
        this.aEngine.goRoom(new Command(vGo, "south"));
    
    }else if(pE.getSource() == this.aBtnEast ){
        this.aEngine.goRoom(new Command(vGo, "east"));
    
    }else if(pE.getSource() == this.aBtnWest ){
        this.aEngine.goRoom(new Command(vGo, "west"));
        
    }else if(pE.getSource() == this.aBtnUp ){
        this.aEngine.goRoom(new Command(vGo, "up"));
    
    }else if(pE.getSource() == this.aBtnDown ){
        this.aEngine.goRoom(new Command(vGo, "down"));
        
    }else if(pE.getSource() == this.aBtnDownWest ){
        this.aEngine.goRoom(new Command(vGo, "downwest"));
        
    }else if(pE.getSource() == this.aBtnDownEast ){
        this.aEngine.goRoom(new Command(vGo, "downeast"));
        
    }else if(pE.getSource() == this.aBtnUpNorth ){
        this.aEngine.goRoom(new Command(vGo, "upnorth"));
        
    }else if(pE.getSource() == this.aBtnUpEast ){
        this.aEngine.goRoom(new Command(vGo, "upeast"));
        
    }else if(pE.getSource() == this.aBtnUpWest ){
        this.aEngine.goRoom(new Command(vGo, "upwest"));
        
    }else if(pE.getSource() == this.aBtnDownSouth ){
        this.aEngine.goRoom(new Command(vGo, "downsouth"));
    
    }
        else 
        // no need to check the type of action at the moment
        // because there is only one possible action (text input)
    this.processCommand(); // never suppress this line
    } // actionPerformed(.)

    /**
     * A command has been entered in the entry field.  
     * Read the command and do whatever is necessary to process it.
     */
    private void processCommand(){
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "\n" );

        this.aEngine.interpretCommand( vInput );
    } // processCommand()
    
    public void closeFrame() {
        if (this.aMyFrame != null) {
            this.aMyFrame.dispose();
        }
    }
    
    
} // UserInterface 
