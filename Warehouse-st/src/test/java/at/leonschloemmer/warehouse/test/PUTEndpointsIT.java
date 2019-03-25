package at.leonschloemmer.warehouse.test;

import org.junit.Before;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class PUTEndpointsIT {

    private Client client;
    private WebTarget target;

    @Before
    public void setup(){
        client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/warehouse/rs/");
    }

}
