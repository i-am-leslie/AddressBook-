import java.util.ArrayList;
import java.util.*;
public class AddressBook {
    private ArrayList<BuddyInfo> buds;


    public AddressBook() {
       this.buds=new ArrayList<BuddyInfo>();

    }

    public void addBuddy(BuddyInfo friend){
        if(friend!=null){
            buds.add(friend);
        }
    }
    public BuddyInfo removeBuddy(int index) {
        if(index < buds.size() && index>=0){
            return buds.remove(index);
        }
        return null;

    }
    public static void main(String[] args){
        BuddyInfo  baby= new BuddyInfo(675, "randa","1125 colonel by dr");
        BuddyInfo  baby1= new BuddyInfo(675, "randa","1125 colonel by dr");

        AddressBook addressBook = new AddressBook();
        addressBook.addBuddy(baby);
        addressBook.addBuddy(baby1);
        addressBook.removeBuddy(0) ;
    }


}
