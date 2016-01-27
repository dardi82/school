package com.kuznik.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.kuznik.model.Class;
import com.kuznik.model.Pupil;
import com.kuznik.model.School;
import com.kuznik.model.Subject;

public class SchoolService {
	private School school;

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	@Deprecated
	public Map<Class, List<Pupil>> getPrimusOfSubject(Subject subject) {
		Map<Class, List<Pupil>> map = new HashMap<>();
		if (school != null) {
			for (Class clazz : school.getClassList()) {
				List<Pupil> primusPupilList = new LinkedList<>();
				if (!clazz.getPupilList().isEmpty()) {
					Pupil primusPupil = clazz.getPupilList().get(0);
					for (Pupil pupil : clazz.getPupilList()) {
						int compare = Float.compare(pupil.getAvgSubject(subject), primusPupil.getAvgSubject(subject));
						if (compare > 0) {
							primusPupil = pupil;
							primusPupilList = new LinkedList<>();
							primusPupilList.add(primusPupil);
						} else if (compare == 0) {
							primusPupilList.add(pupil);
						}
					}
				}
				map.put(clazz, primusPupilList);
			}
		}
		return map;
	}
	
	public Map<Class, Pupil> getOnePrimusOfSubjectPerClass(Subject subject) {
		Map<Class, Pupil> map = new HashMap<>();
		if(school != null){
			map.putAll(school.getPrimusOfSubject(subject));
		}
		return map;
	}

}
