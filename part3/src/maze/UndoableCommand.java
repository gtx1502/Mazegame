package maze;

/**
 * Created by gtx on 2017/7/10.
 */
public interface UndoableCommand extends Command{
    public void undo();
}
