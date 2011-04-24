package net.onlinepresence.opos.domain.service.beans;

import java.util.List;

import net.onlinepresence.opos.domain.Key;
import net.onlinepresence.opos.domain.service.KeyManager;

public class KeyManagerBean 
	extends AbstractManagerBean<Key>
	implements KeyManager {

	public List<Key> getKeys() {
		getReader().setClazz(Key.class);
		return getReader().execute();
	}
	
	public void addKey(Key key) {
		getWriter().setToSave(key);
		getWriter().execute();
	}
	
	public void updateKey(Key key) {
		getUpdater().setToUpdate(key);
		getUpdater().execute();		
	}

	public void removeKey(Key key) {
		getDeleter().setToDelete(key);
		getDeleter().execute();
	}
	
	public void removeKey(String email) {
		Key k = getKey(email);
		getDeleter().setToDelete(k);
		getDeleter().execute();
	}

	public Key getKey(String email) {
		List<Key> keys = getKeys();
		
		for (Key key : keys) {
			if (key.getEmail().equals(email))
				return key;
		}
		
		return null;
	}

}
