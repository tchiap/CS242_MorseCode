
import java.util.TreeMap;

public class MorseCode
{
    private static final char DOT = '.';
    private static final char DASH = '-';

    private static TreeMap codeMap;   //  For encoding
    private static BinaryNode decodeTree;  // For decoding

    public static void start()
    {
    	codeMap = new TreeMap();
    
    	// put a space in the root of the decoding tree
    	decodeTree = new BinaryNode(new Character(' '));
    
    	addSymbol('A', ".-");
    	addSymbol('B', "-...");
    	addSymbol('C', "-.-.");
    	addSymbol('D', "-..");
    	addSymbol('E', ".");
    	addSymbol('F', "..-.");
    	addSymbol('G', "--.");
    	addSymbol('H', "....");
    	addSymbol('I', "..");
    	addSymbol('J', ".---");
    	addSymbol('K', "-.-");
    	addSymbol('L', ".-..");
    	addSymbol('M', "--");
    	addSymbol('N', "-.");
    	addSymbol('O', "---");
    	addSymbol('P', ".--.");
    	addSymbol('Q', "--.-");
    	addSymbol('R', ".-.");
    	addSymbol('S', "...");
    	addSymbol('T', "-");
    	addSymbol('U', "..-");
    	addSymbol('V', "...-");
    	addSymbol('W', ".--");
    	addSymbol('X', "-..-");
    	addSymbol('Y', "-.--");
    	addSymbol('Z', "--..");
    	addSymbol('0', "-----");
    	addSymbol('1', ".----");
    	addSymbol('2', "..---");
    	addSymbol('3', "...--");
    	addSymbol('4', "....-");
    	addSymbol('5', ".....");
    	addSymbol('6', "-....");
    	addSymbol('7', "--...");
    	addSymbol('8', "---..");
    	addSymbol('9', "----.");
    	addSymbol('.', ".-.-.-");
    	addSymbol(',', "--..--");
    	addSymbol('?', "..--..");
    }

    /**
     *  Inserts a letter and its Morse code string into the encoding map 
     *  and calls treeInsert to insert them into the decoding tree.
     */
    private static void addSymbol(char letter, String code)
    {
        Character c = new Character(letter);
        codeMap.put(c,code);
	    treeInsert(letter,code);
    }

    /**
     *  Inserts a letter and its Morse code string into the 
     *  decoding tree.  Each dot-dash string corresponds to a path
     *  in the tree from the root to a node: at a "dot" go left, at a "dash" go
     *  right.  The node at the end of the path holds the symbol
     *  for that code string.
     */
    private static void treeInsert(char letter, String code)
    {

    	BinaryNode rootNode = decodeTree;
    	for (int n = 0; n<code.length(); n++) 
    	{
    	    char codeChar = code.charAt(n);
    	    if (codeChar == DOT)
    	    {
    	        if (!rootNode.hasLeftChild())
    	        {
    	           rootNode.setLeftChild(new BinaryNode("empty"));
    	        }
    	        rootNode = (BinaryNode)rootNode.getLeftChild();
    	    }
    	    else if (codeChar == DASH)
    	    {
    	        if (!rootNode.hasRightChild())
    	        {
    	           rootNode.setRightChild(new BinaryNode("empty"));
    	        }
    	        rootNode = (BinaryNode)rootNode.getRightChild();
    	    }
    	}
        rootNode.setData(new Character(letter));
    }

    /**
     *   Converts text into a Morse code message.  Adds a space after a dot-dash
     *   sequence for each letter.  Other spaces in the text are transferred 
     *   directly into the encoded message.
     *   Returns the encoded message.
     */
    public static String encode(String text)
    {
        
        start();
        StringBuffer morse = new StringBuffer(400);
        //convert text to uppercase
        text = text.toUpperCase();
        for(int i = 0; i < text.length(); i++)
        {
            if(text.charAt(i) == ' ')
            {
                morse.append(' ');
            }
            else
            {
                morse.append(codeMap.get(new Character(text.charAt(i))));
            }
            morse.append(' ');
        }
        return morse.toString();
    }

    /**
     *   Converts a Morse code message into a text string.  Assumes that dot-dash
     *   sequences for each letter are separated by one space.  Additional spaces 
     *   are transferred directly into text.
     *   Returns the plain text message.
     */
    public static String decode(String morse)
    {
    	start();
    	StringBuffer text = new StringBuffer(100);
    	BinaryNode rootNode;
    	for(int i = 0; i < morse.length(); i++)
    	{
    	   rootNode = decodeTree;
    	   while(morse.charAt(i) != ' ')
    	   {
    	       if(morse.charAt(i) == DOT)
    	       {
    	           rootNode = (BinaryNode) rootNode.getLeftChild();
    	       }
    	       else
    	       {
    	           rootNode = (BinaryNode) rootNode.getRightChild();
    	       }
    	       i++;
    	   }
    	   text.append(rootNode.getData());
    	}
    	return text.toString();
    }

    /**
     *   Print the contents of the decoding tree using an in-order traversal.
     *   Use standard output.
     */
    public static void printTree()
    {
    	printTree(decodeTree);
    }
    
        //private class recursive method to print the tree
    private static void printTree(BinaryNode rootNode)
    {
    	StringBuffer contents = new StringBuffer();
    	if (rootNode != null)
    	{
    	    printTree((BinaryNode)rootNode.getLeftChild());
    	    System.out.print(rootNode.getData()+" ");
    	    printTree((BinaryNode)rootNode.getRightChild());
    	}
    }

    public static void main (String[] args)
    {
        start();
        printTree();
        System.out.println();

    }
}