package com.kuznik.model;

public class Pupil {
	private String name;
	private float avgMath;
	private float avgPhysics;

	public Pupil(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getAvgMath() {
		return avgMath;
	}

	public void setAvgMath(float avgMath) {
		this.avgMath = avgMath;
	}

	public float getAvgPhysics() {
		return avgPhysics;
	}

	public void setAvgPhysics(float avgPhysics) {
		this.avgPhysics = avgPhysics;
	}

	@Override
	public String toString() {
		return name + " [avgMath=" + avgMath + ";avgPhysics=" + avgPhysics + "]";
	}

	public float getAvgSubject(Subject subject) {
		switch (subject) {
		case MATH:
			return avgMath;
		case PHYSICS:
			return avgPhysics;
		}
		return -1;
	}
}
