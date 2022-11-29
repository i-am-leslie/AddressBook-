import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class AddressBookModel extends DefaultListModel implements  Serializable {
    private ArrayList<BuddyInfo> buds;
    private DefaultListModel<String> listModel;
    private boolean serializeState;

    public AddressBookModel() {
        listModel=new DefaultListModel<>();
        this.buds=new ArrayList<BuddyInfo>();
        this.serializeState=false;

    }

    public boolean isSerializeState() {
        return serializeState;
    }

    public void changeSerializeState() {
        this.serializeState = !this.serializeState;
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
    public boolean serilizationsave(String fileName){
        try{
            ObjectOutputStream object=new ObjectOutputStream(new FileOutputStream(fileName+".txt"));
            for(BuddyInfo b: buds){
                object.writeObject(b);

            }
            object.close();
        }catch(IOException e){
            return false;
        }
       return true;

    }
    public boolean serilizationImport(String s){
        try{
            ObjectInputStream object=new ObjectInputStream(new FileInputStream(s+".txt"));
            while (true){
                try {
                    addBuddy((BuddyInfo) object.readObject());
                } catch (IOException e) {
                    break;
                } catch (ClassNotFoundException e) {
                    return false;
                }

            }
            object.close();
        }catch(IOException e){
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
