import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class myListIterator implements ListIterator<Fraction>
{
    final myArrayList owner;
    int cursor;       // index of next element to return
    int lastRet = -1; // index of last element returned; -1 if none
    int expectedModCount; // used to prevent a desync between this iterator and its owner

    myListIterator(final myArrayList ownerIn) {
        owner = ownerIn;
        expectedModCount = owner.getModCount();
    }

    myListIterator(final myArrayList ownerIn, int startIndexIn) {
        this(ownerIn);
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
    // code by Mohamed   
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

//Code by Herve
    @Override
   public Fraction next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return myArrayList.get(cursor++);
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
    // code by Mohamed
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
     *
     * @since 25 September 2023
     * @author Julian Edwards
     */
    @Override
    public int nextIndex() { return cursor; }

    /**
     * Returns the index of the element that would be returned by a
     * subsequent call to {@link #previous}. (Returns -1 if the list
     * iterator is at the beginning of the list.)
     *
     * @return the index of the element that would be returned by a
     * subsequent call to {@code previous}, or -1 if the list
     * iterator is at the beginning of the list
     *
     * @since 25 September 2023
     * @author Julian Edwards
     */
    @Override
    public int previousIndex() { return cursor - 1; }

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
     *
     * @since 1 October 2023
     * @author Julian Edwards
     */
    @Override
    public void remove() {
        if(lastRet < 0) throw new IllegalStateException();
        else if(expectedModCount != owner.getModCount()) throw new ConcurrentModificationException();

        try {
            owner.remove(lastRet);
            cursor = lastRet;
            lastRet = -1;
            expectedModCount = owner.getModCount();
        }

        catch(final IndexOutOfBoundsException e) { throw new ConcurrentModificationException(); }
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
     * @since 25 September 2023
     * @author Julian Edwards
     */
    @Override
    public void set(final Fraction fraction) {
        throw new UnsupportedOperationException("Not used by the Lab2 assignment.");
    }

    /**
     * Inserts the specified element into the list (optional operation).
     * The element is inserted immediately before the element that
     * would be returned by {@link #next}, if any, and after the element
     * that would be returned by {@link #previous}, if any. (If the
     * list contains no elements, the new element becomes the sole element
     * on the list.) The new element is inserted before the implicit
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
     *
     * @since 1 October 2023
     * @author Julian Edwards
     */
    @Override
    public void add(final Fraction fraction) {
        if(expectedModCount != owner.getModCount()) throw new ConcurrentModificationException();
        try {
            owner.add(cursor++, fraction);
            lastRet = -1;
            expectedModCount = owner.getModCount();
        }

        catch(final IndexOutOfBoundsException e) { throw new ConcurrentModificationException(); }
    }

    /**
     * Inserts the specified elements into the list (optional operation).
     * The elements are inserted immediately before the element that
     * would be returned by {@link #next}, if any, and after the element
     * that would be returned by {@link #previous}, if any. (If the
     * list contains no elements, the new elements becomes the sole elements
     * on the list.) The new elements are inserted before the implicit
     * cursor: a subsequent call to {@code next} would be unaffected, and a
     * subsequent call to {@code previous} would return the last new element.
     * (This call increases by {@code fractions.length} the value that would be returned by a
     * call to {@code nextIndex} or {@code previousIndex}.)
     *
     * @param fractions the elements to insert
     * @throws UnsupportedOperationException if the {@code add} method is
     *                                       not supported by this list iterator
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this list
     * @throws IllegalArgumentException      if some aspect of the elements
     *                                       prevent them from being added to this list
     * @since 1 October 2023
     * @author Julian Edwards
     */
    public void addAll(final Fraction[] fractions) {
        if(fractions.length != 0) {
            if(expectedModCount != owner.getModCount()) throw new ConcurrentModificationException();
            try {
                // it should be noted that Arrays::asList does not return a standard ArrayList, the one used here is an array wrapper
                owner.addAll(cursor, Arrays.asList(fractions));
                cursor += fractions.length;
                lastRet = -1;
                expectedModCount = owner.getModCount();
            }

            catch(final IndexOutOfBoundsException e) { throw new ConcurrentModificationException(); }
        }
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
     * @since 1 October 2023
     * @author Julian Edwards
     */
    public void removeAllNext() {
        if(lastRet < 0) throw new IllegalStateException();
        else if(expectedModCount != owner.getModCount()) throw new ConcurrentModificationException();

        if(lastRet >= owner.size() - 1) return; // no elements to be removed
        lastRet++; // remove all elements after the selected one

        while(lastRet < owner.size()) owner.remove(lastRet);
        cursor = lastRet - 1;
        lastRet = -1;
        expectedModCount = owner.getModCount();
    }
}
