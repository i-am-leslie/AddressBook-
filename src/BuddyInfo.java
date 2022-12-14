import java.io.Serializable;

public class BuddyInfo implements Serializable {
    private int phoneNumber;
    private String name;
    private String address;

    public BuddyInfo(int phoneNumber, String name, String address) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.address = address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }
    public  String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public  String toString(){
//        StringBuilder str=new StringBuilder("");
//        str.append(getName());
//        str.append(getAddress());
//        str.append(getPhoneNumber());
//        System.out.print(str.toString().);
//        return str.toString();
        String s=getName()+"#"+getAddress()+"#"+getPhoneNumber();
        return s;
    }
    public static BuddyInfo importBuddyInfo(String bud){// factory method to form an instance of a class
        String[] buds =bud.split("#");
        return new BuddyInfo(Integer.parseInt(buds[2]) ,buds[0],buds[1]);

    }
    @Override
    public boolean equals(Object obj){
        BuddyInfo buddyInfo=(BuddyInfo) obj;
        return buddyInfo.getName().equals(this.name) && buddyInfo.getAddress().equals(this.address) && buddyInfo.getPhoneNumber()==this.phoneNumber;
    }
    public String toXML(){
        String Output="<BuddyInfo>\n";
        Output+="<Name>"+getName()+"</Name>\n";
        Output+="<Number>"+getPhoneNumber()+"</Number>\n";
        Output+="<Address>"+getAddress()+"</Address>\n";
        Output+="</BuddyInfo>\n";
        return Output;
    }

    public static void main(String[] args) {
        BuddyInfo b=new BuddyInfo(111,"leslie","1125 colonel by dr");
        b.toString();
    }




}
