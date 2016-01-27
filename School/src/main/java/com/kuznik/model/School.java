package com.kuznik.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class School {
	private List<Class> classList = new LinkedList<>();

	public List<Class> getClassList() {
		return classList;
	}

	@Override
	public String toString() {
		return classList.toString();
	}

	public Map<Class, Pupil> getPrimusOfSubject(Subject subject) {
		Map<Class, Pupil> map = new HashMap<>();
		for (Class clazz : classList) {
			map.put(clazz, clazz.getPrimusOfSubject(subject));
		}
		return map;
	}

}
