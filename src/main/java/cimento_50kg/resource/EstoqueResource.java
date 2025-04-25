package cimento_50kg.resource;

import cimento_50kg.dto.request.EstoqueRequestDTO;
import cimento_50kg.dto.response.EstoqueResponseDTO;
import cimento_50kg.service.EstoqueService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/estoques")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EstoqueResource {

    private final EstoqueService service;

    public EstoqueResource(EstoqueService service) {
        this.service = service;
    }

    @GET
    public List<EstoqueResponseDTO> listarTodos() {
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    public EstoqueResponseDTO buscarPorId(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @POST
    public Response criar(EstoqueRequestDTO dto) {
        EstoqueResponseDTO criado = service.criar(dto);
        return Response.status(Response.Status.CREATED).entity(criado).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, EstoqueRequestDTO dto) {
        EstoqueResponseDTO atualizado = service.atualizar(id, dto);
        return Response.ok(atualizado).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        service.deletar(id);
        return Response.noContent().build();
    }
}
