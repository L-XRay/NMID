package cn.com;

import java.text.SimpleDateFormat;
import java.util.*;
import java.lang.*;

public class ID
{
    public static void main(String[] args)
    {
        Scanner s = new Scanner(System.in);
        System.out.println("请输入身份证号:");
        String id = s.nextLine();

        if(id.length()!=18)
        {
            System.out.println("0000-00-00");

        }
        else
        {
            int n=0;
            for(int i=0;i<17;i++)
            {
                if(id.charAt(i)>='0'&&id.charAt(i)<='9')
                {
                    n++;
                }
                else
                    break;
            }

            if(n<=16||!(id.charAt(17)>='0'&&id.charAt(17)<='9'||id.charAt(17)>='a'&&id.charAt(17)<='z'))
                System.out.println("0000-00-00");

            String birth = id.substring(6, 14);

            String flag1 = "yyyyMMdd" ;
            String flag2 = "yyyy-MM-dd" ;

            SimpleDateFormat birth1 = new SimpleDateFormat(flag1) ;
            SimpleDateFormat birth2 = new SimpleDateFormat(flag2) ;
            Date d = null ;
            Date today =new Date();
            try{
                d = birth1.parse(birth) ;   // 将给定的字符串中的日期提取出来
            }catch(Exception e){            // 如果提供的字符串格式有错误，则进行异常处理
                e.printStackTrace() ;       // 打印异常信息
            }
            if(d.after(today))
            {
                System.out.println("0000-00-00");
            }
            else
            {
                System.out.println(birth2.format(d)) ;
            }

            //校验算法
            int sum=0,mod;
            int [] k= {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};
            for(int j=0;j<17;j++)
            {
                sum+=k[j]*Integer.valueOf(id.substring(j,j+1));
            }
            mod=sum%11;
            String mods="10x98765432";
            if(id.charAt(17)==mods.charAt(mod))
                System.out.println(birth2.format(d)) ;
            else
                System.out.println("0000-00-00");
        }
    }
}
