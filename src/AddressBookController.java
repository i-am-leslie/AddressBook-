import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddressBookController implements ActionListener {
    private AddressBookModel model;
    private AddressBookFrame frame;

    public AddressBookController(AddressBookModel model, AddressBookFrame frame) {
        this.model = model;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add Buddy")) {
            try {
                String name = JOptionPane.showInputDialog(frame, "Name:");
                String address = JOptionPane.showInputDialog(frame, "Address:");
                String stringPhoneNumber = JOptionPane.showInputDialog(frame, "Phone Number:");
                int phoneNumber = Integer.parseInt(stringPhoneNumber);

                BuddyInfo buddyInfo = new BuddyInfo(phoneNumber, name, address);
                model.addBuddy(buddyInfo);
            } catch (HeadlessException | NumberFormatException ex) {
                throw new RuntimeException(ex);
            }


        } else if (e.getActionCommand().equals("Remove")) {
            model.removeBuddy(frame.getList().getSelectedIndex());
            frame.getList().setModel(model.getListModel());
        } else if (e.getActionCommand().equals("View info")) {
            BuddyInfo buddyInfo = model.getBuds().get(frame.getList().getSelectedIndex());
            String message = buddyInfo.getName() + "\n" + buddyInfo.getAddress() + "\n" + buddyInfo.getPhoneNumber();
            JOptionPane.showMessageDialog(frame, message);
        } else if (e.getActionCommand().equals(("Export"))) {
            String s = JOptionPane.showInputDialog("File Name");
            model.save(s);

        } else if (e.getActionCommand().equals(("import"))) {
            String g = JOptionPane.showInputDialog("File Name");
            model.importAddressBook(g);
        }
    }
}
