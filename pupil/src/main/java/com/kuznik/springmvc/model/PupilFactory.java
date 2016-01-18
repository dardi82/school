package com.kuznik.springmvc.model;

public class PupilFactory {
	private static final NullPupil nullPupil = new NullPupil();
	
	public static AbstractPupil getPupil(Pupil p){
		return p == null ? nullPupil : p;
	}
}
