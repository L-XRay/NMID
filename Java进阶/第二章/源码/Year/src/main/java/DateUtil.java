public class DateUtil {
    public  boolean isLeapYear(int year) {
        if(year>0&&year<10000&&year % 4 == 0 && year % 100 != 0 || year % 400 == 0&&year%100==0){
            return true;
        }
        else{
            return false;
        }
    }
}
