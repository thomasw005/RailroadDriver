/* NAME HERE 
   Dr. Steinberg
   COP3503 Spring 2025
   Programming Assignment 5
*/



public class RailroadDriver
{
    public static void main(String[] args) throws Exception
    {
        System.out.println("***************TEST CASE 1***********************");
        Railroad test = new Railroad(4, "railroad1.txt");

        System.out.println(test.buildRailroad());

        System.out.println("***************TEST CASE 2***********************");
        Railroad test2 = new Railroad(10, "railroad2.txt");

        System.out.println(test2.buildRailroad());

        System.out.println("***************TEST CASE 3***********************");
        Railroad test3 = new Railroad(6, "railroad3.txt");

        System.out.println(test3.buildRailroad());

        System.out.println("***************TEST CASE 4***********************");
        Railroad test4 = new Railroad(9, "railroad4.txt");

        System.out.println(test4.buildRailroad());

        System.out.println("***************TEST CASE 5***********************");
        Railroad test5 = new Railroad(21, "railroad5.txt");

        System.out.println(test5.buildRailroad());
    }
}
