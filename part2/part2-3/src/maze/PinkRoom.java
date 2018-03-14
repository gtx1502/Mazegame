package maze;
import java.awt.*;

/**
 * Created by gtx on 2017/7/7.
 */
public class PinkRoom extends Room implements Cloneable{
    public PinkRoom(){}
    public PinkRoom(int num){
        super(num);
    }
    public Color getColor()
    {
        return Color.PINK;
    }

}
