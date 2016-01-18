//package com.kuznik.rest.pupil;
//
//import org.springframework.web.bind.annotation.PathVariable;  
//import org.springframework.web.bind.annotation.RequestMapping;  
//import org.springframework.web.bind.annotation.RequestMethod;  
//import org.springframework.web.bind.annotation.RestController;  
//  
//
//@RestController  
//@RequestMapping("/PupilRest")  
//public class PupilRest {
// 
//	@RequestMapping(value = "/{name}", method = RequestMethod.GET)  
//	public String hello(@PathVariable String name) {  
//		  String result="Hello "+name;    
//		  return result;  
//		 }  
//}

package com.kuznik.rest;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.kuznik.springmvc.model.AbstractPupil;
import com.kuznik.springmvc.model.Pupil;
import com.kuznik.springmvc.service.PupilServiceException;
import com.kuznik.springmvc.service.PupilServiceI;

@RestController
@RequestMapping("/PupilRest")
public class PupilRest {

	static Logger log = Logger.getLogger(PupilRest.class.getName());

	@Autowired
	PupilServiceI PupilService;

	@PostConstruct
	public void init() {
		log.debug("PupilRest INIT");
	}

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public String hello() {
		log.debug("Hello Pupil's World");
		return "helloWorld";
	}

	@RequestMapping(value = "/Pupil", method = RequestMethod.GET)
	public ResponseEntity<Collection<Pupil>> listAllPupils() {
		log.debug("List All Pupils");

		Collection<Pupil> pupils = PupilService.getPupilCollection();
		return new ResponseEntity<Collection<Pupil>>(pupils, HttpStatus.OK);
	}

	@RequestMapping(value = "/Pupil/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Pupil> getPupil(@PathVariable("id") int id) {
		log.debug("Fetching Pupil with id " + id);
		AbstractPupil pupil = PupilService.findById(id);
		if (pupil.isNil()) {
			log.debug("Pupil with id " + id + " not found");
			return new ResponseEntity<Pupil>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Pupil>((Pupil) pupil, HttpStatus.OK);
	}

	@RequestMapping(value = "/Pupil/", method = RequestMethod.POST)
	public ResponseEntity<Pupil> createPupil(@RequestBody Pupil pupil, UriComponentsBuilder ucBuilder) {
		log.debug("Creating Pupil " + pupil.getFirstName() + pupil.getLastName());

		if (PupilService.isPupilExist(pupil)) {
			log.debug("A Pupil " + pupil + " already exist");
			return new ResponseEntity<Pupil>(HttpStatus.CONFLICT);
		}

		Pupil pupilOut = PupilService.addPupil(pupil);

		return new ResponseEntity<Pupil>(pupilOut, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/Pupil/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Pupil> updatePupil(@PathVariable("id") int id, @RequestBody Pupil Pupil) {
		log.debug("Updating Pupil " + id);

		AbstractPupil currentPupil = PupilService.findById(id);

		if (currentPupil.isNil()) {
			log.debug("Pupil with id " + id + " not found");
			return new ResponseEntity<Pupil>(HttpStatus.NOT_FOUND);
		}

		currentPupil.setFirstName(Pupil.getFirstName());
		currentPupil.setLastName(Pupil.getLastName());

		try {
			PupilService.updatePupil((Pupil) currentPupil);
		} catch (PupilServiceException e) {
			return new ResponseEntity<Pupil>((Pupil) currentPupil, HttpStatus.NOT_ACCEPTABLE);
		}
		return new ResponseEntity<Pupil>((Pupil) currentPupil, HttpStatus.OK);
	}

	@RequestMapping(value = "/Pupil/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Pupil> deletePupil(@PathVariable("id") int id) {
		log.debug("Deleting Pupil with id " + id);

		AbstractPupil pupil = PupilService.findById(id);
		if (pupil.isNil()) {
			log.debug("Unable to delete. Pupil with id " + id + " not found");
			return new ResponseEntity<Pupil>(HttpStatus.NOT_FOUND);
		}

		PupilService.deletePupil((Pupil) pupil);
		return new ResponseEntity<Pupil>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/Pupil/", method = RequestMethod.DELETE)
	public ResponseEntity<Pupil> deleteAllPupils() {
		log.debug("Deleting All Pupils");

		PupilService.deleteAllPupils();
		return new ResponseEntity<Pupil>(HttpStatus.NO_CONTENT);
	}

}