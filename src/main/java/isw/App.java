package isw;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
        try {
            MyUcdQuery myUcdQuery = new MyUcdQuery();
            myUcdQuery.scan();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        //System.out.println( "Hello World!" );
    }
}
