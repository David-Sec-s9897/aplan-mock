package tp_outages_client;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;


@Path("/outages")
@RegisterRestClient(baseUri = "http://37.221.248.104:8080/remit-outages/rest/")

public interface ExtensionsService {

    @POST
    @Consumes(MediaType.APPLICATION_XML)
    Response sendOutage(byte[] document);
}