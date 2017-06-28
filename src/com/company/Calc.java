package com.company;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static sun.misc.PostVMInitHook.run;

/**
 * Created by Administrator on 2017/6/28 0028.
 */
public class Calc {
    private Stack<Character> ops;
    private Queue<String> data;

    StringBuffer sb ;


    private void pushOps(char ch){
        if( !ops.empty()){
            if(ch == ')'){
                while((ch = ops.pop())!= '('){
                    data.add(String.valueOf(ch));
                }
                return ;
            }

            while(level(ops.peek()) > level(ch)){

            }
        }else{
            ops.push(ch);
        }
    }

    private void run(String cmd) {
        StringBuffer tmp = new StringBuffer();
        char[] d = cmd.toCharArray();
        for (int i = 0; i < d.length; i++) {
            if(Character.isDigit(d[i])){
                tmp.append(d[i]);
            }else if( d[i] == '.'){
                tmp.append('.');
            }else{
                if (tmp.length() > 0) {
                    double shu =Double.parseDouble(tmp.toString());
                    data.add(shu);
                }else if (level(d[i]) == -1){
                    System.out.println(" error happned " + d[i]);
                    break;
                }else{
                    while(level(d[i]) > level(d))
                }
            }
        }

    }
    class  data{
        public int type ;
        public Object obj;
    }

    public Calc(String cmd) {
        initatial();
        run(cmd);
    }

    private void initatial() {
        ops = new Stack<>();
        data = new LinkedList<>();
        sb = new StringBuffer();
    }

    public static void main(String[] args) {
        String  cmd = "3+4*2/(1−5)^2^3";
        Calc c = new Calc(cmd);
        System.out.println(c.calc());
    }

    private double calc() {
        return 0;
    }


    private int level(char c){
        switch (c) {
            case '+':
            case '-':
                return  0;
            case '*':
            case '/':
                return  1;
            case '^':
                return  2;
            case '(':
                return  3;
            default:
                return -1;
        }
    }

}
