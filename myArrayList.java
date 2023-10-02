import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;


  public class myArrayList extends ArrayList<Fraction> implements Iterable<Fraction>{

      public myArrayList() {
          super(50);
      }
      public Fraction remove(int index) {
          if (index < 0 || index >= size()) {
              throw new IndexOutOfBoundsException("Index is out of bounds.");
          }

          return super.remove(index);
      }

      public void add(int index, Fraction input) {
          if (index < 0 || index > size()) {
              throw new IndexOutOfBoundsException("Index is out of bounds");
          }
          ensureCapacity(size() + 1);
          super.add(index, input);
      }
      public int indexOf(Fraction input) {
          return super.indexOf(input);
      }
      
      public myListIterator getIterator() {
          
          return new myListIterator(this, modCount);
        }
      

 class myListIterator implements ListIterator<Fraction>
{
    final myArrayList owner;
    int cursor;       // index of next element to return
    int lastRet = -1; // index of last element returned; -1 if no such
    int modCount;
    int expectedModCount;

    // ArrayList has a field called modCount, pass that in here.
    myListIterator(myArrayList ownerIn, int modCountIn) {
        owner = ownerIn;
        modCount = modCountIn;
        expectedModCount = modCountIn;
    }

    // ArrayList has a field called modCount, pass that in here.
    myListIterator(myArrayList ownerIn, int modCountIn, int startIndexIn) {
        this(ownerIn, modCountIn);
        cursor = startIndexIn;
    }

    /**
     * Returns {@code true} if this list iterator has more elements when
     * traversing the list in the forward direction. (In other words,
     * returns {@code true} if {@link #next} would return an element rather
     * than throwing an exception.)
     *
     * @return {@code true} if the list iterator has more elements when
     * traversing the list in the forward direction
     */
    //Code by Mohamed
    @Override
    public boolean hasNext() {
        return cursor < owner.size();
    }

    /**
     * Returns the next element in the list and advances the cursor position.
     * This method may be called repeatedly to iterate through the list,
     * or intermixed with calls to {@link #previous} to go back and forth.
     * (Note that alternating calls to {@code next} and {@code previous}
     * will return the same element repeatedly.)
     *
     * @return the next element in the list
     * @throws NoSuchElementException if the iteration has no next element
     */
    @Override
    //Code By Herve
    public Fraction next() {
        if (cursor >= owner.size()) {
            throw new NoSuchElementException("No next element");
        }
        int nextIndex = cursor;
        lastRet = nextIndex;
        cursor = nextIndex + 1;
        return owner.get(nextIndex);
    }

    /**
     * Returns {@code true} if this list iterator has more elements when
     * traversing the list in the reverse direction.  (In other words,
     * returns {@code true} if {@link #previous} would return an element
     * rather than throwing an exception.)
     *
     * @return {@code true} if the list iterator has more elements when
     * traversing the list in the reverse direction
     */
    //Code by Mohamed   
@Override
    public boolean hasPrevious() {
        return cursor > 0;
    }
    /**
     * Returns the previous element in the list and moves the cursor
     * position backwards.  This method may be called repeatedly to
     * iterate through the list backwards, or intermixed with calls to
     * {@link #next} to go back and forth.  (Note that alternating calls
     * to {@code next} and {@code previous} will return the same
     * element repeatedly.)
     *
     * @return the previous element in the list
     * @throws NoSuchElementException if the iteration has no previous
     *                                element
     */
    @Override
    //Code by Silas
    public Fraction previous() {
        if (cursor <= 0) {
            throw new NoSuchElementException("No previous element");
        }
        int previousIndex = cursor - 1;
        lastRet = previousIndex;
        cursor = previousIndex;
        return owner.get(previousIndex);
    }

    /**
     * Returns the index of the element that would be returned by a
     * subsequent call to {@link #next}. (Returns list size if the list
     * iterator is at the end of the list.)
     *
     * @return the index of the element that would be returned by a
     * subsequent call to {@code next}, or list size if the list
     * iterator is at the end of the list
     */
    @Override
    public int nextIndex() {
        return 0;
    }

    /**
     * Returns the index of the element that would be returned by a
     * subsequent call to {@link #previous}. (Returns -1 if the list
     * iterator is at the beginning of the list.)
     *
     * @return the index of the element that would be returned by a
     * subsequent call to {@code previous}, or -1 if the list
     * iterator is at the beginning of the list
     */
    @Override
    public int previousIndex() {
        return 0;
    }

    /**
     * Removes from the list the last element that was returned by {@link
     * #next} or {@link #previous} (optional operation).  This call can
     * only be made once per call to {@code next} or {@code previous}.
     * It can be made only if {@link #add} has not been
     * called after the last call to {@code next} or {@code previous}.
     *
     * @throws UnsupportedOperationException if the {@code remove}
     *                                       operation is not supported by this list iterator
     * @throws IllegalStateException         if neither {@code next} nor
     *                                       {@code previous} have been called, or {@code remove} or
     *                                       {@code add} have been called after the last call to
     *                                       {@code next} or {@code previous}
     */
    @Override
    public void remove() {

    }

    /**
     * Replaces the last element returned by {@link #next} or
     * {@link #previous} with the specified element (optional operation).
     * This call can be made only if neither {@link #remove} nor {@link
     * #add} have been called after the last call to {@code next} or
     * {@code previous}.
     *
     * @param fraction the element with which to replace the last element returned by
     *                 {@code next} or {@code previous}
     * @throws UnsupportedOperationException if the {@code set} operation
     *                                       is not supported by this list iterator
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws IllegalArgumentException      if some aspect of the specified
     *                                       element prevents it from being added to this list
     * @throws IllegalStateException         if neither {@code next} nor
     *                                       {@code previous} have been called, or {@code remove} or
     *                                       {@code add} have been called after the last call to
     *                                       {@code next} or {@code previous}
     */
    @Override
    public void set(Fraction fraction) {

    }

    /**
     * Inserts the specified element into the list (optional operation).
     * The element is inserted immediately before the element that
     * would be returned by {@link #next}, if any, and after the element
     * that would be returned by {@link #previous}, if any.  (If the
     * list contains no elements, the new element becomes the sole element
     * on the list.)  The new element is inserted before the implicit
     * cursor: a subsequent call to {@code next} would be unaffected, and a
     * subsequent call to {@code previous} would return the new element.
     * (This call increases by one the value that would be returned by a
     * call to {@code nextIndex} or {@code previousIndex}.)
     *
     * @param fraction the element to insert
     * @throws UnsupportedOperationException if the {@code add} method is
     *                                       not supported by this list iterator
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws IllegalArgumentException      if some aspect of this element
     *                                       prevents it from being added to this list
     */
    @Override
    public void add(Fraction fraction) {

    }
    /**
     * Removes from the list all elements after the element that would
     * be returned by {@link #next} (optional operation). This call can
     * only be made once per call to {@code next} or {@code previous}.
     * It can be made only if {@link #add} has not been
     * called after the last call to {@code next} or {@code previous}.
     *
     * @throws UnsupportedOperationException if the {@code remove}
     *                                       operation is not supported by this list iterator
     * @throws IllegalStateException         if neither {@code next} nor
     *                                       {@code previous} have been called, or {@code remove} or
     *                                       {@code add} have been called after the last call to
     *                                       {@code next} or {@code previous}
     * @since 25 September 2023
     * @author Julian Edwards
     */
    //Code by Julian
    
    public void addAll(Fraction[] fractions) {
        for(final Fraction fraction : fractions) add(fraction);
    }

    /**
     * Removes from the list all elements after the element that would
     * be returned by {@link #next} (optional operation). This call can
     * only be made once per call to {@code next} or {@code previous}.
     * It can be made only if {@link #add} has not been
     * called after the last call to {@code next} or {@code previous}.
     *
     * @throws UnsupportedOperationException if the {@code remove}
     *                                       operation is not supported by this list iterator
     * @throws IllegalStateException         if neither {@code next} nor
     *                                       {@code previous} have been called, or {@code remove} or
     *                                       {@code add} have been called after the last call to
     *                                       {@code next} or {@code previous}
     * @since 25 September 2023
     * @author Julian Edwards
     */
    //Code by Julian
    public void removeAllNext() {
        if(hasNext()) next(); // set cursor to next value, so the built-in remove method can be used.
        else return; // no elements after current cursor, method cannot be called.
        while(hasNext()) {  // remove all elements after the selected one.
            next();
            remove();
        }

        previous(); // set cursor back to the starting value (prior to this method being called).
    }


}
  
  


    /**
     * Expose this value, so it can be used to prevent possible desyncs from {@link myListIterator}.
     * @return {@link ArrayList#modCount}.
     *
     * @since 1 October 2023
     * @author Julian Edwards
     */
    int getModCount() { return modCount; }
}
