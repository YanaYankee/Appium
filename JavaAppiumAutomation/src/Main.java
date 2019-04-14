import org.junit.Test;

public class Main {
    int a = 1;
    int b = 2;

    // ---------------------- multiply with func result called with 2 argument  // result: 150
    @Test
    public void multiplyTwoParams() {
        int n = this.multiplied(10, 15);
        System.out.println(n);
    }

    public int multiplied( int num, int multiplier) {
        return num * multiplier;
    }

    // -----------------------multiply with func result called with argument  // result: True
    @Test
    public void multipliedNums() {
        int a = this.multiply(5);
        if (a == 10 ) {
            System.out.println("True");
        } else {
            System.out.println("True");
        }
    }

    public int multiply( int number) {
        return number * 2;
    }

// ------------------------use number returned by another func // result: True
    @Test
    public void compareInt() {
        int a = this.giveMeInt();
        if (a > 10 ) {
            System.out.println("False");
        } else {
            System.out.println("True");
        }
    }

    public int giveMeInt() {
       return 5;
    }

 // ------------------------call func from another func / result: I am secondFunc
    @Test

    public void firstFunc() {
        this.secondFunc();
    }

    public void secondFunc() {
        System.out.println("I am secondFunc");
    }

// ----------------------------------print fields/vars from the func and outside func

    @Test
    public void myFirstTest () { //void, cos does not return anything
        int a = 3;
        int b = 4;


        System.out.println(a);
        System.out.println(b);

        System.out.println(this.a);
        System.out.println(this.b);

//     ---------------------------------- Result (call var outside func):
//        3
//        4
//        1
//        2


//        if (a > b) {
//            System.out.print("hello");
//        } else {
//            System.out.print("that");
//        }
    }


}
