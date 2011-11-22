/**
 * Send2SP - Upload your work to sketchpad.cc
 *
 * (c) 2011
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
 * @author		Kaitlyn McDonald http://yoururl.com
 * @modified	11/21/2011
 * @version		0.1
 */

 package template.tool;
 
import java.awt.*;
import java.io.*;
import java.net.*;

import org.json.*;
import org.codehaus.jackson.*;

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
	    	
	    	JLabel usrTxt = new JLabel("Email:");
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
	    		
	    		if((usr.getText().equals("email") && new String(pwd.getPassword()).equals("password"))){
	    			JOptionPane.showMessageDialog(editor, "Success! You typed the right password.");
	    			
	    			// This following section is split into two for now - since there are currently 2 URLs
	    			// This is being updated from server side
					/*try { 		    			
		    			// JSON user object
		    			JSONObject jsonUsr = new JSONObject();
		    			jsonUsr.put("email", usr.getText());
		    			jsonUsr.put("password", new String(pwd.getPassword()));
		    			String jUsrStr = jsonUsr.toString(); 

		    			// JSON sketch
		    			JSONObject jsonSketch = new JSONObject();
		    		    String selection = editor.getSelectedText();
		    		    char[] stuff = selection.toCharArray();
		    		    jsonSketch.put("sketch", stuff);
		    			String jSketchStr = jsonSketch.toString();		    			
		    			
						// Connection info
		    			URL url;
						url = new URL("http://studio.sketchpad.cc/sp/account/sign-in");
		    			URLConnection connection = url.openConnection();
		    			connection.setDoInput(true);  
		    			connection.setDoOutput(true); 
		    			//connection.setRequestProperty("Content-Type", "application/json");
		    			OutputStreamWriter wr = new OutputStreamWriter (connection.getOutputStream ());
		    	        wr.write(jUsrStr);
		    	        wr.flush();
		    	        wr.close();
		    	        
					    // Get response data
					    DataInputStream input;
					    input = new DataInputStream (urlConn.getInputStream ());
					    String str;
					    while (null != ((str = input.readLine()))){
						    System.out.println (str);
						    editor.setSelectedText(str + "\n");
					    }
					    input.close ();
			    		
		    			
					} catch (Exception e) {
					}
	    			*/

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



