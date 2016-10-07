import java.util.ArrayList; 

public class Area implements AdventureConstants 
{ 
    private int north;
    private int south;
    private int east;                                                                                                                                 
    private int west;
    private int northeast;
    private int southeast;
    private int southwest;
    private int northwest;
    private int up;
    private int down;
    private boolean visited;
    private ArrayList<String> inventory;
    private String shortDescription;
    private String longDescription;
    
    public Area()
    { 
        north = NO_EXIT;
        south = NO_EXIT;
        east = NO_EXIT;
        west = NO_EXIT;
        northeast = NO_EXIT;
        southeast = NO_EXIT;
        southwest = NO_EXIT;
        northwest = NO_EXIT;
        up = NO_EXIT;
        down = NO_EXIT;
        visited = false;
        inventory = new ArrayList<String>();
        shortDescription = "";
        longDescription = "";
    } 

    public void setNorth (int newNorth)
    {
        if(newNorth < -1)
            System.out.println("That is invalid");
        else
            north = newNorth;
    }    
    
    public void setSouth (int newSouth)
    {
        if(newSouth < -1)
            System.out.println("That is invalid");
        else
            south = newSouth;
    } 
    
    public void setEast (int newEast)
    {
        if(newEast < -1)
            System.out.println("That is invalid");
        else
            east = newEast;
    } 
    
    public void setWest (int newWest)
    {
        if(newWest < -1)
            System.out.println("That is invalid");
        else
            west = newWest;
    } 
    
    public void setNortheast (int newNortheast)
    {
        if(newNortheast < -1)
            System.out.println("That is invalid");
        else 
            northeast = newNortheast;
        
    } 
    
    public void setSoutheast (int newSoutheast)
    {
        if(newSoutheast < -1)
            System.out.println("That is invalid");
        else
            southeast = newSoutheast;
    } 
    
    public void setSouthwest (int newSouthwest)
    {
        if(newSouthwest < -1)
            System.out.println("That is invalid");
        else    
            southwest = newSouthwest;
    } 
    
    public void setNorthwest (int newNorthwest)
    {
        if(newNorthwest < -1)
            System.out.println("That is invalid");
        else    
            northwest = newNorthwest;
    } 
    
    public void setUp (int newUp)
    {
        if(newUp < -1)
            System.out.println("That is invalid");
        else    
            up = newUp;
    } 
    
    public void setDown (int newDown)
    {
        if(newDown < -1)
            System.out.println("That is an invalid method");
        else    
            down = newDown;
    } 
    
    public void setVisited (boolean newVisited)
    {
        visited = newVisited;
    } 
    
    public void setLongDescription (String newLongDescription)
    {
        longDescription = newLongDescription;
    } 
    
    public void setShortDescription (String newShortDescription)
    {
        shortDescription = newShortDescription;
    } 
    
    public int getNorth()
    {
        return north;
    }  
    
    public int getSouth()
    {
        return south;
    }
    
    public int getEast()
    {
        return east;
    }
    
    public int getWest()
    {
        return west;
    }
    
    public int getNortheast()
    {
        return northeast;
    }
    
    public int getSoutheast()    
    {
        return southeast;
    }
    
    public int getSouthwest()
    {
        return southwest;
    }
    
    public int getNorthwest()
    {
        return northwest;
    }
    
    public int getUp()
    {
        return up;
    }
    
    public int getDown()
    {
        return down;
    }
    
    public boolean getVisited()
    {
        return visited;
    }    
    
    public String getLongDescription()
    {
        return longDescription;
    }  
    
    public String getShortDescription()
    {
        return shortDescription;
    }  
    
    public void addItem(String newItem)
    {
        inventory.add(newItem);
    }
    
    public boolean removeItem(String oldItem)
    {
        if(inventory.remove(oldItem))
            return true;
        else
            return false;
    }        
     
    public void displayInventory()  
    {
        if (!inventory.isEmpty())
        {
            System.out.println("The room contains these items:");
            for (String items: inventory)
                System.out.println(items);
        }       
    }    
 
    public ArrayList getInventory()
    {  
        return inventory;
    }    
    
    public void displayExits()
    {
        if(north == NO_EXIT && south == NO_EXIT && east == NO_EXIT && west == NO_EXIT && northwest == NO_EXIT && northeast == NO_EXIT && southeast == NO_EXIT && southwest == NO_EXIT && up == NO_EXIT && down == NO_EXIT)
        {
            System.out.println("You cannot go anywhere from here. There are no available exits");
            System.out.println(" ");
        }    
        else
        {
            System.out.println("From here you can go: ");
            
            if(north != NO_EXIT)
            {
                System.out.print("North ");
            }
            
            if(south != NO_EXIT)
            {
                System.out.print("South ");
            }
            
            if(east != NO_EXIT)
            {
                System.out.print("East ");
            }
            
            if(west != NO_EXIT)
            {
                System.out.print("West ");
            }
            
            if(northeast != NO_EXIT)
            {
                System.out.print("Northeast ");
            }
            
            if(southeast != NO_EXIT)
            {
                System.out.print("Southeast ");
            }
            
            if(southwest != NO_EXIT)
            {
                System.out.print("Southwest ");
            }
            
            if(northwest != NO_EXIT)
            {
                System.out.print("Northwest ");
            }
            
            if(up != NO_EXIT)
            {
                System.out.print("Up ");
            }
            
            if(down != NO_EXIT)
            {
                System.out.print("Down ");
            }
            
            System.out.println("\n");
        } 
        
    }
       
} 
