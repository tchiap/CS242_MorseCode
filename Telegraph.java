/**
 * Telegraph
 *
 * Constructs a JFrame object to hold a message and its Morse code
 * equivalent.
 *
 * Uses the MorseCode class to perform translation in both directions.
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Telegraph extends JFrame    implements ActionListener
{
    private Telegraph otherStation;  // destination

    private JTextField inputText;   // message to send
    private JTextArea codedText;    // message in Morse code
    private JTextField receivedText; // message decoded

    private static final Font courier16 = 
	                      new Font("Monospaced", Font.PLAIN, 16);

    // Constructor for the Telegraph
    public Telegraph(String name)
    {
	// call the JFrame's constructor
	super(name);

	inputText = new JTextField("Enter a message", 30);
	inputText.setFont(courier16);
	inputText.selectAll();
	inputText.addActionListener(this);
    
	codedText = new JTextArea(4, 30);
	codedText.setEditable(false);
	codedText.setLineWrap(true);
	codedText.setFont(courier16);

	receivedText = new JTextField(30);
	receivedText.setBackground(Color.yellow);
	receivedText.setEditable(false);
	receivedText.setFont(courier16);
    
	Container c = getContentPane();
	c.setLayout(new FlowLayout(FlowLayout.LEFT));
	c.add(inputText);
	c.add(codedText);
	c.add(receivedText);
    }

    // make the connection
    public void connect(Telegraph other)
    {
	otherStation = other;
    }

    // encode the message and send it
    public void send(String message)
    {
	inputText.setText("");
	receivedText.setText("");
	String code = MorseCode.encode(message);
	codedText.setText("[" + code + "] >>>");
	otherStation.receive(code);
    }

    // receive the message and decode it
    public void receive(String code)
    {
	codedText.setText(">>> [" + code + "]");
	String message = MorseCode.decode(code);
	receivedText.setText(message);
    }

    // handle the sending message event
    public void actionPerformed(ActionEvent e)
    {
	send(inputText.getText());
    }

    /******************************************************************/
    /***************                        main       ****************/
    /******************************************************************/

    public static void main(String[] args)
    {
	// Create a telegraph in New York
	Telegraph new_york = new Telegraph("New York");
	new_york.addWindowListener(new WindowAdapter()
	    { public void windowClosing(WindowEvent e) { System.exit(0); }});
	new_york.setBounds(50, 150, 300, 200);

	// Create a telegraph in London
	Telegraph london = new Telegraph("London");
	london.addWindowListener(new WindowAdapter()
	    { public void windowClosing(WindowEvent e) { System.exit(0); }});
	london.setBounds(400, 300, 300, 200);

	// Connect each of the telegraphs to each other
	london.connect(new_york);
	new_york.connect(london);

	// Build the structures needed for encoding and decoding
	MorseCode.start();
	
	// show the results for the London side
	london.show();

	// show the results for the New York side
	new_york.show();
    }
}
