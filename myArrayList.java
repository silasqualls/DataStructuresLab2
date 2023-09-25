

    import java.util.ArrayList;
  public class myArrayList extends ArrayList<Fraction> {


      public myArrayList() {
          ensureCapacity(50);
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
  }

