package com.kuznik.springmvc.storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Storage implements StorageI {

	private final Map<Class<? extends StorageObjectInterface>,Map<Integer, StorageObjectInterface>> objectMap = new ConcurrentHashMap<>();
	private final AtomicInteger idSeq = new AtomicInteger(1);

	private static final Storage instance = new Storage();

	private Storage() {
	}

	public static Storage getInstance() {
		return instance;
	}

	@Override
	public <T extends StorageObjectInterface> T addObject(T obj) {
		int id = idSeq.getAndIncrement();
		T newObj = (T)obj.clone();
		obj.setId(id);
		newObj.setId(id);
		Map<Integer, StorageObjectInterface> tNewMap = new ConcurrentHashMap<>(); 
		Map<Integer, StorageObjectInterface> tMap = ((tMap = objectMap.putIfAbsent(obj.getClass(), tNewMap)) == null) ? tNewMap : tMap;
		
		tMap.put(id, newObj);
		return obj;
	}

	@Override
	public <T extends StorageObjectInterface> T updateObject(T obj) throws StorageException {
		Map<Integer, StorageObjectInterface> tMap = objectMap.get(obj.getClass());
		if(tMap == null || tMap.get(obj.getId()) == null){
			throw new StorageException("Update error, object not exist: " + obj);
		}
		T newObj = (T)obj.clone();
		tMap.put(newObj.getId(), newObj);
		return obj;
	}

	@Override
	public <T extends StorageObjectInterface> T deleteObject(T obj) {
		Map<Integer, StorageObjectInterface> tMap = objectMap.get(obj.getClass());
		if(tMap == null){
			return null;
		}
		return (T)tMap.remove(obj.getId());
	}

	@Override
	public <T extends StorageObjectInterface> Collection<T> getObjectCollection(Class<T> classs) {
		Map<Integer, StorageObjectInterface> tMap = objectMap.get(classs);
		if(tMap == null){
			return new ArrayList<T>();
		}
		return new ArrayList<T>((Collection<T>)tMap.values());
	}

	@Override
	public <T extends StorageObjectInterface> T getObject(T obj) {
		Map<Integer, StorageObjectInterface> tMap = objectMap.get(obj.getClass());
		if(tMap == null){
			return null;
		}
		StorageObjectInterface p1 = tMap.get(obj.getId());
		if (p1 == null) {
			return null;
		}
		return (T)p1.clone();
	}

	public <T extends StorageObjectInterface> void deleteAllObject(Class<T> classs) {
		objectMap.remove(classs);
	}

}
