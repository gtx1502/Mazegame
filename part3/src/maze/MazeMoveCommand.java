package maze;

/**
 * Created by gtx on 2017/7/10.
 */
public class MazeMoveCommand implements UndoableCommand{
    private Maze maze;
    private Direction dir;
    private Boolean enter=false;
    public Direction getDir(){
        return dir;
    }
    public Boolean getEnter(){
        return enter;
    }
    public MazeMoveCommand(Maze maze,Direction dir){
        this.maze=maze;
        this.dir=dir;
    }
    private void move(Direction dir)
    {
        Room curRoom = maze.getCurrentRoom();
        MapSite side = curRoom.getSide(dir);
        side.enter();
        if (side instanceof Room)
            maze.setCurrentRoom((Room)side);
        else if (side instanceof Door) {
            maze.setCurrentRoom(((Door)side).getOtherSide(curRoom));
            enter=maze.getCurrentRoom().enter();
        }
    }

    public void execute(){
        move(dir);
    }
    public void undo(){
        Direction opposite=null;
        switch (dir){
            case North:
                opposite=Direction.South;
                break;
            case South:
                opposite=Direction.North;
                break;
            case East:
                opposite=Direction.West;
                break;
            case West:
                opposite=Direction.East;
                break;
        }
        move(opposite);
    }
}
