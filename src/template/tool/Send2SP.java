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
 * @author		Kaitlyn McDonald 
 * @modified	09/30/2011
 * @version		0.1
 */

 package template.tool;
 
 import javax.swing.JOptionPane;
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

	    Object[] options = { "Upload", "Cancel" };
	    int result = JOptionPane.showOptionDialog(editor,
	    		"Do you wish to upload " + sketchName + " to sketchpad.cc?", "Send2SP",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);
	    
	    if (result == JOptionPane.YES_OPTION) {
	    	JOptionPane.showMessageDialog(editor, "Sent " + sketchName + 
	    			"to sketchpad.cc! (But not really since it doesn't work yet).", "Yay!", JOptionPane.PLAIN_MESSAGE);
	    }
	}
 
 }



