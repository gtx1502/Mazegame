
/*
 * Room.java
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
import java.awt.Color;

/**
 *
 * @author Sunny
 * @version 1.0
 * @since 1.0
 */
public class Room extends MapSite implements Cloneable
{
	private final int SIDES_NUM=4;
	private MapSite[] sides = new MapSite[SIDES_NUM];
	private int number;

	public Room(){}
	public Room(int num)
	{
		number = num;
	}

	public final MapSite getSide(Direction dir)
	{
		return sides[dir.ordinal()];
	}
	
	public final void setSide(Direction dir, MapSite site)
	{
		sides[dir.ordinal()] = site;
	}

	public final int getNumber()
	{
		return number;
	}
	public Room init(int num){
		this.number=num;
		return this;
	}

	@Override
	public boolean enter()
	{
		super.notifyEntryListeners();
		return true;
	}

	@Override
	public Color getColor()
	{
		return Color.WHITE;
	}
	public int getSidesNum(){
		return SIDES_NUM;
	}
	public Room clone(){
		Room room=null;
		try {
			room =(Room) super.clone();
			room.sides=this.sides.clone();

		}catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return room;
	}
}
