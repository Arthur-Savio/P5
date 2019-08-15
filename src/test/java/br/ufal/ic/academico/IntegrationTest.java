package br.ufal.ic.academico;

import br.ufal.ic.academico.db.Routes;
import br.ufal.ic.academico.model.Student;
import br.ufal.ic.academico.db.Database;
import ch.qos.logback.classic.Level;
import io.dropwizard.logging.BootstrapLogging;
import io.dropwizard.testing.ResourceHelpers;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import java.util.List;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.mock;

/**
 *
 * @author willy
 */
@ExtendWith(DropwizardExtensionsSupport.class)
public class IntegrationTest {
    
    
    static {
        BootstrapLogging.bootstrap(Level.DEBUG);
    }

    private Database dao = mock(Database.class);

    private final Routes routes = new Routes(dao);

    public static DropwizardAppExtension<ConfigApp> RULE = new DropwizardAppExtension(AcademicoApp.class,
            ResourceHelpers.resourceFilePath("config-test.yml"));
    
    @Test
    public void testSave() {
        
        Student s = new Student("p1", "0000");
        
        Student saved = RULE.client().target(
             String.format("http://localhost:%d/%s/exemplos", RULE.getLocalPort(), "academicotest"))
            .request()
            .post(Entity.json(s), Student.class);

        assertNotNull(saved.getId());
        
        List<Student> list = RULE.client().target(
             String.format("http://localhost:%d/%s/exemplos", RULE.getLocalPort(), "academicotest"))
            .request()
            .get(new GenericType<List<Student>>() {});

        assertEquals(1, list.size());
    }
    
    @Test
    public void testList() {
        
        List<Student> saved = RULE.client().target(
             String.format("http://localhost:%d/%s/exemplos", RULE.getLocalPort(), "academicotest"))
            .request()
            .get(new GenericType<List<Student>>() {});

        assertEquals(0, saved.size());
    }
}
