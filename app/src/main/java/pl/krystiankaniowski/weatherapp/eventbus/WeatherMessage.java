package pl.krystiankaniowski.weatherapp.eventbus;

public class WeatherMessage {

    private String city;
    private double temperature;

    public WeatherMessage(String city, double temperature) {
        this.city = city;
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }

    public String getCity() {
        return city;
    }

}
