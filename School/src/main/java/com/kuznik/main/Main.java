package com.kuznik.main;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.kuznik.model.Class;
import com.kuznik.model.Pupil;
import com.kuznik.model.School;
import com.kuznik.model.Subject;
import com.kuznik.service.SchoolService;

public class Main {

	private static String[] PUPILS_NAMES = {"Adam", "Jacek", "Piotr", "Rafał", "Marek"};
	private static SchoolService schoolService = new SchoolService();
	
	public static void main(String[] args) {
		
		Random rand = new Random();
		School school = new School();
		for (int i = 0; i < 5; i++) {
			Class c = new Class("Klasa " + (i + 1));
			for (int j = 0; j < PUPILS_NAMES.length; j++) {
				Pupil p = new Pupil(PUPILS_NAMES[j]);
				p.setAvgMath(rand.nextFloat()*6);
				p.setAvgPhysics(rand.nextFloat()*6);
				c.getPupilList().add(p);
			}
			school.getClassList().add(c);
		}
		
		System.out.println(school);
		schoolService.setSchool(school);
		
		System.out.println("Math primus");
		Map<Class, List<Pupil>> mathPrimus = schoolService.getPrimusOfSubject(Subject.MATH);
		for (Entry<Class, List<Pupil>> entry : mathPrimus.entrySet()) {
			System.out.println(entry.getKey().getName() + " " +entry.getValue());
		}
		System.out.println("Physics primus");
		Map<Class, List<Pupil>> physicsPrimus = schoolService.getPrimusOfSubject(Subject.PHYSICS);
		for (Entry<Class, List<Pupil>> entry : physicsPrimus.entrySet()) {
			System.out.println(entry.getKey().getName() + " " +entry.getValue());
		}
	}

}
