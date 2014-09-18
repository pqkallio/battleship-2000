
package util;

import battleship2000.util.StringUtil;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StringUtilsTest {
    
    public StringUtilsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void canBeParsedAsAnIntegerMethodReturnsTrueIfGivenAStringParsableAsInteger() {
        assertTrue(StringUtil.canBeParsedAsAnInteger("42"));
    }
    
    @Test
    public void canBeParsedAsAnIntegerMethodReturnsFalseIfGivenAStringNotParsableAsInteger() {
        assertFalse(StringUtil.canBeParsedAsAnInteger("Itchy"));
    }
    
    @Test
    public void canBeParsedAsAnIntegerMethodReturnsTrueIfGivenAStringParsableAsIntegerTest2() {
        assertTrue(StringUtil.canBeParsedAsAnInteger("0"));
    }
    
    @Test
    public void canBeParsedAsAnIntegerMethodReturnsFalseIfGivenAStringNotParsableAsIntegerTest2() {
        assertFalse(StringUtil.canBeParsedAsAnInteger(""));
    }
    @Test
    public void canBeParsedAsAnIntegerMethodReturnsTrueIfGivenAStringParsableAsIntegerTest3() {
        assertTrue(StringUtil.canBeParsedAsAnInteger("-1"));
    }
    
    @Test
    public void canBeParsedAsAnIntegerMethodReturnsFalseIfGivenAStringNotParsableAsIntegerTest3() {
        assertFalse(StringUtil.canBeParsedAsAnInteger(" "));
    }
}