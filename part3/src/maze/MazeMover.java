package maze;

import java.util.Stack;

public class MazeMover implements MazeMoveListener {

	Stack<Command> moves = new Stack<Command>();
	
	@Override
	public void moveEast(Maze maze) {
	//1. create a new MazeMovecommend();
	//2. push the command into the stack
	//3. execute the command by calling the execute() method inherit from the Command interface. 	
		MazeMoveCommand mazeMoveCommand=new MazeMoveCommand(maze,Direction.East);
		int num=maze.getCurrentRoom().getNumber();
		mazeMoveCommand.execute();
		if (num!=maze.getCurrentRoom().getNumber())
			moves.push(mazeMoveCommand);

	}

	@Override
	public void moveNorth(Maze maze) {
	//1. create a new MazeMovecommend();
	//2. push the command into the stack
	//3. execute the command by calling the execute() method inherit from the Command interface. 	
		MazeMoveCommand mazeMoveCommand=new MazeMoveCommand(maze,Direction.North);
		int num=maze.getCurrentRoom().getNumber();
		mazeMoveCommand.execute();
		if (num!=maze.getCurrentRoom().getNumber())
			moves.push(mazeMoveCommand);
	}

	@Override
	public void moveSouth(Maze maze) {
	//1. create a new MazeMovecommend();
	//2. push the command into the stack
	//3. execute the command by calling the execute() method inherit from the Command interface. 	
		MazeMoveCommand mazeMoveCommand=new MazeMoveCommand(maze,Direction.South);
		int num=maze.getCurrentRoom().getNumber();
		mazeMoveCommand.execute();
		if (num!=maze.getCurrentRoom().getNumber())
			moves.push(mazeMoveCommand);

	}

	@Override
	public void moveWest(Maze maze) {
	//1. create a new MazeMovecommend();
	//2. push the command into the stack
	//3. execute the command by calling the execute() method inherit from the Command interface.
		MazeMoveCommand mazeMoveCommand=new MazeMoveCommand(maze,Direction.West);
		int num=maze.getCurrentRoom().getNumber();
		mazeMoveCommand.execute();
		if (num!=maze.getCurrentRoom().getNumber())
			moves.push(mazeMoveCommand);
	}
	
	@Override
	public void undoMove() {
	//1. check if the stack is empty, if not then
	//2. pop out one command
	//3. undo the command by calling the undo() method inherit from the UndoableCommand interface.
		if (moves.empty())
			return;
		Command command=moves.pop();
		MazeMoveCommand mazeMoveCommand=(MazeMoveCommand) command;
		mazeMoveCommand.undo();
	}

}
