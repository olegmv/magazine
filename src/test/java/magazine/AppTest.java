package magazine;
import static org.mockito.Mockito.mock;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

import service.App;
import service.PersonDao;
import service.PersonDaoImpl;

public class AppTest {
	static App app;
	static Person p;
	static PersonDao person;
	private static final Logger LOGGER = Logger.getLogger(AppTest.class);
	@Rule
	public TestWatcher testWatcher = new TestWatcher() {
		
		

		@Override
		protected void failed(Throwable e, Description description) {
			LOGGER.error("failed " + description.getMethodName()+" "+e.getMessage());
		}

		@Override
		protected void succeeded(Description description) {
			// TODO Auto-generated method stub
			LOGGER.info("success " + description.getMethodName());
		}
		
	};

	@BeforeClass
	public static void setUp(){
		app=new App();
		person=new PersonDaoImpl();
		//person=mock(PersonDaoImpl.class);
		//p = new Person();
		p=mock(Person.class);
	}
	@Test
	public void accert(){
		Assert.assertNotNull(app);
		Assert.assertNotNull(p);
		Assert.assertNotNull(person);	
	}
	@Test
	public void checkConnection(){
		person.add(new Person("igor","vyhor"));
		p.setFirstname("igor");
		p.setLastname("vyhor");
		for(Person per:person.findAll())
			if(per.getFirstname().equals(p.getFirstname())){
				p.setId(per.getId());
				System.out.println("========"+p.getId()+"=========");
				p.setInvoice(per.getInvoice());
				System.out.println(p.toString());
				System.out.println(per.toString());
				Assert.assertEquals(p,per);

			}
	}
	
	@AfterClass
	public static void tearDown() {
		person.delete(p.getId());
		app = null;
		p=null;
		person=null;
		
	}
	

}
