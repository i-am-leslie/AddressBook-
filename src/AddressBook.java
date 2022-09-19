import java.util.ArrayList;
import java.util.*;
public class AddressBook {
    private ArrayList<BuddyInfo> buds;


    public AddressBook() {
       this.buds=new ArrayList<BuddyInfo>();
       System.out.println("Address book");
    }

    public void addBuddy(BuddyInfo friend){
        buds.add(friend);
    }
    public void removeBuddy(BuddyInfo friend) {
        buds.remove(friend);

    }
    public static void main(String[] args){
        BuddyInfo  baby= new BuddyInfo(675, "randa","1125 colonel by dr");
        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(baby);
        addressBook.removeBuddy(baby);
    }


}
