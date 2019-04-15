import org.junit.Test;
import org.junit.Assert;
import java.lang.String;
import java.util.Arrays;

// MainClass class is parent to MainClassTest
public class MainClassTest extends MainClass {

// ---------------------------Тест 1 ---------------------------
//      Сделать класс MainClass.//
//      В классе MainClass сделать метод, который возвращает число 14 (назвать: getLocalNumber).//
//      Сделать класс MainClassTest.//
//      В классе MainClassTest написать тест, проверяющий, что метод getLocalNumber возвращает
//   число 14 (назвать: testGetLocalNumber).
//      Не забываем в проверку добавлять понятный текст ошибки.

        @Test
    public void testGetLocalNumber()
    {
        int a = this.getLocalNumber(); // get returned by method getLocalNumber() value
        int numberExpected = numberToTest(14); // get value to compare

        // compare returned value and expected value, in case of error print error message
        Assert.assertTrue("Expected number is " + numberExpected + " and not " + a ,
                a == numberExpected );
    }
    public int numberToTest(int b) { // method to define value to compare
        return b;
    }
    public String strToTest(String s) { // method to define value to compare
        return s;
    }
// ---------------------------Тест 2 ---------------------------
//    Сделать класс MainClass (если еще нет).//
//    Сделать в классе MainClass приватное поле класса, которое равно 20 (назвать: class_number),
//    и публичный метод (getClassNumber), который эту переменную возвращает.
////    Сделать класс MainClassTest (если еще нет).//
//    В классе MainClassTest написать тест (назвать testGetClassNumber), который проверяет,
//    что метод getClassNumber возвращает число больше 45.
//
    @Test
    public void testGetClassNumber()
    {
        int numIsBiggerThan = this.getClassNumber (); // get returned by method getLocalNumber() value
        int numberToCompare= this.numberToTest(45); // get value to compare

        // compare returned value and expected value, in case of error print error message
        Assert.assertTrue("Error! Number " + numIsBiggerThan  +
                        " is not bigger than " + numberToCompare ,
                numIsBiggerThan > numberToCompare );
    }

    // ---------------------------Тест 3 ---------------------------
//     Сделать в классе MainClass приватное поле класса, которое равно строке “Hello, world”
    //            (назвать: class_string), и публичный метод (назвать: getClassString),
    //            который возвращает строку.
//     Сделать класс MainClassTest (если еще нет).
//     В классе MainClassTest написать тест (назвать testGetClassString), который проверяет,
//     что метод getClassNumber возвращает строку, в которой есть подстрока “hello” или “Hello”,
//     если нет ни одной из подстрок - тест падает.
        @Test
        public void testGetClassString()
        {
           String stringToCheck = getClassString();
           String[] stringsToCheck = {"Hello", "hello", "test"};
           int resultOfCheck = this.checkString(stringsToCheck, stringToCheck);
            System.out.println(resultOfCheck);
            Assert.assertTrue("In the returned string '" + stringToCheck + "' there aren't such substrings: "
                            + Arrays.toString(stringsToCheck) ,
                    resultOfCheck == 1 );
        }
}
