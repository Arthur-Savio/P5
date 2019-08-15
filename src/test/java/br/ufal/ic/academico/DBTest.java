package br.ufal.ic.academico;

import br.ufal.ic.academico.db.Database;
import br.ufal.ic.academico.model.Student;
import io.dropwizard.testing.junit5.DAOTestExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import lombok.SneakyThrows;
import java.lang.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

/**
 *
 * @author Arthur
 */

@ExtendWith(DropwizardExtensionsSupport.class)
public class DBTest {
    
    public DAOTestExtension dbTesting = DAOTestExtension.newBuilder()
            .addEntityClass(Student.class).build();
    
    private Database dao;

    @BeforeEach
    @SneakyThrows
    public void setUp() {
        System.out.println("setUp");
        dao = new Database(dbTesting.getSessionFactory());
    }

    @Test
    public void testStudentCreate(){
        Student c1 = new Student("c1", "00000");
        Student saved = dbTesting.inTransaction(() -> dao.persist(c1));

        assertAll(() -> assertNotNull(saved),
                () -> assertNotNull(saved.getId()),
                () -> assertEquals(c1.getName(), saved.getName()));
    }

    @Test
    public void testStudentRead(){

    }

    @Test
    public void testStudentUpdate(){

    }

    @Test
    public void testStudentDelete(){
        Student c1 = new Student("c1", "00000");
        dbTesting.inTransaction(() -> dao.persist(c1));
        dbTesting.inTransaction(() -> dao.deleteStudent(c1.getId()));

        assertNull(dbTesting.inTransaction(() -> dao.get(Student.class, c1.getId())));
    }
}
