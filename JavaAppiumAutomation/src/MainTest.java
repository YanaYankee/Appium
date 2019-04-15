
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

    public class MainTest extends CoreTestCase
    {
        @Before // executes before each test

        public void textStartTest()
        {
            System.out.println("Start test");
        }
        @Test

        public void textFirstTest()
        {
            System.out.println("First test");
        }
        @Test

        public void textSecondTest()
        {
            System.out.println("Second test");
        }
        @After// executes after each test

        public void textFinishTest()
        {
            System.out.println("Finish test");
        }


        @Test

        public void firstTest()
        {
        int expected1 = 20;
        int actual1 = 5*5;

        Assert.assertTrue("5*5=25", actual1 == expected1); //instead of if, BETTER

        }

        @Test

        public void secondTest()
        {
            int expected = 20;
            int actual = 5*5;

            if (actual != expected)
            {
                Assert.fail ("5*5 != 25");
            }

            // this.assertFail();
        }



        private void assertFail()
        {
            Assert.fail("This message will be printed");//error message added (
            // 1. real problem, 2. not stable tests, 3. infrastructure fails (connection problems to DB)

        }
}
