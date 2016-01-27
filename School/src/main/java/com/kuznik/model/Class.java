package com.kuznik.model;

import java.util.LinkedList;
import java.util.List;

public class Class {
	private String name;
	private List<Pupil> pupilList = new LinkedList<>();

	public Class(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Pupil> getPupilList() {
		return pupilList;
	}

	public void setPupilList(List<Pupil> pupilList) {
		this.pupilList = pupilList;
	}
	
	@Override
	public String toString() {
		return name + " " + pupilList;
	}
}
