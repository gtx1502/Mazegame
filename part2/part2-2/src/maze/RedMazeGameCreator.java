package maze;

/**
 * Created by gtx on 2017/7/6.
 */
public class RedMazeGameCreator extends MazeGameCreator{
    public Wall makeWall(){
        return new RedWall();
    }

    public Door makeDoor(Room room1,Room room2){
        return new GreenDoor(room1,room2);
    }

    public Room makeRoom(int num){
        return new PinkRoom(num);
    }
}
