package maze;
import java.awt.*;

/**
 * Created by gtx on 2017/7/7.
 */
public class BrownDoor extends Door implements Cloneable{
    public BrownDoor(){}
    public BrownDoor(final Room r1,final Room r2){
        super(r1,r2);
    }
    public Color getColor()
    {
        return Color.yellow;
    }

}
