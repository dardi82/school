package com.kuznik.springmvc.model;

public class NullPupil extends AbstractPupil {

	private static final String msg = "OBJECT IS NULL";
	
	@Override
	public boolean isNil() {
		return true;
	}

	@Override
	public String getFirstName() {
		return msg;
	}
	
	@Override
	public String getLastName() {
		return msg;
	}
}
