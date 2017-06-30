package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/6/29 0029.
 */
public class CalcFrame extends JFrame {

    private JTextField field;
    private CalcSerivce service = null;

    private String[] menu = {
            "%", "ce", "C", "<==", "%",
            ")", "7", "8", "9", "*",
            "^2", "4", "5", "6", "-",
            "^3", "1", "2", "3", "+",
            "1/x", "(", "0", ".", "="

    };

    public CalcFrame() throws HeadlessException {
        setTitle("计算器");
//        getContentPane().setLayout(new BorderLayout(10, 1));
        initUi();
        service = new CalcSerivce();
    }

    private void initUi() {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(10, 1));
        panel.add(getText(), BorderLayout.NORTH);
        panel.setPreferredSize(new Dimension(600, 200));
        JPanel keyPanel = new JPanel();
        keyPanel.setLayout(new GridLayout(5, 5, 3, 5));

        for (JButton jb : getButton(menu)) {
            keyPanel.add(jb);
        }
        panel.add(keyPanel, BorderLayout.CENTER);
        this.add(panel);
    }

    public static void main(String args[]) {
        CalcFrame f = new CalcFrame();
        f.pack();
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public java.util.List<JButton> getButton(String[] representer) {

        java.util.List<JButton> buttonList = new ArrayList<JButton>();
        for (String str :
                representer) {
            JButton jb = new JButton(str);
            jb.setBackground(Color.lightGray);
            jb.addActionListener(new ActionListener() {
                StringBuilder sb;

                @Override
                public void actionPerformed(ActionEvent e){
                    String str =  service.onCmd(e.getActionCommand(), getText().getText());
                    if (str != null) {
                        getText().setText(str);
                    }
                }
            });
            buttonList.add(jb);
        }
        return buttonList;
    }

    public JTextField getText() {
        if (field == null) {
            field = new JTextField("0");
        }
        field.setBackground(Color.WHITE);
        field.setMinimumSize(new Dimension(20, 70));
        return field;
    }
}
