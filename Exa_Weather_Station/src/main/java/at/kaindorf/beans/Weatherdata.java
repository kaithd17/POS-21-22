package at.kaindorf.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weatherdata {
    private float temperature;
    private float pressure;
    private float humidity;
    private int windSpeed;
    private int windDirection;
    private LocalDateTime timeData;
}
