package org.acme.resource;

import org.acme.response.LabseqResponse;
import org.acme.service.LabseqService;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/labseq")
public class LabseqResources {

    @Inject
    LabseqService labSeqService = new LabseqService();

    @GET
    @Path("{value}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response labseq(@PathParam("value") int value) {

        if (value < 0) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Input value must be non-negative.")
                    .build();
        }

        int res = labSeqService.calcSeq(value);

        LabseqResponse labseqResponse = new LabseqResponse(res);

        return Response.ok(labseqResponse).build();
    }
}
