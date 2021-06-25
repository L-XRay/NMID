import java.util.Locale;
import java.util.ResourceBundle;

public class Mine {
    public static void main(String[] args) {
        Locale myLocale = Locale.getDefault();

        System.out.println(myLocale);
        ResourceBundle bundle = ResourceBundle.getBundle("msg", myLocale);
        System.out.println("name:"+bundle.getString("university"));
        System.out.println("university:"+bundle.getString("name"));

        myLocale = new Locale("en", "NZ");
        System.out.println(myLocale);
        bundle = ResourceBundle.getBundle("msg", myLocale);
        System.out.println("name:"+bundle.getString("university"));
        System.out.println("university:"+bundle.getString("name"));

        myLocale = new Locale("en", "US");
        System.out.println(myLocale);
        bundle = ResourceBundle.getBundle("msg", myLocale);
        System.out.println("name:"+bundle.getString("name"));
        System.out.println("university:"+bundle.getString("university"));
    }

}
