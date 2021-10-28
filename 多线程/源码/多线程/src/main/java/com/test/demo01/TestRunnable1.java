package com.test.demo01;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TestRunnable1 implements Runnable{
    private String url;  //图片地址
    private String name;  //保存的文件名  要加后缀名！

    public TestRunnable1(String url, String name) {
        this.url = url;
        this.name= name;
    }

    @Override
    public void run() {
        WebDownLoaderByRunnable webDownLoaderByRunnable=new WebDownLoaderByRunnable();
        webDownLoaderByRunnable.downLoader(url,name);
        System.out.println("下载了文件名为:"+name);
    }
    public static void main(String[] args) {
        TestThread1 thread1=new TestThread1("https://wx3.sinaimg.cn/mw690/0026VLyFgy1gukfxaiw37j635s2dc1ky02.jpg","早安.jpg");
        TestThread1 thread2=new TestThread1("https://wx4.sinaimg.cn/mw690/0026VLyFly1guk1gauncxj60u00jztec02.jpg","雨夜.jpg");
        TestThread1 thread3=new TestThread1("https://wx1.sinaimg.cn/mw690/0026VLyFgy1gujl757wejj60tv0tvwoc02.jpg","o(=•ェ•=)m.jpg");

        new Thread(thread1).start();
        new Thread(thread2).start();
        new Thread(thread3).start();

    }
}


//下载器
class WebDownLoaderByRunnable{
    public void downLoader(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，DownLoader出现问题！");
        }
    }
}
