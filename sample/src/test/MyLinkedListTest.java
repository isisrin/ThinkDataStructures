/**
 * 
 */
package test;

import java.util.ArrayList;

import org.junit.Before;
import structures.MyLinkedList;


/**
 * @author downey
 *
 */
public class MyLinkedListTest extends MyArrayListTest {

	/**
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		list = new ArrayList<Integer>();
		list.add(1);
		list.add(2);
		list.add(3);

		mylist = new MyLinkedList<Integer>();
		mylist.addAll(list);
	}
}
