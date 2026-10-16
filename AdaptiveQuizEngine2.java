import java.io.*;
import java.util.*;

abstract class Question {
    private String q, a;

    public Question(String q, String a) {
        this.q = q;
        this.a = a;
    }

    public String getQ() {
        return q;
    }

    public String getA() {
        return a;
    }

    public abstract void show();

    public boolean check(String in) {
        return a.equalsIgnoreCase(in.trim());
    }
}

class MCQ extends Question {
    private List<String> opts;

    public MCQ(String q, List<String> opts, String a) {
        super(q, a);
        this.opts = opts;
    }

    @Override
    public void show() {
        System.out.println(getQ());
        for (int i = 0; i < opts.size(); i++)
            System.out.println((i + 1) + ". " + opts.get(i));
    }
}