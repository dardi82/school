package com.kuznik.springmvc.service;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.kuznik.springmvc.model.AbstractPupil;
import com.kuznik.springmvc.model.Pupil;

@Component
public interface PupilServiceI {
	public Pupil addPupil(Pupil pupil);
	public void deletePupil(Pupil pupil);
	public Collection<Pupil> getPupilCollection();
	public AbstractPupil findById(int id);
	public boolean isPupilExist(Pupil pupil);
	public void updatePupil(Pupil pupil) throws PupilServiceException;
	public void deleteAllPupils();
}
