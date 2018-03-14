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
public class MazeGameAbstractFactory {

    private static Map<Room,String[] > roomMap=new HashMap<>();
    private static Map<String,Door> doorMap=new HashMap<>();
    private static ArrayList<Room> rooms=new ArrayList<>();

    /**
     * Creates a small maze.
     */
    public Maze createMaze(MazeFactory mazeFactory) {
        Maze maze = new Maze();
        Room room0 = mazeFactory.makeRoom(0);
        Room room1 = mazeFactory.makeRoom(1);
        Door door = mazeFactory.makeDoor(room0,room1);
        maze.addRoom(room0);
        maze.addRoom(room1);
        room0.setSide(Direction.South, door);
        room0.setSide(Direction.North, mazeFactory.makeWall());
        room0.setSide(Direction.West, mazeFactory.makeWall());
        room0.setSide(Direction.East, mazeFactory.makeWall());
        room1.setSide(Direction.North, door);
        room1.setSide(Direction.South, mazeFactory.makeWall());
        room1.setSide(Direction.West, mazeFactory.makeWall());
        room1.setSide(Direction.East, mazeFactory.makeWall());
        maze.setCurrentRoom(room0);
        return maze;
    }

    public Maze loadMaze(final String path,MazeFactory mazeFactory) throws FileNotFoundException {
        Maze maze=new Maze();
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNext()) {     //读入文件
            String mapSite = scanner.next();
            if (mapSite.equals("room")) {
                Room room;
                int num=scanner.nextInt();
                if (mazeFactory==null)
                    room=makeRoom(num);
                else
                    room=mazeFactory.makeRoom(num);
                String str[]=new String[room.getSidesNum()+1];
                for (int i=0;i<room.getSidesNum();i++)
                    str[i]=scanner.next();
                roomMap.put(room,str);
                rooms.add(room);
            } else if (mapSite.equals("door")) {
                String doorName=scanner.next();
                Room r1=rooms.get(scanner.nextInt());
                Room r2=rooms.get(scanner.nextInt());
                Door door;
                if (mazeFactory==null)
                    door=makeDoor(r1,r2);
                else
                    door=mazeFactory.makeDoor(r1,r2);
                doorMap.put(doorName,door);
            } else continue;
        }

        for (int i=0;i<rooms.size();i++){
            maze.addRoom(rooms.get(i));
            String m[]=roomMap.get(rooms.get(i));
            for (int j=0;j<rooms.get(i).getSidesNum();j++){
                MapSite mapSite=parseString(m[j],mazeFactory);
                rooms.get(i).setSide(Direction.values()[j],mapSite);
            }
        }
        maze.setCurrentRoom(rooms.get(0));
        return maze;
    }

    public MapSite parseString(String string,MazeFactory mazeFactory){
        if (string.equals("wall")) {
            if (mazeFactory==null)
                return makeWall();
            else
                return mazeFactory.makeWall();
        }
        else if (string.substring(0,1).equals("d"))
            return doorMap.get(string);
        else
            return rooms.get(Integer.parseInt(string));
    }

    public Wall makeWall(){
        return new Wall();
    }

    public Door makeDoor(Room room1,Room room2){
        return new Door(room1,room2);
    }

    public Room makeRoom(int num){
        return new Room(num);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Maze maze;
        MazeGameAbstractFactory mazeGameAbstractFactory = new MazeGameAbstractFactory();
        MazeFactory mazeFactory;

        if (args.length==1) {  //一个参数
            if (args[0].equals("large")||args[0].equals("small"))
                maze =mazeGameAbstractFactory.loadMaze(args[0]+".maze",null);
            else if (args[0].equals("red"))
                maze=mazeGameAbstractFactory.createMaze(new RedMazeFactory());
            else if (args[0].equals("blue"))
                maze=mazeGameAbstractFactory.createMaze(new BlueMazeFactory());
            else
                maze=null;
        }

        else {
            if (args.length==2){  //两个参数
                if (args[1].equals("red"))
                    mazeFactory=new RedMazeFactory();
                else if (args[1].equals("blue"))
                    mazeFactory=new BlueMazeFactory();
                else{
                    mazeFactory=null;
                }
            }
            else {  //自定义颜色
                Wall wall=new RedWall();
                Room room=new PinkRoom();
                Door door=new GreenDoor();
                for (int i=0;i<args.length-1;i++){
                    switch (args[i+1].substring(0,5)){
                        case "wall=":
                            if (args[i+1].substring(5,args[i+1].length()).equals("blue"))
                                wall=new BlueWall();
                            break;
                        case "door=":
                            if (args[i+1].substring(5,args[i+1].length()).equals("brown"))
                                door=new BrownDoor();
                            break;
                        case "room=":
                            if (args[i+1].substring(5,args[i+1].length()).equals("lightGray"))
                                room=new LightGrayRoom();
                            break;
                    }
                }
                mazeFactory=new PrototypeMazeFactory(wall,door,room);
            }
            maze =mazeGameAbstractFactory.loadMaze(args[0]+".maze",mazeFactory);
        }

        MazeViewer viewer = new MazeViewer(maze);
        viewer.run();
    }
}
