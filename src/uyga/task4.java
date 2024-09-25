package uyga;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class task4 {
    public static void main(String[] args) throws IOException {
        String check1=" ";
        String check2="$";
        String check3="*";
        String check4="&";
        String check5="#";
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        BufferedReader bufferedReader = new BufferedReader(new FileReader("uyga2.txt"));
        String emails = "";
        List<String> list=new ArrayList<>();
        ArrayList<Character> characters = new ArrayList<>();
        int y=0;
        String n="";
        while (bufferedReader.ready()){
            int read = bufferedReader.read();
           n+=(char)read;
            if (y==1000){
           list.add(n);
           n="";

            }
            ++y;
        }
        if (list.size()==0||list.get(list.size()-1)!=n)
        {
            list.add(n);
        }
        File file=new File("uyga3.txt");
        file.createNewFile();


        for (int i = 0; i < list.size(); i++) {
            int finalI = i;

            String j=list.get(i);
            executorService.execute(()->{
                String replace = j.replace(check2, "").replace(check3, "");
                String replace1 = replace.replace(check4, "").replace(check5, "").replaceAll( "\\ {2,}", " ");
                try {
                    Files.writeString(file.toPath(),replace1);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            }
        }
    }

