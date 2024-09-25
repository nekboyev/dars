package uyga;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class task3 {
    public static void main(String[] args) throws IOException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        BufferedReader bufferedReader = new BufferedReader(new FileReader("uyga4"));
        String emails = "";
        List<String> list=new ArrayList<>();

        int y=0;

        while (bufferedReader.ready()){
       list.add(bufferedReader.readLine());
        }

AtomicInteger count= new AtomicInteger();
        for (int i = 0; i < list.size(); i++) {
            int finalI = i;

            String j=list.get(i).trim();
            executorService.execute(()->{
                Pattern pattern=Pattern.compile("(\\w)(\\d)");
                Matcher matcher = pattern.matcher(j);
                Pattern pattern2=Pattern.compile("\\W");
                Matcher matcher2 = pattern.matcher(j);
                if (!(j.length()>=8&&matcher.find()&&matcher2.find())){
                    System.out.println(j);
                    count.incrementAndGet();
                }
            });

        }
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println(count.get());
    }
}
