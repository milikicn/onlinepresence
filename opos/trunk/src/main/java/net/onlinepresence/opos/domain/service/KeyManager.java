package net.onlinepresence.opos.domain.service;

import java.util.List;

import net.onlinepresence.opos.domain.Key;

public interface KeyManager {

	void addKey(Key key);
	
	void updateKey(Key key);
	
	void removeKey(Key key);
	
	Key getKey(String email);
	
	void removeKey(String email);
	
	List<Key> getKeys();
}
