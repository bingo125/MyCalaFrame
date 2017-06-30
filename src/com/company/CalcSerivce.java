package com.company;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/6/30 0030.
 */
public class CalcSerivce {
    private boolean isExit = false;
    private String key_num = "1234567890.+*/-()^3^2";
    private String key_ops = "=<==1/xC";

    public String onCmd(String cmd, String in) {

        StringBuilder sb = new StringBuilder();
        if (!in.equals("0")) {
            if (isExit == true) {
                isExit = false;
                sb = new StringBuilder("");
            } else {
                sb = new StringBuilder(in);
            }

        } else {
            sb = new StringBuilder("");
        }

        if (key_num.contains(cmd)) {
            sb.append(cmd);

        } else if (key_ops.contains(cmd)) {
            switch (cmd) {
                case "<==":
                    sb = new StringBuilder(in);
                    if (sb.length() > 2) {
                        String str = sb.substring(sb.length() - 2, sb.length());
                        if (str.compareTo("^2") == 0 || str.compareTo("^3") == 0) {

                            sb.delete(sb.length() - 2, sb.length());
                        } else {
                            sb.delete(sb.length() - 1, sb.length());
                        }

                    } else {
                        sb.delete(sb.length() - 1, sb.length());

                    }

                    break;
                case "=":
                    Calc c = new Calc(in);
                    try {
                        BigDecimal bg = new BigDecimal(c.calc());
                        sb = new StringBuilder(bg.toPlainString());
                    } catch (Exception e1) {
                        sb = new StringBuilder("非法输入");
                    }

                    isExit = true;
                    break;

                case "C":
                    sb.insert(0, "0");
                    sb.setLength(1);
                    isExit = true;
                    break;
            }
        }
        return sb.toString();
    }

}
