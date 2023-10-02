
import java.util.Arrays;
import java.util.List;

public class lab2main {

    public static void main(String[] args) {
        myArrayList arrayList1 = new myArrayList();
        arrayList1.add(0, new Fraction(4,5));
        arrayList1.add(0, new Fraction(3,6));
        arrayList1.add(0, new Fraction(2,7));
        
        myArrayList.myListIterator itr = arrayList1.getIterator();
     
        Fraction[] fracArray = new Fraction[]{new Fraction(1,2), new Fraction(4,6)};  
        //adds all the Fractions from an array to ArrayList
        
        itr.addAll(fracArray);
        //Checks if there is a next element and prints it and moves our cursor
        //until we reach end
        System.out.println("Next Prints: ");
        while(itr.hasNext()) {
            itr.next().print();;
        }
        //Checks for previous element and prints it and moves cursor back
        //until we reach beginning
        System.out.println("Previous Prints: ");
        while(itr.hasPrevious()) {
            itr.previous().print();
        }
        itr.removeAllNext();
        //Should have removed all elements from arrayList except for the first element
        System.out.println("Remove Prints: ");
        while(itr.hasNext()) {
            itr.next().print();;
        }
    }

}
