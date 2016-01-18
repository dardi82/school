package com.kuznik.springmvc.storage;

public interface StorageObjectInterface extends Cloneable {
	public int getId();
	public void setId(int id);
	public Object clone();
}
