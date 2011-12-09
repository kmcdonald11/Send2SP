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
import java.io.*;
import java.net.*;


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

					try { 		    			
		    			
		    			String user = usr.getText();
		    			String pass = new String(pwd.getPassword());
		    			
		    			URL URLObj = null;
		    			URLConnection connect = null;
		    			String cookie = "";
		    			
		    	        try {
		    	        	// Establish a URL and open a connection to it. Set it to output mode.
		    	        	URLObj = new URL("http://studio.sketchpad.cc/sp/account/sign-in");
		    	        	connect = URLObj.openConnection();
		    	        	connect.setDoOutput(true); 
		    	        }
		    	        catch (MalformedURLException ex) {
		    	        	System.out.println("The URL specified was unable to be parsed or uses an invalid protocol. Please try again.");
		    	        	System.exit(1);
		    	        }
		    	        catch (Exception ex) {
		    	        	System.out.println("An exception occurred. " + ex.getMessage());
		    	        	System.exit(1);
		    	        }
		    	         
		    	        
		    	        String headerName=null;
		    			for (int i=1; (headerName = connect.getHeaderFieldKey(i))!=null; i++) {
		    			 	if (headerName.equals("Set-Cookie"))              
		    			 		cookie = connect.getHeaderField(i);  
		    			 		//editor.setSelectedText("Cookie!");
		    			 }
		    			 	
	    				cookie = cookie.substring(0, cookie.indexOf(";"));
	    		        String cookieName = cookie.substring(0, cookie.indexOf("="));
	    		        String cookieValue = cookie.substring(cookie.indexOf("=") + 1, cookie.length());
	    		        
	    		        //cookie name (cookieName) and the cookie value (cookieValue)
					    //editor.setSelectedText("Cookie name: " + cookieName + "\nCookie value: " + cookieValue);
	    		        
					    // We can't just write to the connection again. We need to remake it, and 
					    // give the cookie values from above
		    	        try {
		    	        	// Establish a URL and open a connection to it. Set it to output mode.
		    	        	URLObj = new URL("http://studio.sketchpad.cc/sp/account/sign-in");
		    	        	connect = URLObj.openConnection();
		    	        	editor.setSelectedText("Set cookie: " + cookieName + "=" + cookieValue + "\n"); 
			    			connect.setRequestProperty("Cookie", cookieName + "=" + cookieValue);
		    	        	connect.setDoOutput(true); 
		    	        	connect.connect();
		    	        	
		    	        	editor.setSelectedText("Get cookie: " + connect.getRequestProperty("Cookie") + "\n"); 
		    	        }
		    	        catch (MalformedURLException ex) {
		    	        	System.out.println("The URL specified was unable to be parsed or uses an invalid protocol. Please try again.");
		    	        	System.exit(1);
		    	        }
		    	        catch (Exception ex) {
		    	        	System.out.println("An exception occurred. " + ex.getMessage());
		    	        	System.exit(1);
		    	        }	
					    
		    	        try {
		    	        	// Create a buffered writer to the URLConnection's output stream and write our forms parameters.
		    	        	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connect.getOutputStream()));
		    	        	writer.write("email=" + user + "&password=" + pass);
		    	        	writer.close();
		    	        	// Now establish a buffered reader to read the URLConnection's input stream.
		    	        	BufferedReader reader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
		    	        	String lineRead = "";
		    	        	 // Read all available lines of data from the URL and print them to screen.
		    	        	while ((lineRead = reader.readLine()) != null) {
		    	        		System.out.println(lineRead);
		    	        	}
		    	        	reader.close();
		    	        }
		    	        catch (Exception ex) {
		    	        	System.out.println("There was an error reading or writing to the URL: " + ex.getMessage());
		    	        }
					    
					}
					catch (Exception e) {
						e.printStackTrace(); 
					}
	    			

	    	}
	    	
	    	
	    	
	    }
	}

 
 }



