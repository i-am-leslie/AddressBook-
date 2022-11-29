import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddressBookModelTest {
    AddressBookModel a;
    AddressBookModel b;
    @Before
    public void setUp(){
        a=new AddressBookModel();
        b=new AddressBookModel();
    }

    @After
    public void tearDown() {
        a=null;
        b=null;
    }

    @Test
    public void test(){
        a.addBuddy(new BuddyInfo(11,"leslie","1127 colonel"));
        a.addBuddy(new BuddyInfo(10,"sus","1129 colonel"));
        a.addBuddy(new BuddyInfo(91,"dan","1125 colonel"));
        assertTrue(a.save("firstattempt"));

        b.importAddressBook("firstattempt");

        for(int i=0;i<=2;i++){
            assertEquals(b.getBuds().get(i), a.getBuds().get(i));

        }

    }


    @Test
    public void serializationTest(){
        a.addBuddy(new BuddyInfo(11,"leslie","1127 colonel"));
        a.addBuddy(new BuddyInfo(10,"sus","1129 colonel"));
        a.addBuddy(new BuddyInfo(91,"dan","1125 colonel"));
        assertTrue(a.serilizationsave("firstattempt"));

        b.serilizationImport("firstattempt");

        for(int i=0;i<=2;i++){
            assertEquals(b.getBuds().get(i), a.getBuds().get(i));

        }

    }


}