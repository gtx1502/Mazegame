package maze;

/**
 * Created by gtx on 2017/7/7.
 */
public class PrototypeMazeFactory implements MazeFactory{
    private Room room_prototype;
    private Door door_prototype;
    private Wall wall_prototype;
    public PrototypeMazeFactory(Wall wall, Door door, Room room){
        this.wall_prototype = wall;
        this.door_prototype = door;
        this.room_prototype = room;
    }
    public Wall makeWall(){
        return wall_prototype.clone();
    }

    public Door makeDoor(Room r1,Room r2){
        return door_prototype.clone().init(r1,r2);
    }

    public Room makeRoom(int num){
        return room_prototype.clone().init(num);
    }

}
