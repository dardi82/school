package com.kuznik.model;

import java.util.LinkedList;
import java.util.List;

public class School {
	private List<Class> classList = new LinkedList<>();

	public List<Class> getClassList() {
		return classList;
	}

	public void setClassList(List<Class> classList) {
		this.classList = classList;
	}

	@Override
	public String toString() {
		return classList.toString();
	}
}
