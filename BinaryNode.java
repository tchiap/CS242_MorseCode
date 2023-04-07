
/**
 * Write a description of class BinaryNode here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

 
public class BinaryNode implements BinaryNodeInterface, java.io.Serializable
{

    private Object data;
    private BinaryNode left;
    private BinaryNode right;
    
    public BinaryNode()
    {
        this(null);
    }
    
    public BinaryNode(Object dataPortion)
    {
        this(dataPortion, null, null);   
    }
    
    public BinaryNode(Object dataPortion, BinaryNode leftChild,
                                            BinaryNode rightChild)
    {
        data = dataPortion;
        left = leftChild;
        right = rightChild;
    }
    
    public Object getData()
    {
        return data;   
    }
    
    public void setData(Object newData)
    {
        data = newData;   
    }
    
    public BinaryNodeInterface getLeftChild()
    {
        return left;   
    }
    
    public void setLeftChild(BinaryNodeInterface leftChild)
    {
        left = (BinaryNode)leftChild;
    }
    
    public boolean hasLeftChild()
    {
        return left != null;   
    }
    
    public BinaryNodeInterface getRightChild()
    {
        return right;   
    }
    
    public void setRightChild(BinaryNodeInterface rightChild)
    {
        right = (BinaryNode)rightChild;
    }
    
    public boolean hasRightChild()
    {
        return right != null;   
    }    
    
    public boolean isLeaf()
    {
        return (left == null) && (right == null);   
    }
    
    public BinaryNode copy()
    {
        BinaryNode newRoot = new BinaryNode(data);
        
        if (left != null)
            newRoot.left = left.copy();        
        if (right != null)
            newRoot.right = right.copy();
            
        return newRoot;
    }
    
    public int getHeight()
    {
        return getHeight(this);
    }
    
    private int getHeight(BinaryNode node)
    {
        int height = 0;
        
        if (node != null)
            height = 1 + Math.max(getHeight(node.left),
                                    getHeight(node.right));
        return height;
    }
    
    public int getNumberOfNodes()
    {
        int leftNumber = 0;
        int rightNumber = 0;
        
        if (left != null)
            leftNumber = left.getNumberOfNodes();
        if (right != null)
            rightNumber = right.getNumberOfNodes();
        return 1 + leftNumber + rightNumber;
    }
    

}

