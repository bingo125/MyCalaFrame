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

        Digit(int type, String data) {
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

    private String operator  = "-+*/^()%";

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
        if(numeral.length() != 0){
            pushDigit(0, numeral.toString());
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
      //  String  cmd = "3+4*2/(1-5)^2";
        String  cmd = "(3+4)*2/((1-5)^2/2 )";
        Calc c = new Calc(cmd);
        System.out.println( c.calc());
    }




    private int level(char c){
        switch (c) {
            case '+':
            case '-':
                return  0;
            case '*':
            case '/':
            case '%':
                return  1;
            case '^':
                return  2;
            case '(':
                return  3;
            default:
                return -1;
        }
    }



    public  String calc(){
        Stack<Digit> tmp = new Stack<>();
        Digit d = null;
        while ( (d = data.poll())!= null ){
            if(d.type == 0){
                tmp.push(d);
            }else{
               double d1 = calc(tmp.pop(), tmp.pop(), d);
                tmp.push(new Digit(0, String.valueOf(d1)));
            }
        }
        if (tmp.size() != 1) {
            NumberFormatException e = new NumberFormatException();
            throw e;
        }else{
            return tmp.pop().toString();
        }
    }

    private double calc(Digit digit1, Digit digit2, Digit ops) {
        double f1 = Double.parseDouble(digit2.data);
        double f2 = Double.parseDouble(digit1.data);

        switch (ops.data.charAt(0)) {
            case '+':
                return f1 + f2;
            case '-':
                return f1 - f2;
            case '*':
                return f1 * f2;
            case '/':
                return f1 / f2;
            case '^':
                return Math.pow(f1, f2);
            case '%':
                return f1 % f2;
            default:
                NumberFormatException e = new NumberFormatException();
                throw e;
        }

    }



}
