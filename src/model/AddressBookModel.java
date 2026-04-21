package model;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AddressBookModel extends DefaultHandler implements  Serializable {
    private final List<BuddyInfo> buddy;
    public final DefaultListModel<String> buddyUiList = new DefaultListModel<>();
    private boolean serializeState = false;
    public static final String  FILEPATH= "/Users/ejehleslie/Desktop/AddressBook-/AddressBook.txt";
    private static final String FILENAME = "AddressBook.txt";

    public AddressBookModel() {
        this.buddy =new ArrayList<>();
    }

    public boolean isSerializeState() {
        return serializeState;
    }

    public void changeSerializeState() {
        this.serializeState = !this.serializeState;
    }

    public void addBuddy(BuddyInfo friend){
        if(friend!=null){
            buddy.add(friend);
            buddyUiList.addElement(friend.getName());
        }
    }
    public void removeBuddy(int index) {
        if(index >= 0 && index < buddy.size()){
            buddyUiList.remove(index);
            buddy.remove(index);
        }
    }

    public List<BuddyInfo> getBuddy() {
        return buddy;
    }

    public DefaultListModel<String> getBuddyUiList() {
        return buddyUiList;
    }

    public boolean save() {
        File file=new File(FILEPATH);

        try {
            FileWriter writer=new FileWriter(file,true);
            for(BuddyInfo b: buddy){
                writer.write(b.toString()+"\n");
            }
            writer.close();

        }catch(IOException e){
            System.out.println(e);
            return  false;
        }
        return true;

    }
    public void importAddressBook() {
        try{
            BufferedReader scan =new BufferedReader(new FileReader(FILENAME));
            String s=scan.readLine();
            while (s!=null){
                addBuddy(BuddyInfo.importBuddyInfo(s));
                s = scan.readLine();
            }
        }catch(IOException e){
            System.out.println(e);
        }
    }
    public String toXML(){
        String output = "<AddressBook>\n";
        for(BuddyInfo b: buddy){
            output+= b.toXML();

        }
        output+="</AddressBook>\n";
        return output;
    }

    public boolean serilizationSave(){
        try{
            ObjectOutputStream object=new ObjectOutputStream(new FileOutputStream(FILENAME));
            for(BuddyInfo b: buddy){
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
            ObjectInputStream object=new ObjectInputStream(new FileInputStream(FILENAME+ ".dat"));
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

    public void exportToXmlFile(String fileName){
        File file=new File(fileName+".txt");
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(toXML());
            writer.close();
        }
        catch(IOException e){
        }




    }
    public static AddressBookModel importFromXmlFile(String fileName) {
        try {
            AddressBookModel addressBookModel = new AddressBookModel();
            File file = new File(fileName);

            SAXParserFactory spf = SAXParserFactory.newInstance();
            SAXParser s = spf.newSAXParser();
            DefaultHandler dh = new DefaultHandler () {
                String tag = "";
                String name = "";
                int number;

                public void startElement (String u, String In, String qName, Attributes a) {
                    tag = qName;
                }
                public void endElement (String uri, String localName, String qName) {
                    tag = "";
                }
                public void characters (char [] ch,int start, int length) {

                    switch (tag){
                        case "Address":
                            BuddyInfo buddyInfo = new BuddyInfo(number, name, new String(ch, start, length));
                            addressBookModel.addBuddy(buddyInfo);
                            break;
                        case "Number":
                            number = Integer.parseInt(new String(ch, start, length));
                            break;
                        case "Name":
                            name = new String(ch, start, length);
                            break;
                        default:
                            System.out.println(tag);
                    }
                }

            };

            s.parse(file, dh);
            return addressBookModel;
        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args){
        BuddyInfo  baby= new BuddyInfo(675, "randa","1125 colonel by dr");
        BuddyInfo  baby1= new BuddyInfo(675, "randa","1125 colonel by dr");

        AddressBookModel addressBookModel = new AddressBookModel();
        addressBookModel.addBuddy(baby);
        addressBookModel.addBuddy(baby1);
        addressBookModel.toXML();
        System.out.println(addressBookModel.toXML());
        System.out.println("\n==========================\n");
        addressBookModel.exportToXmlFile("xml");
        addressBookModel.importFromXmlFile("xml");
    }
}
