package avltree;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for {@link AVLNode}
 * 
 * @author holyshit
 *
 */
public class AVLTreeTests {
	@Test
        public void createAndSearchTest(){
            AvlTree t = new AvlTree();
            try{
                t.addValue(14);
                t.addValue(30);
                t.addValue(15);
                t.addValue(16);

                assertEquals(true, t.hasValue(30));
                assertEquals(true, t.hasValue(15));
                assertEquals(true, t.hasValue(16));
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }
        
        @Test
        public void delAndFind(){
            AvlTree t = new AvlTree();
            
            try{
                t.addValue(14);
                t.addValue(30);
                t.addValue(15);
                t.addValue(16);
                
                t.delValue(16);
                
                assertEquals(false, t.hasValue(16));
                assertEquals(true, t.hasValue(15));
                assertEquals(true, t.hasValue(30));
            }catch(Exception e){
                e.printStackTrace();
            }
        }
}
