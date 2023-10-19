import java.util.Set;
import java.util.HashMap;

/*
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0 (February 2002) DBMOD:04/04/2008, 2019
 */

public class Room 
{
    private String aDescription;
    private HashMap<String,Room> aExits; // stores exits of this room
    // private String aImageName;

    /**
     * Create a room described by "aDescription" with a given image. 
     * Initially, it has no exits. "aDescription" is something like 
     * "in a kitchen" or "in an open court yard".
     */
    public Room( final String pDescription ) //final String pImage ) 
    {
        this.aDescription = pDescription;
        this.aExits = new HashMap<String,Room>();
    // this.aImageName = pImage;
    }

    /**
     * Define an exit from this room.
     */
    public void setExit(String direction, Room neighbor) 
    {
        this.aExits.put(direction, neighbor);
    }

    /**
     * Return the description of the room (the one that was defined in the
     * constructor).
     */
    public String getShortDescription()
    {
        return this.aDescription;
    }

    /**
     * Return a long description of this room, in the form:
     *     You are in the kitchen.
     *     Exits: north west
     */
    public String getLongDescription()
    {
        return "You are " + this.aDescription + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     */
    private String getExitString()
    {
        StringBuilder returnString = new StringBuilder( "Exits:" );
        for ( String vS : this.aExits.keySet() )
            returnString.append( " " + vS );
        return returnString.toString();
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     */
    public Room getExit(String direction) 
    {
        return this.aExits.get(direction);
    }

    /**
     * Return a string describing the room's image name
     */
    // public String getImageName()
    // {
    //     return this.aImageName;
    // }
}

