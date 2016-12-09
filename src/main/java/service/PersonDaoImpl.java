package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.apache.log4j.LogManager;

import magazine.JDBCUtils;
import magazine.Person;

public class PersonDaoImpl implements PersonDao{
	static Logger log=Logger.getLogger("PersonDaoImpl");
	//public static Logger logger = LogManager.getFormatterLogger("PersonDaoImpl");

	public Connection getConn() {
		return conn;
	}


	private static final String ADD_PERSON = "insert into registration (`first`,`last`) values (?,?)";
	private static final String FIND_PERSON_BY_ID = "select * from registration where client_id=?";
	private static final String FIND_ALL_PERSON = "select * from registration";
	private static final String UPDATE_PERSON_BY_ID = "update registration set invoice=? where client_id=?";
	private static final String DELETE_PERSON_BY_ID = "delete from registration WHERE client_id=?";
	private Connection conn;


	public PersonDaoImpl() {
		conn = JDBCUtils.getConnection();
	}

	
	public List<Person> findAll() {
		System.out.println("open in findAll method!");
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Person> listOfPerson = new ArrayList<Person>();
		try {
			stmt = conn.prepareStatement(FIND_ALL_PERSON);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Person p = new Person();
				p.setId(rs.getInt("client_id"));
				p.setFirstname(rs.getString("first"));
				p.setLastname(rs.getString("last"));
				p.setInvoice(rs.getDouble("invoice"));
				listOfPerson.add(p);
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				System.out.println("closed in findAll method!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return listOfPerson;
	}
	public Person findById(int id) {

			System.out.println("open in findById method!");
			PreparedStatement stmt = null;
			ResultSet rs = null;
			Person p = null;
			try {
				stmt = conn.prepareStatement(FIND_PERSON_BY_ID);
				stmt.setInt(1, id);
				rs = stmt.executeQuery();

				p = new Person();
				while (rs.next()) {
					p.setId(rs.getInt("client_id"));
					p.setFirstname(rs.getString("first"));
					p.setLastname(rs.getString("last"));
					p.setInvoice(rs.getDouble("invoice"));
				}
				rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					stmt.close();
					System.out.println("closed in findById method!");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return p;
		}	

	public void updateById(Person p) {
		// TODO Auto-generated method stub
		
	}

	public void delete(int id) {
		System.out.println("open in delete method!");
		PreparedStatement stmt = null;
		try {
			stmt = conn.prepareStatement(DELETE_PERSON_BY_ID);
			stmt.setInt(1, id);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				System.out.println("closed in delete method!");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
		
	


	public void add(Person p) {

			System.out.println("open in add method!");

			PreparedStatement stmt = null;
			log.info(p.getFirstname()+" : "+p.getLastname());


			
			try {

				stmt = conn.prepareStatement(ADD_PERSON, PreparedStatement.RETURN_GENERATED_KEYS);
				stmt.setString(1, p.getFirstname());
				stmt.setString(2,p.getLastname());
				stmt.executeUpdate();
				ResultSet rs = stmt.getGeneratedKeys();
				if (rs.next()) {
					int generatedId = rs.getInt(1);
					System.out.println(generatedId);
					p.setId(generatedId);
				}

			} catch (SQLException e) {
				log.warning(Arrays.toString(e.getStackTrace()));
			} finally {
				try {
					stmt.close();
					System.out.println("closed in add method!");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		
	}

}
