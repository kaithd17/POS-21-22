package at.kaindorf.inheritance.database;

import at.kaindorf.inheritance.pojos.Car;
import at.kaindorf.inheritance.pojos.Truck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class InitDB {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private TruckRepository truckRepository;

    @PostConstruct
    public void init(){
        carRepository.save(new Car("Volvo","electric",5));
        truckRepository.save(new Truck("Mercedes",26_000.0,340));
    }
}
