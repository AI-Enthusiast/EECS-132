import java.lang.reflect.Method;

/**
 * Created by Cormac on 12/6/2016.
 */
public class PreLab14 {

    public static void preLab(){
        Object word = new String("wOrD");
        Class<?> classy = word.getClass();
        Method[] classyMethods = classy.getMethods();
        StringBuilder builder = new StringBuilder();
        builder.append("Methods: ");
        for(int index = 0; index < classyMethods.length; index++){
            builder.append(classyMethods[index] + " ");
        }
        System.out.println(builder.toString());
    }
}
