import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class recur extends RecursiveTask<List> {
    private List<Path> list=new ArrayList<>();
    private String check="";
    private int limit=0;
    private int from=0;
    private int to=list.size();

    public recur(List<Path> list,String check,int limit) {
        this.list=list;
        this.check=check;
        this.limit=limit;
    }

    @Override
    protected List compute() {
        List<Path> newList=new ArrayList<>();
        if (list.size()<limit){
            for (int i = 0; i < list.size(); i++) {
                Pattern pattern=Pattern.compile(check);
                Matcher matcher = pattern.matcher(list.get(i).getFileName().toString());
                if (matcher.find()){
                    newList.add(list.get(i));
                }
            }
            return newList;
        }
        else {
           int middle=(from+to)/2;
            recur recur = new recur(list.subList(from, middle), check, limit);
            recur recur1 = new recur(list.subList( middle,to), check, limit);
            List join = recur.join();
            List join1 = recur1.join();
            join.addAll(join1);
            return join;
        }



    }
}
