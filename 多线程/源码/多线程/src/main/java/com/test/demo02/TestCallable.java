package com.test.demo02;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

//练习 thread  ，实现多线程同步下载图片
public class TestCallable implements Callable<String> {
    private String url;  //图片地址
    private String name;  //保存的文件名  要加后缀名！

    public TestCallable(String url, String name) {
        this.url = url;
        this.name= name;
    }

    @Override
    public String call() {
        WebDownLoader webDownLoader=new WebDownLoader();
        webDownLoader.downLoader(url,name);
        System.out.println("下载了文件名为:"+name);
        return "下载了文件名为:"+name;
    }
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable thread1=new TestCallable("https://wx3.sinaimg.cn/mw690/0026VLyFgy1gukfxaiw37j635s2dc1ky02.jpg","早安.jpg");
        TestCallable thread2=new TestCallable("https://wx4.sinaimg.cn/mw690/0026VLyFly1guk1gauncxj60u00jztec02.jpg","雨夜.jpg");
        TestCallable thread3=new TestCallable("https://wx1.sinaimg.cn/mw690/0026VLyFgy1gujl757wejj60tv0tvwoc02.jpg","o(=•ェ•=)m.jpg");

        //创建线程池
        ExecutorService service= Executors.newFixedThreadPool(3);

        //提交服务执行
        Future<String> res1= service.submit(thread1);
        Future<String> res2= service.submit(thread2);
        Future<String> res3= service.submit(thread3);

        //获取返回值
        String r1= res1.get();
        String r2= res2.get();
        String r3= res3.get();
        System.out.println("最后的返回值按顺序输出");
        System.out.println(r1);
        System.out.println(r2);
        System.out.println(r3);

        //关闭服务
        service.shutdownNow();
    }
}


//下载器
class WebDownLoader{
    public void downLoader(String url,String name){
        try {
            FileUtils.copyURLToFile(new URL(url),new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO异常，DownLoader出现问题！");
        }
    }
}

