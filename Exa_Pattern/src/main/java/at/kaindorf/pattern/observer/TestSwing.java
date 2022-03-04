package at.kaindorf.pattern.observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TestSwing extends JFrame {

    public TestSwing() throws HeadlessException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        setSize(300, 300);
        Container container = getContentPane();
        container.setLayout(new GridLayout(3,1,4,4));
        JButton btStart = new JButton("Start");
        btStart.addActionListener(this::onStartClick);
        container.add(btStart);
        setLocationRelativeTo(null);
    }

    private void onStartClick(ActionEvent actionEvent) {
        System.out.println("Click");
    }

    public static void main(String[] args) {
        new TestSwing().setVisible(true);
    }
}
