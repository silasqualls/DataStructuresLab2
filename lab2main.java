
import java.util.Arrays;
import java.util.List;

public class lab2main {

    public static void main(String[] args) {
        myArrayList arrayList1 = new myArrayList();
        arrayList1.add(0, new Fraction(4,5));
        arrayList1.add(0, new Fraction(3,6));
        arrayList1.add(0, new Fraction(2,7));
        
        myArrayList.myListIterator itr = arrayList1.getIterator();
     
        Fraction[] fracArray = new Fraction[]{new Fraction(1,2), new Fraction(4,5)};  
        //adds all the Fractions from an array to ArrayList
        itr.addAll(fracArray);
        //Checks if there is a next element and prints it and moves our cursor
        //until we reach end
        while(itr.hasNext()) {
            System.out.println(itr.next());
        }
        //Checks for previous element and prints it and moves cursor back
        //until we reach beginning
        while(itr.hasPrevious()) {
            System.out.println(itr.previous());
        }
        itr.removeAllNext();
        //Should have removed all elements from arrayList
        while(itr.hasNext()) {
            System.out.println(itr.next());
        }
    }

}
