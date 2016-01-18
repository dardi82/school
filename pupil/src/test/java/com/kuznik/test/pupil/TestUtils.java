package com.kuznik.test.pupil;

import java.util.Arrays;
import java.util.Collection;

import org.apache.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.kuznik.springmvc.model.Pupil;

public class TestUtils {

	private static final String REST_SERVICE_URI = "http://localhost:8080/pupil/PupilRest/";
	static Logger log = Logger.getLogger(TestUtils.class.getName());
	
	public static String hello() {
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(REST_SERVICE_URI + "/hello", String.class);
	}

	public static Collection<Pupil> listAllPupils() {
		log.info("listAllPupils API----------");

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Pupil[]> responseEntity = restTemplate.getForEntity(REST_SERVICE_URI + "/Pupil", Pupil[].class);
		Pupil[] pupils = responseEntity.getBody();
		if (pupils != null && pupils.length > 0) {
			log.info("Pupil list: " + pupils);
		} else {
			log.info("No pupil exist----------");
		}
		return Arrays.asList(pupils);
	}

	public static Pupil getPupil(int id) {
		log.info("getPupil API----------");

		RestTemplate restTemplate = new RestTemplate();
		Pupil pupil = restTemplate.getForObject(REST_SERVICE_URI + "/Pupil/" + id, Pupil.class);
		log.info(pupil);
		return pupil;
	}

	public static Pupil createPupil(String firstName, String lastName) {
		log.info("create Pupil API----------");

		Pupil pupil = new Pupil.Builder(-1).firstName(firstName).lastName(lastName).build();

		RestTemplate restTemplate = new RestTemplate();
		Pupil pupilOut = restTemplate.postForObject(REST_SERVICE_URI + "/Pupil/", pupil, Pupil.class);
		if (pupilOut != null) {
			log.info("created: " + pupilOut);
		} else {
			log.info("Pupil wasn't create");
		}
		return pupilOut;
	}

	public static Pupil updatePupil(int id, String newFirstName, String newLastName) {
		log.info("update Pupil API----------");

		RestTemplate restTemplate = new RestTemplate();
		Pupil pupil = new Pupil.Builder(id).firstName(newFirstName).lastName(newLastName).build();

		restTemplate.put(REST_SERVICE_URI + "/Pupil/" + id, pupil);
		log.info(pupil);

		return pupil;
	}

	public static void deletePupil(int id) {
		log.info("delete Pupil API----------");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI + "/Pupil/" + id);
	}

	public static void deleteAllPupils() {
		log.info("delete all Pupils API----------");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.delete(REST_SERVICE_URI + "/Pupil/");
	}

//	private List<HttpMessageConverter<?>> getMessageConverters() {
//		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
//		converters.add(new MappingJackson2HttpMessageConverter());
//		return converters;
//	}
}
