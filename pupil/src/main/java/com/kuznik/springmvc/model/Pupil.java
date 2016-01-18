package com.kuznik.springmvc.model;

public class Pupil extends AbstractPupil{

	private Pupil() {
	};

	private Pupil(Builder builder){
		if(builder == null){
			return;
		}
		setId(builder.id);
		setFirstName(builder.firstName);
		setLastName(builder.lastName);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null){
			return false;
		}
		if (this == obj)
			return true;
		if (!(this.getClass().equals(obj.getClass())))
			return false;
		Pupil pupil = (Pupil) obj;
		return (null == getFirstName() ? null == pupil.getFirstName() : getFirstName().equals(pupil.getFirstName()))
				&& (null == getLastName() ? null == pupil.getLastName() : getLastName().equals(pupil.getLastName()));
	}
	
	@Override
	public int hashCode() {
		return (null == getFirstName() ? 1 : getFirstName().hashCode())
				* (null == getLastName() ? 1 : getLastName().hashCode());
	}
	
	@Override
	public String toString() {
		return "[id : " + id + ", firstName : " + firstName + ", lastName : " + lastName +"]";
	}
	
	@Override
	public boolean isNil() {
		return false;
	}
	
	public static class Builder {

		private int id;
		private String firstName;
		private String lastName;
		
		public Builder(int id){
			this.id = id;
		}
		
		public Builder firstName(String firstName){
			this.firstName = firstName;
			return this;
		}
		
		public Builder lastName(String lastName){
			this.lastName = lastName;
			return this;
		}
		
		public Pupil build(){
			return new Pupil(this);
		}
	}
}
