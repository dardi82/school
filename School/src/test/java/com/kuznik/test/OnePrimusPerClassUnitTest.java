package com.kuznik.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import com.kuznik.model.Class;
import com.kuznik.model.Pupil;
import com.kuznik.model.School;
import com.kuznik.model.Subject;

public class OnePrimusPerClassUnitTest {

	@org.junit.Test
	public void pupilTest() {
		float avgMath = 5f;
		float avgPhysics = 4f;
		Pupil pupil = new Pupil("Michał", avgMath, avgPhysics);
		assertEquals(avgMath, pupil.getAvgSubject(Subject.MATH), 0);
		assertEquals(avgPhysics, pupil.getAvgSubject(Subject.PHYSICS), 0);
	}

	@org.junit.Test
	public void classTest() {
		Class clazz = new Class("Klasa 1");
		float avgMath = 5f;
		float avgPhysics = 4f;
		Pupil michalPupil = new Pupil("Michał", avgMath, avgPhysics);
		clazz.getPupilList().add(michalPupil);
		Pupil karolPupil = new Pupil("Karol", avgMath + 1, avgPhysics - 1);
		clazz.getPupilList().add(karolPupil);
		Pupil tomaszPupil = new Pupil("Tomasz", avgMath + 2, avgPhysics - 2);
		clazz.getPupilList().add(tomaszPupil);

		assertEquals(tomaszPupil, clazz.getPrimusOfSubject(Subject.MATH));
		assertEquals(michalPupil, clazz.getPrimusOfSubject(Subject.PHYSICS));
	}

	@org.junit.Test
	public void schoolTest() {

		Map<Class, Pupil> expectedMathPrimusMap = new HashMap<>();
		Map<Class, Pupil> expectedPhysicsPrimusMap = new HashMap<>();

		School school = new School();
		Class clazz = new Class("Klasa 1");
		float avgMath = 5f;
		float avgPhysics = 4f;
		Pupil michalPupil = new Pupil("Michał", avgMath, avgPhysics);
		clazz.getPupilList().add(michalPupil);
		Pupil karolPupil = new Pupil("Karol", avgMath + 1, avgPhysics - 1);
		clazz.getPupilList().add(karolPupil);
		Pupil tomaszPupil = new Pupil("Tomasz", avgMath + 2, avgPhysics - 2);
		clazz.getPupilList().add(tomaszPupil);
		school.getClassList().add(clazz);

		expectedMathPrimusMap.put(clazz, tomaszPupil);
		expectedPhysicsPrimusMap.put(clazz, michalPupil);

		clazz = new Class("Klasa 2");
		Pupil adamPupil = new Pupil("Adam", avgMath, avgPhysics);
		clazz.getPupilList().add(adamPupil);
		Pupil piotrPupil = new Pupil("Piotr", avgMath - 1, avgPhysics + 1);
		clazz.getPupilList().add(piotrPupil);
		Pupil lukaszPupil = new Pupil("Łukasz", avgMath - 2, avgPhysics + 2);
		clazz.getPupilList().add(lukaszPupil);
		school.getClassList().add(clazz);

		expectedMathPrimusMap.put(clazz, adamPupil);
		expectedPhysicsPrimusMap.put(clazz, lukaszPupil);

		assertEquals(expectedMathPrimusMap, school.getPrimusOfSubject(Subject.MATH));
		assertEquals(expectedPhysicsPrimusMap, school.getPrimusOfSubject(Subject.PHYSICS));
	}
}
