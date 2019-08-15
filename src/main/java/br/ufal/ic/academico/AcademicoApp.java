package br.ufal.ic.academico;

import br.ufal.ic.academico.db.Routes;
import br.ufal.ic.academico.model.*;
import br.ufal.ic.academico.db.Database;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;

@Slf4j
public class AcademicoApp extends Application<ConfigApp> {
    public static void main(String[] args) throws Exception {
        new AcademicoApp().run(args);
    }

    @Override
    public void initialize(Bootstrap<ConfigApp> bootstrap) {
        log.info("initialize");
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(ConfigApp config, Environment environment) {
        SessionFactory sessionFactory = hibernate.getSessionFactory();
        final Database db = new Database(sessionFactory);
//        final Routes routes = new Routes(db);
//
//        environment.jersey().register(routes);

        sessionFactory.getCurrentSession().beginTransaction();

        db.persist(Course.class, new Course("Ciência da Computação"));
        db.persist(Department.class, new Department("IC"));
        db.persist(Drca.class, new Drca());
        db.persist(Offer.class, new Offer());
        db.persist(Secretariat.class, new Secretariat("GRADUATE"));
        db.persist(Student.class, new Student("Arthur", "05492927485"));
        db.persist(Subject.class, new Subject("P5"));
        db.persist(Teacher.class, new Teacher("Willy"));

        sessionFactory.getCurrentSession().getTransaction().commit();

        System.out.println("chegouuuuuuuuuuuu");
    }

    private final HibernateBundle<ConfigApp> hibernate = new HibernateBundle<ConfigApp>(
            Course.class,
            Department.class,
            Drca.class,
            Offer.class,
            Secretariat.class,
            Student.class,
            Subject.class,
            Teacher.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(ConfigApp configuration) {
            return configuration.getDatabase();
        }
    };
}
