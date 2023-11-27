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
    private JButton    aBtnAudio;
    private JButton    aBtnQuit;
    private JButton    aBtnHelp;
    private JButton    aBtnEat;
    private JButton atest;
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
        this.aBtnAudio.setPreferredSize(new Dimension(150,50));
        
        this.aBtnQuit = new JButton("quit");
        this.aBtnQuit.setPreferredSize(new Dimension(150,50));
    
        this.aBtnHelp = new JButton("help");
        this.aBtnHelp.setPreferredSize(new Dimension(150,50));
        
        this.aBtnEat = new JButton("eat");
        this.aBtnEat.setPreferredSize(new Dimension(150,50));
        
        
        //JLabel
        this.aImage = new JLabel();

        //temporraire je change la size de l'emplacement de l'image 
        //this.aImage.setPreferredSize(new Dimension(500, 400));
        
        JPanel vPanel = new JPanel();
        JPanel vPane2 = new JPanel();
        vPanel.setLayout( new BorderLayout() ); // ==> only five places
        vPanel.add( this.aImage, BorderLayout.NORTH );
        vPanel.add( vListScroller, BorderLayout.CENTER );
        vPanel.add( this.aEntryField, BorderLayout.SOUTH );
        vPanel.add( vPane2, BorderLayout.EAST);//le placer à l'est
        
        
        vPane2.setLayout( new BorderLayout() );
        vPane2.add(this.aBtnAudio,BorderLayout.NORTH );
        vPane2.add(this.aBtnQuit,BorderLayout.SOUTH );
        vPane2.add(this.aBtnHelp,BorderLayout.EAST );
        vPane2.add(this.aBtnEat,BorderLayout.WEST );
        
        this.aMyFrame.getContentPane().add( vPanel, BorderLayout.CENTER );

        // add some event listeners to some components
        this.aEntryField.addActionListener( this );
        this.aBtnAudio.addActionListener( this );
        this.aBtnQuit.addActionListener( this );
        this.aBtnHelp.addActionListener( this );
        this.aBtnEat.addActionListener( this );

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
        if (pE.getSource() == this.aBtnAudio) {
            this.aBool =! this.aBool;
            if(aBool == false) {
                this.aBtnAudio.setText("stop audio");
                this.aEngine.toggleAudio();
            }else {
                this.aBtnAudio.setText("audio");
                this.aEngine.toggleAudio();
            }
        } else if(pE.getSource() == this.aBtnQuit ){
            this.aEngine.interpretCommand(this.aBtnQuit.getActionCommand());   
        
        } else if(pE.getSource() == this.aBtnHelp ){
            this.aEngine.interpretCommand(this.aBtnHelp.getActionCommand()); 
        
        }else if(pE.getSource() == this.aBtnEat ){
            this.aEngine.interpretCommand(this.aBtnEat.getActionCommand());
        
        }else 
        // no need to check the type of action at the moment
        // because there is only one possible action (text input)
        this.processCommand(); // never suppress this line
        } // actionPerformed(.)

    /**
     * A command has been entered in the entry field.  
     * Read the command and do whatever is necessary to process it.
     */
    private void processCommand()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );

        this.aEngine.interpretCommand( vInput );
    } // processCommand()
    
    
} // UserInterface 
