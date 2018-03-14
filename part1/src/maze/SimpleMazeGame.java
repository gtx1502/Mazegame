/*
 * SimpleMazeGame.java
 * Copyright (c) 2008, Drexel University.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the Drexel University nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY DREXEL UNIVERSITY ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL DREXEL UNIVERSITY BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package maze;

import maze.ui.MazeViewer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Sunny
 * @version 1.0
 * @since 1.0
 */
public class SimpleMazeGame {

    private static Map<Room,String[] > roomMap=new HashMap<>();
    private static Map<String,Door> doorMap=new HashMap<>();
    private static ArrayList<Room> rooms=new ArrayList<>();

    /**
     * Creates a small maze.
     */
    public Maze createMaze() {
        Maze maze = new Maze();
        Room room0 = new Room(0);
        Room room1 = new Room(1);
        Door door = new Door(room0, room1);
        Wall wall0 = new Wall();
        Wall wall1 = new Wall();
        Wall wall2 = new Wall();
        Wall wall3 = new Wall();
        Wall wall4 = new Wall();
        Wall wall5 = new Wall();
        maze.addRoom(room0);
        maze.addRoom(room1);
        room0.setSide(Direction.South, door);
        room0.setSide(Direction.North, wall0);
        room0.setSide(Direction.West, wall1);
        room0.setSide(Direction.East, wall2);
        room1.setSide(Direction.North, door);
        room1.setSide(Direction.South, wall3);
        room1.setSide(Direction.West, wall4);
        room1.setSide(Direction.East, wall5);
        maze.setCurrentRoom(room0);
        return maze;
    }

    public Maze loadMaze(final String path) throws FileNotFoundException {
        Maze maze=new Maze();
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNext()) {     //读入文件
            String mapSite = scanner.next();
            if (mapSite.equals("room")) {
                Room room=new Room(scanner.nextInt());
                String str[]=new String[room.getSidesNum()+1];
                for (int i=0;i<room.getSidesNum();i++)
                    str[i]=scanner.next();
                roomMap.put(room,str);
                rooms.add(room);
            } else if (mapSite.equals("door")) {
                String doorName=scanner.next();
                Room r1=rooms.get(scanner.nextInt());
                Room r2=rooms.get(scanner.nextInt());
                Door door=new Door(r1,r2);
                doorMap.put(doorName,door);
            } else continue;
        }
        for (int i=0;i<rooms.size();i++){
            maze.addRoom(rooms.get(i));
            String m[]=roomMap.get(rooms.get(i));
            for (int j=0;j<rooms.get(i).getSidesNum();j++){
                MapSite mapSite=parseString(m[j]);
                rooms.get(i).setSide(Direction.values()[j],mapSite);
            }
        }
        maze.setCurrentRoom(rooms.get(0));
        return maze;
    }

    public MapSite parseString(String string){
        if (string.equals("wall"))
            return new Wall();
        else if (string.substring(0,1).equals("d"))
            return doorMap.get(string);
        else
            return rooms.get(Integer.parseInt(string));
    }
    public static void main(String[] args) throws FileNotFoundException {
        SimpleMazeGame simpleMazeGame = new SimpleMazeGame();
        Maze maze;
        if (args.length==0)
            maze = simpleMazeGame.createMaze();
        else
            maze = simpleMazeGame.loadMaze(args[0]);
        MazeViewer viewer = new MazeViewer(maze);
        viewer.run();
    }
}
