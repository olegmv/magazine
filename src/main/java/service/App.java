package service;

import magazine.Person;

public class App {

	public static void main(String[] args) {
		PersonDao person=new PersonDaoImpl();
		person.add(new Person("oleg","vyhor"));
		person.add(new Person("oleg","vyhor"));
		//person.add(new Person("igor","vyhor"));



	}

}
