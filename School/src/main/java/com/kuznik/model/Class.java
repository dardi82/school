package com.kuznik.model;

import java.util.Collections;
import java.util.Comparator;
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

	public Pupil getPrimusOfSubject(Subject subject){
		return Collections.max(pupilList, new Comparator<Pupil>() {
			@Override
			public int compare(Pupil o1, Pupil o2) {
				return Float.compare(o1.getAvgSubject(subject), o2.getAvgSubject(subject));
			}
		});
		
	}
	
	@Override
	public String toString() {
		return name + " " + pupilList;
	}
}
