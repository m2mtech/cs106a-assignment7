/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program 
implements FacePamphletConstants {

	/**
	 * private instance variables
	 */
	private JTextField nameField;
	private JButton addButton;
	private JButton deleteButton;
	private JButton lookupButton;

	private JTextField statusField;
	private JButton statusButton;
	private JTextField pictureField;
	private JButton pictureButton;
	private JTextField friendField;
	private JButton friendButton;

	private FacePamphletDatabase data;
	private FacePamphletProfile profile;
	private FacePamphletCanvas canvas;

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
		data = new FacePamphletDatabase();
		profile = null;

		canvas = new FacePamphletCanvas();
		add(canvas);

		add(new JLabel("Name:"), NORTH);
		nameField = new JTextField(TEXT_FIELD_SIZE);
		add(nameField, NORTH);
		addButton = new JButton("Add");
		add(addButton, NORTH);
		deleteButton = new JButton("Delete");
		add(deleteButton, NORTH);
		lookupButton = new JButton("Lookup");
		add(lookupButton, NORTH);

		statusField = new JTextField(TEXT_FIELD_SIZE);
		statusField.addActionListener(this);
		add(statusField, WEST);
		statusButton = new JButton("Change Status");
		add(statusButton, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);

		pictureField = new JTextField(TEXT_FIELD_SIZE);
		pictureField.addActionListener(this);
		add(pictureField, WEST);
		pictureButton = new JButton("Change Picture");
		add(pictureButton, WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);

		friendField = new JTextField(TEXT_FIELD_SIZE);
		friendField.addActionListener(this);
		add(friendField, WEST);
		friendButton = new JButton("Add Friend");
		add(friendButton, WEST);

		addActionListeners();
	}

	/**
	 * This class is responsible for detecting when the buttons are
	 * clicked or interactors are used, so you will have to add code
	 * to respond to these actions.
	 */
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source == addButton) {
			if (!emptyTextField(nameField)) {
				/*println("Add: " + nameField.getText());
    			FacePamphletProfile testDummy = new FacePamphletProfile(nameField.getText());
    			println(testDummy);
    			testDummy.setStatus("coding");
    			println(testDummy);
    			testDummy.addFriend("Don");
    			println(testDummy);
    			testDummy.addFriend("Chelsea");
    			println(testDummy);
    			testDummy.addFriend("Bob");
    			println(testDummy);
    			testDummy.addFriend("Don");
    			println(testDummy);
    			testDummy.addFriend("Mehran");
    			println(testDummy);
    			testDummy.removeFriend("Mehran");
    			println(testDummy);*/

				String name = nameField.getText();
				if (data.containsProfile(name)) {
					profile = data.getProfile(name);
					//println("Add: profile for " + name + " already exists: " + profile);
					canvas.displayProfile(profile);
					canvas.showMessage("A profile with the name " + name + " already exists");
				} else {
					profile = new FacePamphletProfile(name);
					data.addProfile(profile);
					//println("Add: new profile: " + profile);
					canvas.displayProfile(profile);
					canvas.showMessage("New profile created");
				}				
				//println("--> Current profile: " + profile);
			}    		
		} else if (source == deleteButton) {
			if (!emptyTextField(nameField)) {
				//println("Delete: " + nameField.getText());

				String name = nameField.getText();
				if (data.containsProfile(name)) {
					data.deleteProfile(name);
					profile = null;
					canvas.displayProfile(profile);
					canvas.showMessage("Profile of " + name + " deleted");
					//println("Delete: profile of " + name + " deleted");
				} else {
					profile = null;
					canvas.displayProfile(profile);
					canvas.showMessage("A profile with the name " + name + " does not exist");
					//println("Delete: profile with name " + name + " does not exist");
				}				
				//println("--> No current profile");
			}    		    		
		} else if (source == lookupButton) {
			if (!emptyTextField(nameField)) {
				//println("Lookup: " + nameField.getText());

				String name = nameField.getText();
				if (data.containsProfile(name)) {
					profile = data.getProfile(name);
					//println("Lookup: " + profile);
					//println("--> Current profile: " + profile);
					canvas.displayProfile(profile);
					canvas.showMessage("Displaying " + name);
				} else {
					//println("Lookup: profile with name " + name + " does not exist");
					profile = null;
					canvas.displayProfile(profile);
					canvas.showMessage("A profile with the name " + name + " does not exist");
					//println("--> No current profile");
				}
			}    		    		
		} else if ((source == statusField) || (source == statusButton)) {
			if (!emptyTextField(statusField)) {    			
				if (profile != null) {
					String status = statusField.getText();
					profile.setStatus(status);
					//println("Status updated to " + status);
					//println("--> Current profile: " + profile);
					canvas.displayProfile(profile);
					canvas.showMessage("Status updated to " + status);
				} else {
					//println("Please select profile to change status");
					//println("--> No current profile");
					canvas.displayProfile(profile);
					canvas.showMessage("Please select a profile to change status");
				}    			
			}    		
		} else if ((source == pictureField) || (source == pictureButton)) {
			if (!emptyTextField(pictureField)) {
				if (profile != null) {
					String filename = pictureField.getText();    			
					GImage image = null;
					try {
						image = new GImage(filename);
					} catch (ErrorException ex) {
						// Code that is executed if the filename cannot be opened. 
					}
					if (image != null) {
						profile.setImage(image);
						//println("Picture updated to " + filename);
						canvas.displayProfile(profile);
						canvas.showMessage("Picture updated");
					} else {
						//println("Please use a valid picture");
						canvas.displayProfile(profile);
						canvas.showMessage("Unable to open image file: " + filename);
					}
					//println("--> Current profile: " + profile);
				} else {
					//println("Please select profile to change picture");
					//println("--> No current profile");
					canvas.displayProfile(profile);
					canvas.showMessage("Please select a profile to change picture");
				}    			
			}    		
		} else if ((source == friendField) || (source == friendButton)) {
			if (!emptyTextField(friendField)) {
				if (profile != null) {
					String friend = friendField.getText();
					if (data.containsProfile(friend)) {
						if (profile.addFriend(friend)) {
							data.getProfile(friend).addFriend(profile.getName());
							//println(friend + " added as friend");
							canvas.displayProfile(profile);
							canvas.showMessage(friend + " added as friend");
						} else {
							//println("Friend with name " + friend + " already exists");
							canvas.displayProfile(profile);
							canvas.showMessage(profile.getName() + " already has " + friend + " as a friend.");
						}
					} else {
						//println("profile with name " + friend + " does not exist");
						canvas.displayProfile(profile);
						canvas.showMessage(friend + " does not exist");
					}
					//println("--> Current profile: " + profile);
				} else {
					//println("Please select profile to add friend");
					//println("--> No current profile");					
					canvas.displayProfile(profile);
					canvas.showMessage("Please select a profile to add friend");
				}
			}    		    		
		}
	}

	private boolean emptyTextField(JTextField field) {
		return field.getText().equals("");
	}

}
