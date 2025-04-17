package cimento_50kg.resource;

import cimento_50kg.dto.request.PedidoRequestDTO;
import cimento_50kg.dto.response.PedidoResponseDTO;
import cimento_50kg.service.PedidoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    PedidoService service;

    @GET
    public List<PedidoResponseDTO> listar() {
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscar(@PathParam("id") Long id) {
        var dto = service.buscarPorId(id);
        return dto != null ? Response.ok(dto).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response criar(PedidoRequestDTO dto) {
        return Response.status(Response.Status.CREATED).entity(service.criar(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, PedidoRequestDTO dto) {
        var atualizado = service.atualizar(id, dto);
        return atualizado != null ? Response.ok(atualizado).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        return service.deletar(id) ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}