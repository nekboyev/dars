package uyga;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class task2 {
    public static void main(String[] args) throws IOException {
Lock lock=new ReentrantLock();

        TreeMap<String,Integer> map=new TreeMap<>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        BufferedReader bufferedReader = new BufferedReader(new FileReader("uyga5"));
        StringBuilder text = new StringBuilder();
        List<String> list = new ArrayList<>();

        int y = 0;

        while (bufferedReader.ready()) {
            list.add(bufferedReader.readLine());
            text.append(list.get(y));
            ++y;
        }

        AtomicReference<AtomicInteger> count = new AtomicReference<>(new AtomicInteger(0));
        for (int i = 0; i < list.size(); i++) {
            String j = list.get(i).trim();
            String finalText = text.toString();
            executorService.execute(() -> {
                lock.lock();
                Pattern pattern = Pattern.compile(j);
                Matcher matcher = pattern.matcher(finalText);

             while (matcher.find()){
                 count.get().incrementAndGet();
             }
                map.put(j, count.get().get());
             count.set(new AtomicInteger());
lock.unlock();
            });

        }
       /* for (int i = map.size()-5; i <map.size() ; i++) {
            System.out.println(map.get);
        }*/

        System.out.println("map = " + map);

    }
}
