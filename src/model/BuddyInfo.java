package model;

import java.io.Serializable;

public class BuddyInfo implements Serializable {
    private long phoneNumber;
    private String name;
    private String address;

    public BuddyInfo(long phoneNumber, String name, String address) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.address = address;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }
    public  String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public  String toString(){
        return getName() + "#" + getAddress() + "#" + getPhoneNumber();
    }
    public static BuddyInfo importBuddyInfo(String buddy){// factory method to form an instance of a class
        String[] buddySplit =buddy.split("#");
        return new BuddyInfo(Integer.parseInt(buddySplit[2]) ,buddySplit[0],buddySplit[1]);
    }
    @Override
    public boolean equals(Object obj){
        BuddyInfo buddyInfo=(BuddyInfo) obj;
        return buddyInfo.getName().equals(this.name) && buddyInfo.getAddress().equals(this.address) && buddyInfo.getPhoneNumber()==this.phoneNumber;
    }
    public String toXML(){
        String Output="<model.BuddyInfo>\n";
        Output+="<Name>"+getName()+"</Name>\n";
        Output+="<Number>"+getPhoneNumber()+"</Number>\n";
        Output+="<Address>"+getAddress()+"</Address>\n";
        Output+="</model.BuddyInfo>\n";
        return Output;
    }

    public static void main(String[] args) {
        BuddyInfo b=new BuddyInfo(111,"leslie","1125 colonel by dr");
        System.out.println(b.toString());
    }




}
