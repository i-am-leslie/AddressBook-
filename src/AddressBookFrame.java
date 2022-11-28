import javax.swing.*;

public class AddressBookFrame extends JFrame {

    private AddressBookModel model;
    private JList<String> list;
    public enum commands{DELETE,ADD,REMOVE,VIEW};


    public AddressBookFrame() {
        super("Addressbook.exe");
        JMenuBar menuBar = new JMenuBar( );
        this.setJMenuBar(menuBar);

        model =new AddressBookModel();
        list = new JList<>(model.getListModel());
        this.add(list);
        AddressBookController control=new AddressBookController(model,this);

        JMenu menu=new JMenu("Menu");
        JMenuItem addBuddy=new JMenuItem("Add Buddy");
        JMenuItem remove=new JMenuItem("Remove");
        JMenuItem view=new JMenuItem("View info");

        JMenuItem export=new JMenuItem("Export");
        JMenuItem importFile=new JMenuItem("import");

        addBuddy.setActionCommand(addBuddy.getText());
        addBuddy.addActionListener(control);
        remove.setActionCommand(remove.getText());
        remove.addActionListener(control);

        view.setActionCommand(view.getText());
        view.addActionListener(control);
        export.setActionCommand(export.getText());
        export.addActionListener(control);
        importFile.setActionCommand(importFile.getText());
        importFile.addActionListener(control);


        menu.add(addBuddy);
        menu.add(remove);

        menu.add(view);
        menu.add(export);
        menu.add(importFile);

        menuBar.add(menu);


        this.setSize(400,400);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);


    }



    public static void main(String[] args) {
        new AddressBookFrame();
    }

    public JList<String> getList() {
        return list;
    }
}
