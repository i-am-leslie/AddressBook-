package controller;

import model.AddressBookModel;
import model.BuddyInfo;

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
        switch (e.getActionCommand()) {
            case "Add Buddy" -> {
                try {
                    String name = JOptionPane.showInputDialog(frame, "Name:");
                    String address = JOptionPane.showInputDialog(frame, "Address:");
                    String stringPhoneNumber = JOptionPane.showInputDialog(frame, "Phone Number:");
                    long phoneNumber = Long.parseLong(stringPhoneNumber);

                    BuddyInfo buddyInfo = new BuddyInfo(phoneNumber, name, address);
                    model.addBuddy(buddyInfo);
                } catch (HeadlessException | NumberFormatException ex) {
                    throw new RuntimeException(ex);
                }
            }
            case "Remove" -> {
                model.removeBuddy(frame.getList().getSelectedIndex());
                frame.getList().setModel(model.getBuddyUiList());
            }
            case "View info" -> {
                // We can get the index pressed
                // get the actual last name from the default list
                // Dispplay it

                BuddyInfo buddyInfo = model.getBuddy().get(frame.getList().getSelectedIndex());
                String message = buddyInfo.getName() + "\n" + buddyInfo.getAddress() + "\n" + buddyInfo.getPhoneNumber();
                JOptionPane.showMessageDialog(frame, message);
            }
            case ("Export") -> {
                String s = JOptionPane.showInputDialog("File Name");
                model.save();
            }
            case ("import") -> {
                String g = JOptionPane.showInputDialog("File Name");
                model.importAddressBook();
            }
            default -> model.changeSerializeState();
        }
    }
}
