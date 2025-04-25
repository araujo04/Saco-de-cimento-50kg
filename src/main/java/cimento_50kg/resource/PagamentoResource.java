package cimento_50kg.resource;

import cimento_50kg.dto.request.PagamentoRequestDTO;
import cimento_50kg.dto.response.PagamentoResponseDTO;
import cimento_50kg.service.PagamentoService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/pagamentos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PagamentoResource {

    private final PagamentoService service;

    public PagamentoResource(PagamentoService service) {
        this.service = service;
    }

    @GET
    public List<PagamentoResponseDTO> listarTodos() {
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    public PagamentoResponseDTO buscarPorId(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @POST
    public Response criar(PagamentoRequestDTO dto) {
        PagamentoResponseDTO criado = service.criar(dto);
        return Response.status(Response.Status.CREATED).entity(criado).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, PagamentoRequestDTO dto) {
        PagamentoResponseDTO atualizado = service.atualizar(id, dto);
        return Response.ok(atualizado).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        service.deletar(id);
        return Response.noContent().build();
    }
}
