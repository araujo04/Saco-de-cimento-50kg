package cimento_50kg.resource;

import cimento_50kg.dto.request.FornecedorRequestDTO;
import cimento_50kg.dto.response.FornecedorResponseDTO;
import cimento_50kg.service.FornecedorService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/fornecedores")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FornecedorResource {

    private final FornecedorService service;

    public FornecedorResource(FornecedorService service) {
        this.service = service;
    }

    @GET
    public List<FornecedorResponseDTO> listarTodos() {
        return service.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") Long id) {
        var dto = service.buscarPorId(id);
        return dto != null ? Response.ok(dto).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response criar(FornecedorRequestDTO dto) {
        var criado = service.criar(dto);
        return Response.status(Response.Status.CREATED).entity(criado).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") Long id, FornecedorRequestDTO dto) {
        var atualizado = service.atualizar(id, dto);
        return atualizado != null ? Response.ok(atualizado).build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        boolean deletado = service.deletar(id);
        return deletado ? Response.noContent().build() : Response.status(Response.Status.NOT_FOUND).build();
    }
}
