package cimento_50kg.resource;

import cimento_50kg.dto.request.Cimento50kgRequestDTO;
import cimento_50kg.dto.response.Cimento50kgResponseDTO;
import cimento_50kg.service.Cimento50kgService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/sacos-cimento")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Cimento50kgResource {

    private final Cimento50kgService service;

    public Cimento50kgResource(Cimento50kgService service) {
        this.service = service;
    }

    @GET
    public List<Cimento50kgResponseDTO> listarTodos() {
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        var dto = service.buscarPorId(id);
        if (dto == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(dto).build();
    }

    @GET
    @Path("/marca/{marca}")
    public List<Cimento50kgResponseDTO> buscarPorMarca(@PathParam("marca") String marca) {
        return service.buscarPorMarca(marca);
    }

    @POST
    public Response criar(Cimento50kgRequestDTO dto) {
        var criado = service.criar(dto);
        return Response.status(Response.Status.CREATED).entity(criado).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, Cimento50kgRequestDTO dto) {
        var atualizado = service.atualizar(id, dto);
        if (atualizado == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.ok(atualizado).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        boolean deletado = service.deletar(id);
        if (!deletado)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.noContent().build();
    }
}