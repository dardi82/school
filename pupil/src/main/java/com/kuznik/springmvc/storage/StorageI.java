package com.kuznik.springmvc.storage;

import java.util.Collection;

public interface StorageI {
	
	public <T extends StorageObjectInterface> T getObject(T obj);
	public <T extends StorageObjectInterface> T addObject(T obj);
	public <T extends StorageObjectInterface> T updateObject(T obj) throws StorageException;
	public <T extends StorageObjectInterface> T deleteObject(T obj);
	public <T extends StorageObjectInterface> Collection<T> getObjectCollection(Class<T> classs);
	public <T extends StorageObjectInterface> void  deleteAllObject(Class<T> classs);
}
