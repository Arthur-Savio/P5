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
 * @author Willy
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
    public void testCRUD() {

        System.out.println("testCRUD");
        
        Student c1 = new Student("c1", "00000");
        
        Student saved = dbTesting.inTransaction(() -> dao.persist(c1));
        
        assertNotNull(saved);
        assertNotNull(saved.getId());
        assertEquals(c1.getName(), saved.getName());
    }
}
