
public class MainClass
{
    private int class_number = 20;
    private String  class_string = "Hello world!";

// ---------------------------Задание 3 ---------------------------
    public String getClassString()
    {
        return this.class_string.toLowerCase();
    }
// ------ Знаю, что перемудрила, но интересно было разобраться с массивами в Java ;-)
    public int checkString(String [] array, String strs) {
        int check = 0;

        for(int i=0; i<array.length; i++ ) {
            System.out.println(array[i]);
            String itemToLowerCase = array[i].toLowerCase();
            if(strs.contains(itemToLowerCase) ) {
                check = 1;
                System.out.println(check);
            }
        }
        System.out.println(check);
        return check;
    }
// ---------------------------Задание 2 ---------------------------

    public int getClassNumber()
    {
        return this.class_number;
    }
// ---------------------------Задание 1 ---------------------------
    public int getLocalNumber()
    {
        return 14;
    }

}
