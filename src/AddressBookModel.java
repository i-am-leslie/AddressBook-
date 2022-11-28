import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AddressBookModel extends DefaultListModel {
    private ArrayList<BuddyInfo> buds;
    private DefaultListModel<String> listModel;

    public AddressBookModel() {
        listModel=new DefaultListModel<>();
        this.buds=new ArrayList<BuddyInfo>();

    }

    public void addBuddy(BuddyInfo friend){
        if(friend!=null){
            buds.add(friend);
            listModel.addElement(friend.getName());
        }
    }
    public BuddyInfo removeBuddy(int index) {
        if(index < buds.size() && index>=0){
            listModel.remove(index);
            return buds.remove(index);

        }
        return null;

    }

    public ArrayList<BuddyInfo> getBuds() {
        return buds;
    }

    public DefaultListModel<String> getListModel() {
        return listModel;
    }

    public boolean save(String name) {
        File file=new File(name + ".txt");

        try {
            FileWriter writer=new FileWriter(file);
            for(BuddyInfo b:buds){
                writer.write(b.toString()+"\n");

            }
            writer.close();

        }catch(IOException e){
            System.out.println(e);
            return  false;
        }
        return true;

    }
    public boolean importAddressBook(String fileName) {
        try{
            BufferedReader scan =new BufferedReader(new FileReader(fileName+".txt"));
            String s=scan.readLine();
            while (s!=null){
                addBuddy(BuddyInfo.importBuddyInfo(s));
                s=scan.readLine();
            }
        }catch(IOException e){
            System.out.println(e);
            return false;

        }
        return true;


    }

    public static void main(String[] args){
        BuddyInfo  baby= new BuddyInfo(675, "randa","1125 colonel by dr");
        BuddyInfo  baby1= new BuddyInfo(675, "randa","1125 colonel by dr");

        AddressBookModel addressBookModel = new AddressBookModel();
        addressBookModel.addBuddy(baby);
        addressBookModel.addBuddy(baby1);
        addressBookModel.removeBuddy(0) ;
        System.out.println("order");
    }



}
