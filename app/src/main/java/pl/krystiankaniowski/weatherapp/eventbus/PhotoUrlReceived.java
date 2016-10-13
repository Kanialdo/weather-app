package pl.krystiankaniowski.weatherapp.eventbus;

/**
 * Created by kryst on 13.10.2016.
 */

public class PhotoUrlReceived {

    private final int cityId;
    private final String link;

    public PhotoUrlReceived(int cityId, String link) {
        this.cityId = cityId;
        this.link = link;
    }

    public int getCityId() {
        return cityId;
    }

    public String getLink() {
        return link;
    }

}
