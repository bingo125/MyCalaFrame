package com.company;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;



public class Calc {
    private Stack<Character> ops;
    private Queue<Digit> data;

    StringBuffer sb ;


    private void pushOperator(char  ch){
        if( !ops.empty()){
            if(ch == ')'){
                while((ch = ops.pop())!= '('){
                    pushDigit(1, String.valueOf(ch));
                }
                return ;
            }
            if(ops.peek() == ch && ch == '^'){
                ops.push(ch);
                return;
            }

            while(!ops.isEmpty()
                    &&ops.peek() != '('
                        &&level(ops.peek()) >= level(ch)){
                pushDigit(1, String.valueOf(ops.pop()));
            }
            ops.push(ch);
        }else{
            ops.push(ch);
        }
    }

    public class Digit{
        private int type;
        private String data;

        public Digit(int type, String data) {
            this.type = type;
            this.data = data;
        }

        @Override
        public String toString() {
            return data;
        }
    }

    private void pushDigit(int type, String str){
        Digit d = new Digit(type, str);
        data.add(d);
    }

    private String operator  = "-+*/^()";

    private  boolean isDigit(char ch){
        boolean ret = false;
        if( Character.isDigit(ch)){
            ret = true;
        }

        if(ch == '.'){
            ret = true;
        }
        return ret;
    }

    private void run(String cmd) {
        StringBuilder numeral = new StringBuilder();
        char[] d = cmd.toCharArray();
        for (int i = 0; i < d.length; i++) {
            if(isDigit(d[i])){
                numeral.append(d[i]);
            }else if(operator.contains(String.valueOf(d[i]))){
                if(numeral.length() != 0){
                    pushDigit(0,numeral.toString());
                    numeral.setLength(0);
                }
                pushOperator(d[i]);

            }
        }
        while(!ops.empty()){
            pushDigit(1, String.valueOf(ops.pop()));
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
//        String  cmd = "2/(1-5)";
//        String  cmd = "3+4*2/(1−5)";
        String  cmd = "3.1+4.1*2.1/(1.1-5.1)^2.1^3.1";
        Calc c = new Calc(cmd);
        System.out.println(c.calc());
        System.out.println(cmd);
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
