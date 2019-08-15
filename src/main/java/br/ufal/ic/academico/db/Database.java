package br.ufal.ic.academico.db;

import br.ufal.ic.academico.model.Student;
import io.dropwizard.hibernate.AbstractDAO;
import java.io.Serializable;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

@Slf4j
public class Database extends AbstractDAO <Object> {
    public Database(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public <T> T get(Class<T> clazz, Serializable id) throws HibernateException {
        log.info("Getting object: id={}", id);
        return (T) super.get(id);
    }

    public List<Object> list() throws HibernateException {
        log.info("Getting Objects: ");
        return super.list(query("from Student"));
    }

    public <T> T persist(Class<T> clazz, T entity) throws HibernateException {
        return (T) super.persist(entity);
    }

    public Student persist(Student student) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
