/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */

import acm.graphics.*;

import java.awt.*;
import java.util.Iterator;

public class FacePamphletCanvas extends GCanvas 
					implements FacePamphletConstants {
	
	/**
	 * private constants
	 */
	private static final Color PROFILE_NAME_COLOR = Color.BLUE;
	private static final String NO_IMAGE_TEXT = "No Image";
	private static final String NO_STATUS_TEXT = "No current status";
	private static final String STATUS_TEXT_DELIMITER = " is ";
	private static final String FRIENDS_LABEL_TEXT = "Friends:";
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas() {
		// You fill this in
	}
	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
		GLabel label = new GLabel(msg);
		label.setFont(MESSAGE_FONT);
		label.setLocation(
				(getWidth() - label.getWidth()) / 2,
				getHeight() - BOTTOM_MESSAGE_MARGIN);
		add(label);
	}
		
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		if (profile == null) return;
		double x = LEFT_MARGIN;
		double y = TOP_MARGIN;
		y = drawName(profile.getName(), x, y);
		y += IMAGE_MARGIN;
		drawFriends(profile.getFriends(), getWidth() / 2, y);
		y = drawImage(profile.getImage(), x, y);
		drawStatus(profile, x, y + STATUS_MARGIN);
	}
	
	private double drawName(String name, double x, double y) {
		GLabel label = new GLabel(name);
		label.setFont(PROFILE_NAME_FONT);
		label.setColor(PROFILE_NAME_COLOR);
		label.setLocation(x, y += label.getAscent());
		add(label);
		return y;
	}
	
	private double drawImage(GImage image, double x, double y) {
		double width = IMAGE_WIDTH;
		double height = IMAGE_HEIGHT;
		if (image == null) {
			add(new GRect(x, y, width, height));
			GLabel label = new GLabel(NO_IMAGE_TEXT);
			label.setFont(PROFILE_IMAGE_FONT);
			label.setLocation(
					x + (width - label.getWidth()) / 2, 
					y + (height + label.getAscent()) / 2);
			add(label);
		} else {
			image.scale(width / image.getWidth(), height / image.getHeight());
			image.setLocation(x, y);
			add(image);
		}
		return y + height;
	}
	
	private void drawStatus(FacePamphletProfile profile, double x, double y) {
		String status = profile.getStatus(); 
		if (status.equals("")) {
			status = NO_STATUS_TEXT;
		} else {
			status = profile.getName() + STATUS_TEXT_DELIMITER + status;
		}
		GLabel label = new GLabel(status);
		label.setFont(PROFILE_STATUS_FONT);
		label.setLocation(x, y += label.getAscent());
		add(label);
	}
	
	private void drawFriends(Iterator<String> friends, double x, double y) {
		GLabel label = new GLabel(FRIENDS_LABEL_TEXT, x, y);
		label.setFont(PROFILE_FRIEND_LABEL_FONT);
		add(label);
		y += label.getHeight();
		while (friends.hasNext()) {
			GLabel friend = new GLabel(friends.next(), x, y);
			friend.setFont(PROFILE_FRIEND_FONT);
			y += friend.getHeight();
			add(friend);
		}
	}
	
}
