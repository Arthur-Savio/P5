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

    public <T> T persist(Class<T> clazz, T entity) throws HibernateException {
        return (T) super.persist(entity);
    }

    public <T> T get(Class<T> clazz, Serializable id) throws HibernateException {
        log.info("Getting object: id={}", id);
        return (T) super.get(id);
    }

    public <T> List<Object> list(Class<T> clazz) throws HibernateException {
        log.info("Getting Objects: ");
        return super.list(query("SELECT * FROM " + clazz) );
    }

    //Student Query

    public Student persist(Student student) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void deleteStudent(Long id) {
        log.info("Delete the Student with id: " + id);
        query("DELETE FROM Student WHERE ID = " + id);
    }

    public void updateStudentName(Long id, String name) {
        log.info("Update Student Name");
        query("UPDATE Student SET name = " + name + "WHERE id = " + id);
    }

    public void updateStudentCpf(Long id, String cpf) {
        log.info("Update Student CPF");
        query("UPDATE Student SET cpf = " + cpf + "WHERE id = " + id);
    }

    public void updateStudentYearEntry(Long id, int year) {
        log.info("Update Student Year Entry");
        query("UPDATE Student SET yearEntry = " + year + "WHERE id = " + id);
    }

    public void updateStudentCredit(Long id, int credit) {
        log.info("Update Student Credit");
        query("UPDATE Student SET credit = " + credit + "WHERE id = " + id);
    }

    // Teacher Query

    public void deleteTeacher(Long id) {
        log.info("Delete the Teacher with id: " + id);
        query("DELETE FROM Teacher WHERE ID = " + id);
    }

    public void updateTeacher(Long id, String name) {
        log.info("Update Student name");
        query("UPDATE Teacher SET name = " + name + "WHERE id = " + id);
    }

    public void updateTeacherCpf(Long id, String cpf) {
        log.info("Update Teacher CPF");
        query("UPDATE Teacher SET cpf = " + cpf + "WHERE id = " + id);
    }

}
