public class BuddyInfo {
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
        System.out.print(s);
        return s;
    }
    public static BuddyInfo importBuddyInfo(String bud){
        String[] buds =bud.split("#");
        return new BuddyInfo(Integer.parseInt(buds[2]) ,buds[0],buds[1]);

    }
    @Override
    public boolean equals(Object obj){
        BuddyInfo buddyInfo=(BuddyInfo) obj;
        return buddyInfo.getName().equals(this.name) && buddyInfo.getAddress().equals(this.address) && buddyInfo.getPhoneNumber()==this.phoneNumber;
    }

    public static void main(String[] args) {
        BuddyInfo b=new BuddyInfo(111,"leslie","1125 colonel by dr");
        b.toString();
    }



}
