import com.example.kj.Car;

public class CarCreator {
    public Car createSedan( long carId, String registration ) {
        return new Car( carId, registration, Car.CarType.SEDAN ) ;
    }
}