package net.onlinepresence.opos.domain.service;

import net.onlinepresence.opos.service.crud.impl.DeleteCommand;
import net.onlinepresence.opos.service.crud.impl.ExampleSearchCommand;
import net.onlinepresence.opos.service.crud.impl.ReadCommand;
import net.onlinepresence.opos.service.crud.impl.UpdateCommand;
import net.onlinepresence.opos.service.crud.impl.WriteCommand;

public interface AbstractManager<Type> {
	
	ReadCommand<Type> getReader();
	void setReader(ReadCommand<Type> reader);

	UpdateCommand<Type> getUpdater();
	void setUpdater(UpdateCommand<Type> updater);

	WriteCommand<Type> getWriter();
	void setWriter(WriteCommand<Type> writer);

	DeleteCommand<Type> getDelete();
	void setDelete(DeleteCommand<Type> delete);

	ExampleSearchCommand<Type> getSearcher();
	void setSearcher(ExampleSearchCommand<Type> searcher);
}
