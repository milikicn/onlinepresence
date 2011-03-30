package net.onlinepresence.opos.domain.service.beans;

import java.util.List;

import org.apache.tapestry5.ioc.annotations.Inject;

import net.onlinepresence.opos.core.spring.SpringBean;
import net.onlinepresence.opos.domain.Key;
import net.onlinepresence.opos.domain.service.Keys;
import net.onlinepresence.opos.service.crud.impl.DeleteCommand;
import net.onlinepresence.opos.service.crud.impl.ReadCommand;
import net.onlinepresence.opos.service.crud.impl.UpdateCommand;
import net.onlinepresence.opos.service.crud.impl.WriteCommand;

public class KeysBean implements Keys {

	@Inject
	@SpringBean("net.onlinepresence.opos.service.crud.impl.ReadCommand")
	private ReadCommand<Key> reader;
	
	@Inject
	@SpringBean("net.onlinepresence.opos.service.crud.impl.WriteCommand")
	private WriteCommand<Key> writer;
	
	@Inject
	@SpringBean("net.onlinepresence.opos.service.crud.impl.DeleteCommand")
	private DeleteCommand<Key> delete;
	
	@Inject
	@SpringBean("net.onlinepresence.opos.service.crud.impl.UpdateCommand")
	private UpdateCommand<Key> updater;
	
	
	public List<Key> getKeys() {
		reader.setClazz(Key.class);
		return reader.execute();
	}
	
	public void addKey(Key key) {
		writer.setToSave(key);
		writer.execute();
	}
	
	public void updateKey(Key key) {
		updater.setToUpdate(key);
		updater.execute();		
	}

	public void removeKey(Key key) {
		delete.setToDelete(key);
		delete.execute();
	}
	
	public void removeKey(String email) {
		Key k = getKey(email);
		delete.setToDelete(k);
		delete.execute();
	}
	

	public Key getKey(String email) {
		List<Key> keys = getKeys();
		
		for (Key key : keys) {
			if (key.getEmail().equals(email))
				return key;
		}
		
		return null;
	}

	public ReadCommand<Key> getReader() {
		return reader;
	}

	public void setReader(ReadCommand<Key> reader) {
		this.reader = reader;
	}

	public WriteCommand<Key> getWriter() {
		return writer;
	}

	public void setWriter(WriteCommand<Key> writer) {
		this.writer = writer;
	}

	public DeleteCommand<Key> getDelete() {
		return delete;
	}

	public void setDelete(DeleteCommand<Key> delete) {
		this.delete = delete;
	}

	public UpdateCommand<Key> getUpdater() {
		return updater;
	}

	public void setUpdater(UpdateCommand<Key> updater) {
		this.updater = updater;
	}



}
