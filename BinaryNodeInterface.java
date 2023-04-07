
/**
 * Write a description of interface BinaryNodeInterface here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

public interface BinaryNodeInterface
{

    public Object getData();
    
    public void setData(Object newData);
    
    public BinaryNodeInterface getLeftChild();
    public BinaryNodeInterface getRightChild();
    
    public void setLeftChild(BinaryNodeInterface leftChild);
    
    public void setRightChild(BinaryNodeInterface rightChild);
    
    public boolean hasLeftChild();
    public boolean hasRightChild();
    
    public boolean isLeaf();
    

}
