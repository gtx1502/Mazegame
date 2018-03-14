package maze;
import java.awt.*;

/**
 * Created by gtx on 2017/7/7.
 */
public class LightGrayRoom extends Room{
    public LightGrayRoom(int num){
        super(num);
    }
    public Color getColor()
    {
        return Color.LIGHT_GRAY;
    }
}
