package rest;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

@Path("/aplanmock")
public class AplanMockResource {

    public static final Logger LOGGER = Logger.getLogger(AplanMockResource.class.getName());
    @POST
    @Path("SubmissionStatus")
    public Response getSubmissionStatus(byte[] submissionStatus) {
        String submissionStatusString = new String(submissionStatus, StandardCharsets.UTF_8);
        LOGGER.log(Level.INFO,  submissionStatusString);
        return Response.ok().build();
    }
}
