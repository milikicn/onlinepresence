package net.onlinepresence.opos.core.beans;


import static org.testng.AssertJUnit.assertNull;
import static org.testng.AssertJUnit.assertSame;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;



public class BaseBeanTest {
	
	  private BaseBean baseBean;

		protected BaseBean createUnderTest() {
			return new BaseBean();
		}
	    
	    @BeforeMethod
	    public void setUp() throws Exception {
	        baseBean = createUnderTest();
	    }
	    
	    @Test()
	    public void test_id() {
	        Long magicId = new Long(0);
	        assertNull("_id should be null", baseBean.get_id()); //$NON-NLS-1$
	        baseBean.set_id(magicId);
	        assertSame("Tested bean's _id should be: " + magicId + ".", //$NON-NLS-2$
	                magicId, baseBean.get_id());
	    }

}
