package cimento_50kg.resource;

import cimento_50kg.dto.request.NotaFiscalRequestDTO;
import cimento_50kg.dto.response.NotaFiscalResponseDTO;
import cimento_50kg.service.NotaFiscalService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/notas-fiscais")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NotaFiscalResource {

    private final NotaFiscalService service;

    public NotaFiscalResource(NotaFiscalService service) {
        this.service = service;
    }

    @GET
    public List<NotaFiscalResponseDTO> listarTodos() {
        return service.listarTodos();
    }

    @GET
    @Path("{id}")
    public NotaFiscalResponseDTO buscarPorId(@PathParam("id") Long id) {
        return service.buscarPorId(id);
    }

    @POST
    public Response inserir(NotaFiscalRequestDTO dto) {
        service.inserir(dto);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    public Response atualizar(@PathParam("id") Long id, NotaFiscalRequestDTO dto) {
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
