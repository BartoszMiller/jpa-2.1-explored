package explored.single;

import explored.bootstrap.Context;
import explored.entity.single.Person;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import static org.junit.Assert.assertNotNull;

public class SingleEntityCrudWithManualTransactionManagement {

    private EntityManager em = Context.getEntityManager();

    @Test
    public void crudPerson() {

        Long id = createPerson();
        readPerson(id);
        String newName = updatePersonName(id);
        deletePersonByNewName(newName);
    }

    private Long createPerson() {

        Person person = new Person("John", "Smith");

        em.getTransaction().begin(); // transaction required for insert, update, delete
        em.persist(person);
        em.getTransaction().commit();

        return person.getId();
    }

    private void readPerson(Long id) {

        Person person = em.find(Person.class, id); // transaction not required
        assertNotNull(person);
    }

    private String updatePersonName(Long id) {

        String newName = "Gregory";

        em.getTransaction().begin();
        Person person = em.find(Person.class, id);
        person.setName(newName);
        em.getTransaction().commit();

        return newName;
    }

    private void deletePersonByNewName(String name) {

        em.getTransaction().begin();

        Query query = em.createQuery("from Person where name = :name");
        query.setParameter("name", name);
        Object singleResult = query.getSingleResult();
        em.remove(singleResult);

        em.getTransaction().commit();
    }
}
