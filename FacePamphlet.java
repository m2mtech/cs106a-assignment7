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

public class FacePamphlet extends ConsoleProgram 
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

	
	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
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
    			println("Add: " + nameField.getText());
    		}    		
    	} else if (source == deleteButton) {
    		if (!emptyTextField(nameField)) {
    			println("Delete: " + nameField.getText());    			
    		}    		    		
    	} else if (source == lookupButton) {
    		if (!emptyTextField(nameField)) {
    			println("Lookup: " + nameField.getText());
    		}    		    		
    	} else if ((source == statusField) || (source == statusButton)) {
    		if (!emptyTextField(statusField)) {
    			println("Change Status: " + statusField.getText());
    		}    		
    	} else if ((source == pictureField) || (source == pictureButton)) {
    		if (!emptyTextField(pictureField)) {
    			println("Change Picture: " + pictureField.getText());    			
    		}    		
    	} else if ((source == friendField) || (source == friendButton)) {
    		if (!emptyTextField(friendField)) {
    			println("Add Friend: " + friendField.getText());
    		}    		    		
    	}
	}
    
    private boolean emptyTextField(JTextField field) {
    	return field.getText().equals("");
    }

}
