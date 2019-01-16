
package com.example.marjanraad.weather.weatherpack;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Atmosphere {

    @SerializedName("humidity")
    @Expose
    private float humidity;
    @SerializedName("visibility")
    @Expose
    private float visibility;
    @SerializedName("pressure")
    @Expose
    private Double pressure;
    @SerializedName("rising")
    @Expose
    private float rising;

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public float getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public float getRising() {
        return rising;
    }

    public void setRising(Integer rising) {
        this.rising = rising;
    }

}
