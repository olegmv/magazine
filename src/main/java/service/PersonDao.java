package service;

import java.sql.Connection;
import java.util.List;
import magazine.*;

public interface PersonDao {
	void add(Person p);

	List<Person> findAll();

	Person findById(int id);

	void updateById(Person p);

	void delete(int id);
	public Connection getConn();
}
