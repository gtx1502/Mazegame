package maze;

/**
 * Created by gtx on 2017/7/6.
 */
public class BlueMazeGameCreator extends MazeGameCreator{
    public Wall makeWall(){
        return new BlueWall();
    }

    public Door makeDoor(Room room1,Room room2){
        return new BrownDoor(room1,room2);
    }

    public Room makeRoom(int num){
        return new LightGrayRoom(num);
    }
}
