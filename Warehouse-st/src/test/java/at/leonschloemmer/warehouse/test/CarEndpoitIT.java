package at.leonschloemmer.warehouse.test;

import org.junit.Before;
import org.junit.Test;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CarEndpoitIT {

    private Client client;
    private WebTarget target;

    @Before
    public void setup(){
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/warehouse/rs/cars/");
    }

    @Test
    public void test01_getAllCars() {
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void test02_getCarById() {
        Response response = target.path("car").queryParam("id", 1).request(MediaType.APPLICATION_JSON).get();
        JsonObject car = response.readEntity(JsonObject.class);
        assertThat(car.getString("make"), is("Lamborghini"));
    }

    @Test
    public void test03_newCar() {
        JsonObjectBuilder carBuilder = Json.createObjectBuilder();
        carBuilder.add("make", "Lamborghini");
        carBuilder.add("model", "Gallardo");
        carBuilder.add("horsepower", 560);
        carBuilder.add("value", 100000);
        JsonObject car = carBuilder.build();
        Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(car.toString(), MediaType.APPLICATION_JSON));
        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void test04_updateCar() {
        JsonObjectBuilder carBuilder = Json.createObjectBuilder();
        carBuilder.add("make", "Lamborghini");
        carBuilder.add("model", "Huracan");
        carBuilder.add("horsepower", 620);
        carBuilder.add("value", 240000);
        JsonObject car = carBuilder.build();
        Response response = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(car.toString(), MediaType.APPLICATION_JSON));
        assertThat(response.getStatus(), is(200));
        car = response.readEntity(JsonObject.class);
        assertThat(car.getInt("horsepower"), is(620));
    }

}
