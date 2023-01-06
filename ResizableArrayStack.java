import java.util.Arrays;
import java.util.EmptyStackException;

/**
    A class of stacks whose entries are stored in an array.
    @author Frank M. Carrano and Timothy M. Henry
    @version 5.0
*/
public final class ResizableArrayStack<T> implements StackInterface<T> {
    private T[] stack;    //Array of stack entries
    private int topIndex; //Index of top entry
    private boolean integrityOK = false;
    private static final int DEFAULT_CAPACITY = 50;
    private static final int MAX_CAPACITY = 10000;
  
    public ResizableArrayStack() {
        this(DEFAULT_CAPACITY);
    } //end default constructor
  
    public ResizableArrayStack(int initialCapacity) {
        integrityOK = false;
        checkCapacity(initialCapacity);
      
        //The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempStack = (T[])new Object[initialCapacity];
        stack = tempStack;
        topIndex = -1;
        integrityOK = true;
    } //end constructor
  
    //adding to the top
    public void push(T newEntry) {
        checkIntegrity();
        ensureCapacity();
        stack[topIndex + 1] = newEntry;
        topIndex++;
    } //end push


    //removing from the top
    public T pop() {
        checkIntegrity();
        if (isEmpty())
            throw new EmptyStackException();
        else {
            T top = stack[topIndex];
            stack[topIndex] = null;
            topIndex--;
            return top;
        } //end if
    } //end pop

    //Retreiving the top
    public T peek() {
        checkIntegrity();
        if (isEmpty())
            throw new EmptyStackException();
        else
            return stack[topIndex];
    } //end peek

    //isEmpty and Clear
    public boolean isEmpty() {
        return topIndex < 0;
    } // end isEmpty

    public void clear() {
        checkIntegrity();
        // Remove references to the objects in the stack,
        // but do not deallocate the array
        while (topIndex > -1) {
            stack[topIndex] = null;
            topIndex--;
        } // end while
        //Assertion: topIndex is -1
    } // end clear

    // Throws an exception if the client requests a capacity that is too large.
    private void checkCapacity(int capacity) {
        if (capacity > MAX_CAPACITY)
        throw new IllegalStateException("Attempt to create a bag whose capacity exceeds allowed maximum of " + MAX_CAPACITY);
    } // end checkCapacity

    private void ensureCapacity() {
        //If array is full, double its size 
        if (topIndex >= stack.length - 1) { 
            int newLength = 2 * stack.length;
            checkCapacity(newLength);
            stack = Arrays.copyOf(stack, newLength);
        } //end if
    } //end ensureCapacity
    
    // Throws an exception if receiving object is not initialized.
    private void checkIntegrity() {
        if (!integrityOK)
        throw new SecurityException ("ArrayBag object is corrupt.");
    } // end checkintegrity
} // end ResizableArrayStack
