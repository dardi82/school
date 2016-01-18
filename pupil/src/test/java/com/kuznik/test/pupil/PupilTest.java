package com.kuznik.test.pupil;

import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.web.client.HttpClientErrorException;

import com.kuznik.springmvc.model.Pupil;

public class PupilTest {
	
	static Logger log = Logger.getLogger(PupilTest.class.getName());
	
	@Before
	public void set() {
		TestUtils.deleteAllPupils();
	}

	@Test
	public void hello() {
		log.info("Testing hello-----------");
		assertEquals("helloWorld", TestUtils.hello());
	}

	@Test
	public void emptyPupilsList() {
		log.info("Testing emptyPupilsList-----------");
		assertEquals(0, TestUtils.listAllPupils().size());
	}

	@Test
	public void addOnePupil() {
		log.info("Testing addOnePupil-----------");
		Pupil pupil = TestUtils.createPupil("Mieszko", "Pierwszy");
		Collection<Pupil> pupils = TestUtils.listAllPupils();
		assertEquals(1, pupils.size());
		assertEquals(true, pupils.contains(pupil));
	}

	@Test(expected = HttpClientErrorException.class)
	public void addSamePupil() {
		log.info("Testing addSamePupil-----------");
		TestUtils.createPupil("Mieszko", "Pierwszy");
		TestUtils.createPupil("Mieszko", "Pierwszy");
	}

	@Test
	public void addOtherPupil() {
		log.info("Testing addSamePupil-----------");
		Pupil pupil1 = TestUtils.createPupil("Mieszko", "Pierwszy");
		Pupil pupil2 = TestUtils.createPupil("Bolesław", "Drugi");
		Collection<Pupil> pupils = TestUtils.listAllPupils();
		assertEquals(2, pupils.size());
		assertEquals(true, pupils.contains(pupil1));
		assertEquals(true, pupils.contains(pupil2));
	}

	@Test
	public void updatePupil() {
		log.info("Testing updatePupil-----------");
		Pupil pupil = TestUtils.createPupil("Mieszko", "Pierwszy");
		int id = pupil.getId();
		String newFirstName = "Bolesław";
		String newLastName = "Drugi";
		TestUtils.updatePupil(id, newFirstName, newLastName);
		Pupil updatedPupil = TestUtils.getPupil(id);

		assertEquals(id, updatedPupil.getId());
		assertEquals(newFirstName, updatedPupil.getFirstName());
		assertEquals(newLastName, updatedPupil.getLastName());
	}

	@Test
	public void addAndGetPupil() {
		log.info("Testing addAndGetPupil-----------");
		Pupil pupil1 = TestUtils.createPupil("Mieszko", "Pierwszy");
		Pupil pupil2 = TestUtils.createPupil("Bolesław", "Drugi");
		Pupil pupil3 = TestUtils.createPupil("Jan", "Trzeci");
		assertEquals(3, TestUtils.listAllPupils().size());
		assertEquals(pupil1, TestUtils.getPupil(pupil1.getId()));
		assertEquals(pupil2, TestUtils.getPupil(pupil2.getId()));
		assertEquals(pupil3, TestUtils.getPupil(pupil3.getId()));
	}
	
	@Test(expected = HttpClientErrorException.class)
	public void updateNotExistPupil() {
		log.info("Testing updateNotExistPupil-----------");
		Pupil pupil = TestUtils.createPupil("Mieszko", "Pierwszy");
		int id = pupil.getId() + 1; //id not exist
		String newFirstName = "Bolesław";
		String newLastName = "Drugi";
		TestUtils.updatePupil(id, newFirstName, newLastName);
	}
	
	@Test(expected = HttpClientErrorException.class)
	public void deleteNotExistPupil() {
		log.info("Testing deleteNotExistPupil-----------");
		Pupil pupil = TestUtils.createPupil("Mieszko", "Pierwszy");
		int id = pupil.getId() + 1; //id not exist
		TestUtils.deletePupil(id);
	}
	
	@Test
	public void deleteExistPupil() {
		log.info("Testing deleteExistPupil-----------");
		Pupil pupil = TestUtils.createPupil("Mieszko", "Pierwszy");
		assertEquals(1, TestUtils.listAllPupils().size());
		TestUtils.deletePupil(pupil.getId());
		assertEquals(0, TestUtils.listAllPupils().size());
	}
}
