package com.kuznik.test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.BeforeClass;
import static org.junit.Assert.*;

import com.kuznik.model.Class;
import com.kuznik.model.Pupil;
import com.kuznik.model.School;
import com.kuznik.model.Subject;
import com.kuznik.service.SchoolService;

public class OneClassWithConstAvgTest {
	private static String[] PUPILS_NAMES = { "Adam", "Jacek", "Piotr", "Rafał", "Marek" };
	private static SchoolService schoolService = new SchoolService();
	private static Map<Class, List<Pupil>> mathPrimus = new HashMap<>();
	private static Map<Class, List<Pupil>> physicsPrimus = new HashMap<>();

	@BeforeClass
	public static void createSchool() {
		School school = new School();
		for (int i = 0; i < 1; i++) {
			Class c = new Class("Klasa " + (i + 1));
			List<Pupil> primusMathPupilList = new LinkedList<>();
			List<Pupil> primusPhysicsPupilList = new LinkedList<>();
			float primusAvgMath = Float.MIN_VALUE;
			float primusAvgPhysics = Float.MIN_VALUE;

			for (int j = 0; j < PUPILS_NAMES.length; j++) {
				Pupil p = new Pupil(PUPILS_NAMES[j]);
				float avgMath = j + 1;
				p.setAvgMath(avgMath);
				float avgPhysics = PUPILS_NAMES.length - j;
				p.setAvgPhysics(avgPhysics);
				c.getPupilList().add(p);
				if (primusAvgMath < avgMath) {
					primusAvgMath = avgMath;
					primusMathPupilList = new LinkedList<>();
					primusMathPupilList.add(p);
				} else if (primusAvgMath == avgMath) {
					primusMathPupilList.add(p);
				}
				if (primusAvgPhysics < avgPhysics) {
					primusAvgPhysics = avgPhysics;
					primusPhysicsPupilList = new LinkedList<>();
					primusPhysicsPupilList.add(p);
				} else if (primusAvgPhysics == avgPhysics) {
					primusPhysicsPupilList.add(p);
				}
			}
			
			mathPrimus.put(c, primusMathPupilList);
			physicsPrimus.put(c, primusPhysicsPupilList);
			
			school.getClassList().add(c);
		}
		schoolService.setSchool(school);
		System.out.println(school);
		System.out.println();
	}

	@org.junit.Test
	public void testMath() {
		System.out.println("Math primus");
		Map<Class, List<Pupil>> mathPrimus = schoolService.getPrimusOfSubject(Subject.MATH);
		for (Entry<Class, List<Pupil>> entry : mathPrimus.entrySet()) {
			System.out.println(entry.getKey().getName() + " " + entry.getValue());
		}
		assertEquals(OneClassWithConstAvgTest.mathPrimus, mathPrimus);
	}

	@org.junit.Test
	public void testPhysics() {
		System.out.println("Physics primus");
		Map<Class, List<Pupil>> physicsPrimus = schoolService.getPrimusOfSubject(Subject.PHYSICS);
		for (Entry<Class, List<Pupil>> entry : physicsPrimus.entrySet()) {
			System.out.println(entry.getKey().getName() + " " + entry.getValue());
		}
		assertEquals(OneClassWithConstAvgTest.physicsPrimus, physicsPrimus);
	}

}
