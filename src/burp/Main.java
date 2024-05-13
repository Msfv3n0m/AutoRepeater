package burp;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JComponent;
import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;


//Burp will auto-detect and load any class that extends BurpExtension.
public class Main implements BurpExtension {
    @Override
    public void initialize(MontoyaApi api) {
        api.extension().setName("AutoRepeater");
        
        //Register the HTTP handler with Burp.
         MyHttpHandler handler = new MyHttpHandler(api);
        api.http().registerHttpHandler(handler);
        
        //Register UI
        api.userInterface().registerSuiteTab("AutoRepeater", new AutoRepeaterTab(api,handler));

    }
    private class AutoRepeaterTab extends JComponent
    {
		private static final long serialVersionUID = 1L;

		public AutoRepeaterTab(MontoyaApi api, MyHttpHandler handler)
        {
			setLayout(new GridBagLayout()); // Use GridBagLayout for more precise positioning
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.gridx = 0; // Place the component in the first column
	        gbc.gridy = 0; // Place the component in the first row
	        gbc.anchor = GridBagConstraints.NORTHWEST; // Align component to the top-left corner
	        JButton button = new JButton("OFF");
        	button.setBackground(new Color(191,26,26));
	        button.addActionListener(e -> {
	        	String tmpStatus = button.getText();
		        if (tmpStatus == "OFF") {
		        	button.setText("ON");
		        	button.setBackground(new Color(38,100,57));
		            api.logging().raiseDebugEvent("color: " + button.getBackground());
		        }
		        else {
		        	button.setBackground(new Color(191,26,26));
			        button.setText("OFF");
		        }
	            handler.toggleStatus();
	            api.logging().raiseDebugEvent("Status: " + handler.getStatus());
	        });

	        add(button, gbc); // Add button with constraints
	    }
	}
}