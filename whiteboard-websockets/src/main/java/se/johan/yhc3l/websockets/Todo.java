package se.johan.yhc3l.websockets;

import java.awt.List;
import java.util.ArrayList;

public class Todo {
	private String id;
	private String desc;
	private ArrayList<String> listinfo;
	private String color;
	private boolean done;
	private String action;
	

	public Todo() {
		listinfo = new ArrayList<String>();
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isDone() {
		return done;
	}

	public void setDone(boolean done) {
		this.done = done;
	}

	public ArrayList<String> getListinfo() {
		return listinfo;
	}

	public void setListinfo(ArrayList<String> listinfo) {
		this.listinfo = listinfo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

}
