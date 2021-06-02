package main;

import java.util.*;

public class num_square {
    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        int n;
        System.out.println("请输入行列数:");
        n=s.nextInt();
        for(int i=1;i<=n*n;i++){
            System.out.print(i+" ");
            if(i%n==0){
                System.out.println();
            }
        }
    }
}

