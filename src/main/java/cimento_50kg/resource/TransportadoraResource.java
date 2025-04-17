package cimento_50kg.resource;

import cimento_50kg.dto.request.TransportadoraRequestDTO;
import cimento_50kg.dto.response.TransportadoraResponseDTO;
import cimento_50kg.service.TransportadoraService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/transportadoras")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TransportadoraResource {

    private final TransportadoraService service;

    public TransportadoraResource(TransportadoraService service) {
        this.service = service;
    }

    @GET
    public List<TransportadoraResponseDTO> listarTodos() {
        return service.listarTodos();
    }

    @GET
    @Path("{id}")
    public TransportadoraResponseDTO buscarPorId(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @POST
    public Response inserir(TransportadoraRequestDTO dto) {
        service.inserir(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    public Response atualizar(@PathParam("id") Long id, TransportadoraRequestDTO dto) {
        service.atualizar(id, dto);
        return Response.ok().build();
    }

    @DELETE
    @Path("{id}")
    public Response deletar(@PathParam("id") Long id) {
        service.deletar(id);
        return Response.noContent().build();
    }
}
