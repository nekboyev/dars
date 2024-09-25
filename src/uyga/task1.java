package uyga;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Stream;

public class task1 {
    public static void main(String[] args) throws IOException {
        List<Path> uy = Files.walk(Path.of("uy")).toList();
        System.out.println(uy.size());
        ForkJoinPool forkJoinPool=new ForkJoinPool();
     //   recur recur = new recur(uy,".txt",3,0,uy.size());
        recur recur = new recur(uy,".txt",3);
        List<Path> invoke = forkJoinPool.invoke(recur);
        for (int i = 0; i < invoke.size(); i++) {
            System.out.println(invoke.get(i));
        }


    }
}
