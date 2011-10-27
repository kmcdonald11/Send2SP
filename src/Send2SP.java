/**
 * Send2SP - Upload your work to sketchpad.cc
 *
 * ##copyright##
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA  02111-1307  USA
 * 
 * @author		##author##
 * @modified	##date##
 * @version		##version##
 */

 package template.tool;
 
import java.awt.*;
import javax.swing.*;
import processing.app.*;
import processing.app.tools.*;
  
 
 public class Send2SP implements Tool {
 
	Editor editor;
 
	public String getMenuTitle() {
		return "Send2SP";
	}
 
	public void init(Editor theEditor) {
		this.editor = theEditor;
	}
 
	public void run() {
	    String sketchName = editor.getSketch().getName();

	    Object[] sendOptions = { "Upload", "Cancel" };
	    int send = JOptionPane.showOptionDialog(editor,
	    		"Do you wish to upload " + sketchName + " to sketchpad.cc?", "Send2SP",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, sendOptions, sendOptions[1]);
	    
	    // If they select "yes" to upload, display login
	    if (send == JOptionPane.YES_OPTION) {
	    	
	    	Object[] loginOptions = { "Sign in", "Cancel" };
	    	
	    	JLabel usrTxt = new JLabel("Username:");
	    	TextField usr = new TextField("", 20);
	    	JLabel pwdTxt = new JLabel("Password:");
	    	JPasswordField pwd = new JPasswordField("", 20);
	    	
	    	JPanel panel = new JPanel();
	    	panel.setLayout(new GridLayout(3,2));
	    	panel.add(usrTxt); panel.add(usr);
	    	panel.add(pwdTxt); panel.add(pwd);
	    	
	    	int login = JOptionPane.showOptionDialog(editor, panel, "Sign in using your sketchpad.cc account",
	    			JOptionPane.OK_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, loginOptions, loginOptions[0]);
	    	
		    // Validate login credentials
	    	if (login == JOptionPane.OK_OPTION){
	    		
	    		// No API functionality yet - that's 0.3 release
	    		// Just standard checks
	    		// For testing purposes, type username as "username" and 
	    		// password as "password" to get into this function   	      
	    	      
	    		if((usr.getText().equals("username") && new String(pwd.getPassword()).equals("password"))){
	    			JOptionPane.showMessageDialog(editor, "Success! You typed the right password.");
	    		}
	    		else{
	    			JOptionPane.showMessageDialog(editor, "Your username and/or password did not match our records. Please try again.", "Nay", JOptionPane.WARNING_MESSAGE);
	    		}
	    	}
	    	
	    	
	    	
	    }
	}
	
	/*  Bit of logic to implement later for password validation
        boolean isCorrect = true;
        char[] correctPassword = { 'p', 'a', 's', 's', 'w', 'o', 'r', 'd' };
 
        if (input.length != correctPassword.length) {
            isCorrect = false;
        } else {
            isCorrect = Arrays.equals (input, correctPassword);
        }
 
        // Reset the correct password
        Arrays.fill(correctPassword,'0');
 
        return isCorrect;
    }*/
 
 }



