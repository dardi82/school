package com.kuznik.springmvc.service;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.kuznik.springmvc.model.AbstractPupil;
import com.kuznik.springmvc.model.Pupil;
import com.kuznik.springmvc.model.PupilFactory;
import com.kuznik.springmvc.storage.Storage;
import com.kuznik.springmvc.storage.StorageException;
import com.kuznik.springmvc.storage.StorageI;

@Service
public class PupilService implements PupilServiceI {
	
	static Logger log = Logger.getLogger(PupilService.class.getName());
	
	@PostConstruct
	public void init() {
		log.debug("PupilService INIT");
	}

	private StorageI storage = Storage.getInstance();

	@Override
	public Pupil addPupil(Pupil pupil) {
		return storage.addObject(pupil);
	}

	@Override
	public void deletePupil(Pupil pupil) {
		storage.deleteObject(pupil);
	}

	@Override
	public Collection<Pupil> getPupilCollection() {
		return storage.getObjectCollection(Pupil.class);
	}

	@Override
	public AbstractPupil findById(int id) {
		Pupil p = new Pupil.Builder(id).build();
		p.setId(id);
		Pupil pupilOut = storage.getObject(p);
		return PupilFactory.getPupil(pupilOut);
	}

	@Override
	public boolean isPupilExist(Pupil pupil) {
		return storage.getObjectCollection(Pupil.class).contains(pupil);
	}

	@Override
	public void updatePupil(Pupil pupil) throws PupilServiceException {
		try {
			storage.updateObject(pupil);
		} catch (StorageException e) {
			throw new PupilServiceException("Update error", e);
		}
	}

	@Override
	public void deleteAllPupils() {
		storage.deleteAllObject(Pupil.class);
	}

}
