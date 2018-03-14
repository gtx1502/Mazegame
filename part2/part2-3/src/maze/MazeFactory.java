package maze;
/**
 * Created by gtx on 2017/7/7.
 */
public interface MazeFactory {
    public Wall makeWall();
    public Door makeDoor(Room room1,Room room2);
    public Room makeRoom(int num);
}
