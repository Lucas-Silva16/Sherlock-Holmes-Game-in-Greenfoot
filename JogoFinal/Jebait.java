import greenfoot.*;  

public class Jebait extends World
{
    GreenfootSound musica = new GreenfootSound("Never.mp3");

    public Jebait()
    {    
        super(220, 220, 1); 
        addObject(new GifActor(), getWidth()/2, getHeight()/2);
        musica.playLoop(); 
    }
}

class GifActor extends Actor
{
    private GifImage myGif = new GifImage("rr.gif");
    public void act()
    {
        setImage(myGif.getCurrentImage());
    }
}


