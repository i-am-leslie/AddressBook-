import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.*;
import java.util.ArrayList;

public class AddressBookModel extends DefaultHandler implements  Serializable {
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
    public String toXML(){
        String output = "<AddressBook>\n";
        for(BuddyInfo b:buds){
            output+= b.toXML();

        }
        output+="</AddressBook>\n";
        return output;
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
            File file = new File(fileName+".txt");

            SAXParserFactory spf = SAXParserFactory.newInstance() ;
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
                    if(tag.equals("Name"))
                        name = new String(ch, start, length);
                    else if(this.tag.equals("Number"))
                        number = Integer.parseInt(new String(ch, start, length));
                    else if(this.tag.equals("Address")) {
                        BuddyInfo buddyInfo = new BuddyInfo(number, name, new String(ch, start, length));
                        addressBookModel.addBuddy(buddyInfo);
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
