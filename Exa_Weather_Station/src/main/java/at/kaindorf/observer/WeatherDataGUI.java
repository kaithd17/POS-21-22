package at.kaindorf.observer;

import at.kaindorf.beans.Weatherdata;

import javax.swing.*;
import java.awt.*;

public class WeatherDataGUI extends JFrame implements WeatherDataObserver {
    private JTextArea weatherTextArea = new JTextArea();

    public WeatherDataGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
        setVisible(true);
    }

    private void initComponents() {
        setSize(800, 400);
        Container container = getContentPane();
        container.setLayout(new GridLayout(1,1,4,4));
        weatherTextArea.setEditable(false);

        JScrollPane scroll = new JScrollPane (weatherTextArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        container.add(scroll);
        setLocationRelativeTo(null);
    }
    @Override
    public void update(Weatherdata weatherData) {
        weatherTextArea.append(weatherData.toString() + "\n");
    }
}
