package net.onlinepresence.opos.domain.service.beans;

import net.onlinepresence.opos.core.spring.SpringBean;
import net.onlinepresence.opos.service.crud.impl.DeleteCommand;
import net.onlinepresence.opos.service.crud.impl.ExampleSearchCommand;
import net.onlinepresence.opos.service.crud.impl.ReadCommand;
import net.onlinepresence.opos.service.crud.impl.UpdateCommand;
import net.onlinepresence.opos.service.crud.impl.WriteCommand;

import org.apache.tapestry5.ioc.annotations.Inject;

public abstract class AbstractManagerBean<Type> {
	
	@Inject
	@SpringBean("net.onlinepresence.opos.service.crud.impl.ReadCommand")
	private ReadCommand<Type> reader;
	
	@Inject
	@SpringBean("net.onlinepresence.opos.service.crud.impl.UpdateCommand")
	private UpdateCommand<Type> updater;
	
	@Inject
	@SpringBean("net.onlinepresence.opos.service.crud.impl.WriteCommand")
	private WriteCommand<Type> writer;
	
	@Inject
	@SpringBean("net.onlinepresence.opos.service.crud.impl.DeleteCommand")
	private DeleteCommand<Type> deleter;
	
	@Inject
	@SpringBean("net.onlinepresence.opos.service.crud.impl.ExampleSearchCommand")
	private ExampleSearchCommand<Type> searcher;
		
	public AbstractManagerBean() { }
		
	public ReadCommand<Type> getReader() {
		return reader;
	}

	public void setReader(ReadCommand<Type> reader) {
		this.reader = reader;
	}

	public UpdateCommand<Type> getUpdater() {
		return updater;
	}

	public void setUpdater(UpdateCommand<Type> updater) {
		this.updater = updater;
	}

	public WriteCommand<Type> getWriter() {
		return writer;
	}

	public void setWriter(WriteCommand<Type> writer) {
		this.writer = writer;
	}

	public DeleteCommand<Type> getDeleter() {
		return deleter;
	}

	public void setDeleter(DeleteCommand<Type> delete) {
		this.deleter = delete;
	}

	public ExampleSearchCommand<Type> getSearcher() {
		return searcher;
	}

	public void setSearcher(ExampleSearchCommand<Type> searcher) {
		this.searcher = searcher;
	}
}
