package com.kuznik.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.junit.BeforeClass;

import com.kuznik.model.Class;
import com.kuznik.model.Pupil;
import com.kuznik.model.School;
import com.kuznik.model.Subject;
import com.kuznik.service.SchoolService;

public class OnePrimusPerClassIntegrationTest {
	private static final String[] PUPILS_NAMES = { "Adam", "Jacek", "Piotr", "Rafał", "Marek" };
	private static final int CLASS_COUNT = 5; 
	private static final School SCHOOL = new School();
	private static final Map<Class, Pupil> EXPECTED_MATH_PRIMUS_MAP = new HashMap<>();
	private static final Map<Class, Pupil> EXPECTED_PHYSICS_PRIMUS_MAP = new HashMap<>();

	@BeforeClass
	public static void fillSchool() {
		Random rand = new Random();
		for (int i = 0; i < CLASS_COUNT; i++) {
			Class c = new Class("Klasa " + (i + 1));
			float primusAvgMath = Float.MIN_VALUE;
			float primusAvgPhysics = Float.MIN_VALUE;
			Pupil mathPrimus = null;
			Pupil physicsPrimus = null;
			for (int j = 0; j < PUPILS_NAMES.length; j++) {
				Pupil pupil = new Pupil(PUPILS_NAMES[j]);
				float avgMath = rand.nextFloat() * 6;
				pupil.setAvgMath(avgMath);
				float avgPhysics = rand.nextFloat() * 6;
				pupil.setAvgPhysics(avgPhysics);
				c.getPupilList().add(pupil);
				if (primusAvgMath < avgMath) {
					primusAvgMath = avgMath;
					mathPrimus = pupil;
				} 
				if (primusAvgPhysics < avgPhysics) {
					primusAvgPhysics = avgPhysics;
					physicsPrimus = pupil;
				} 
			}
			
			EXPECTED_MATH_PRIMUS_MAP.put(c, mathPrimus);
			EXPECTED_PHYSICS_PRIMUS_MAP.put(c, physicsPrimus);
			
			SCHOOL.getClassList().add(c);
		}
	}
	
	@org.junit.Test
	public void testMath() {
		SchoolService schoolService = new SchoolService();
		schoolService.setSchool(SCHOOL);
		System.out.println("Math primus");
		for (Entry<Class, Pupil> entry : OnePrimusPerClassIntegrationTest.EXPECTED_MATH_PRIMUS_MAP.entrySet()) {
			System.out.println(entry.getKey().getName() + " " + entry.getValue());
		}
		System.out.println("Math primus from school");
		Map<Class, Pupil> mathPrimusMap = schoolService.getOnePrimusOfSubjectPerClass(Subject.MATH);
		for (Entry<Class, Pupil> entry : mathPrimusMap.entrySet()) {
			System.out.println(entry.getKey().getName() + " " + entry.getValue());
		}
		assertEquals(OnePrimusPerClassIntegrationTest.EXPECTED_MATH_PRIMUS_MAP, mathPrimusMap);
	}

	@org.junit.Test
	public void testPhysics() {
		SchoolService schoolService = new SchoolService();
		schoolService.setSchool(SCHOOL);
		System.out.println("Physics primus");
		for (Entry<Class, Pupil> entry : OnePrimusPerClassIntegrationTest.EXPECTED_PHYSICS_PRIMUS_MAP.entrySet()) {
			System.out.println(entry.getKey().getName() + " " + entry.getValue());
		}
		System.out.println("Physics primus from school");
		Map<Class, Pupil> physicsPrimusMap = schoolService.getOnePrimusOfSubjectPerClass(Subject.PHYSICS);
		for (Entry<Class, Pupil> entry : physicsPrimusMap.entrySet()) {
			System.out.println(entry.getKey().getName() + " " + entry.getValue());
		}
		assertEquals(OnePrimusPerClassIntegrationTest.EXPECTED_PHYSICS_PRIMUS_MAP, physicsPrimusMap);
	}
}
