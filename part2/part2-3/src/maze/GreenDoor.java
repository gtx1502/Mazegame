package maze;
import java.awt.*;

public class GreenDoor extends Door implements Cloneable{
    public GreenDoor(){}
    public GreenDoor(final Room r1,final Room r2){
        super(r1,r2);
    }
    public Color getColor()
    {
        return Color.GREEN;
    }
}
