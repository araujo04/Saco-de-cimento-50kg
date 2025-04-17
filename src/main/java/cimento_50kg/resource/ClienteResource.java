package cimento_50kg.resource;

import cimento_50kg.dto.request.ClienteRequestDTO;
import cimento_50kg.dto.response.ClienteResponseDTO;
import cimento_50kg.service.ClienteService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteResource {

    private final ClienteService service;

    public ClienteResource(ClienteService service) {
        this.service = service;
    }

    @GET
    public List<ClienteResponseDTO> listarTodos() {
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        var dto = service.buscarPorId(id);
        if (dto == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(dto).build();
    }

    @POST
    public Response criar(ClienteRequestDTO dto) {
        var criado = service.criar(dto);
        return Response.status(Response.Status.CREATED).entity(criado).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, ClienteRequestDTO dto) {
        var atualizado = service.atualizar(id, dto);
        if (atualizado == null) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.ok(atualizado).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        boolean deletado = service.deletar(id);
        if (!deletado) return Response.status(Response.Status.NOT_FOUND).build();
        return Response.noContent().build();
    }
}