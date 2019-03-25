package at.leonschloemmer.warehouse.test;

import org.junit.Before;
import org.junit.Test;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.ResourceBundle;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class GETEndpointsIT {

    private Client client;
    private WebTarget target;

    @Before
    public void setup(){
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/warehouse/rs/");
    }

    @Test
    public void test01_getAllWarehouses() {
        Response response = target.path("warehouses").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void test02_getWarehouseByLocation() {
        Response response = target.path("warehouses").queryParam("location", "london").request(MediaType.APPLICATION_JSON).get();
        JsonArray warehouses = response.readEntity(JsonArray.class);
        JsonObject warehouse = warehouses.getJsonObject(0);
        assertThat(warehouse.getInt("id"), is(1));
    }

    @Test
    public void test03_getWarehouseById() {
        Response response = target.path("warehouses").queryParam("id", 1).request(MediaType.APPLICATION_JSON).get();
        JsonObject warehouse = response.readEntity(JsonObject.class);
        assertThat(warehouse.getInt("id"), is(1));
    }

    @Test
    public void test04_getAllCars() {
        Response response = target.path("cars").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void test05_getCarById() {
        Response response = target.path("cars").queryParam("id", 1).request(MediaType.APPLICATION_JSON).get();
        JsonObject car = response.readEntity(JsonObject.class);
        assertThat(car.getString("make"), is("Lamborghini"));
    }

    @Test
    public void test06_getAllManagers() {
        Response response = target.path("managers").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void test07_getManagerById() {
        Response response = target.path("managers").queryParam("id", 2).request(MediaType.APPLICATION_JSON).get();
        JsonObject manager = response.readEntity(JsonObject.class);
        int id = manager.getInt("id");
        assertThat(id, is(2));
    }

    @Test
    public void test08_getAllCustomers() {
        Response response = target.path("customers").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void test09_getCustomerById() {
        Response response = target.path("customers").queryParam("id", 1).request(MediaType.APPLICATION_JSON).get();
        JsonObject customer = response.readEntity(JsonObject.class);
        assertThat(response.getStatus(), is(200));
        assertThat(customer.getInt("id"), is(1));
    }

    @Test
    public void test10_getAllStorages() {
        Response response = target.path("storages").request(MediaType.APPLICATION_JSON).get();
        assertThat(response.getStatus(), is(200));
    }

    @Test
    public void test11_getStorageById() {
        Response response = target.path("storages").queryParam("id", 1).request(MediaType.APPLICATION_JSON).get();
        JsonObject storage = response.readEntity(JsonObject.class);
        assertThat(response.getStatus(), is(200));
        assertThat(storage.getInt("id"), is(1));
    }

}
