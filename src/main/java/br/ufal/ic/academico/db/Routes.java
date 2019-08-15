package br.ufal.ic.academico.db;

import br.ufal.ic.academico.model.Student;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.dropwizard.hibernate.UnitOfWork;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Path("model")
@Slf4j
@RequiredArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
public class Routes {
    private final Database database;

//    @GET
//    @UnitOfWork
//    public Response getAll(){
//        log.info("getAll");
//        return Response.ok(database.list()).build();
//    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Response getById(@PathParam("id") long id) {
        log.info("getById: id={}", id);

        Student p = database.get(Student.class, id);

        return Response.ok(p).build();
    }

    @POST
    @UnitOfWork
    @Consumes("application/json")
    public Response save(StudentDTO entity) {
        log.info("Save: {}", entity);

        Student s = new Student(entity.getName(), entity.getCpf());
        s.setCredit(entity.getCredits());

        return Response.ok(database.persist(Student.class, s)).build();
    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    @Consumes
    public Response update(@PathParam("id") Long id, StudentDTO entity) {
        log.info("update: id={}, {}", id, entity);
        return Response.ok(entity).build();
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public Response delete(@PathParam("id") Long id) {
        log.info("delete: id={}", id);

        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Getter
    @RequiredArgsConstructor
    @AllArgsConstructor
    @ToString
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class StudentDTO {
        private String name;
        private String cpf;
        private int credits;
    }
}
