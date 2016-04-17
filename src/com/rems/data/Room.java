package com.rems.data;
/**
 * @name: Room
 * @author: Team Amrita Virdi
 * @description: This is a bean class for Room Table which contains a default
 *               constructor, class variables.
 **/
public class Room implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer roomId;
	private String roomDescription;
	
	public Room() {
	}

	
	public String getRoomDescription() {
		return roomDescription;
	}

	public void setRoomDescription(String roomDescription) {
		this.roomDescription = roomDescription;
	}

	public Integer getRoomId() {
		return this.roomId;
	}

	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}

}
