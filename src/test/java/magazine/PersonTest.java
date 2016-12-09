package magazine;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class PersonTest {
	private static Person p;
	@BeforeClass
	public static void setUp(){
		p=new Person("igor","vyhor");
	}
	@Test
	public void accert(){
		Assert.assertNotNull(p);
	}
	@AfterClass
	public static void tearDown() {
		p = null;
	}

	
	
}
