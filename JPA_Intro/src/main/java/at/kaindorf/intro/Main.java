package at.kaindorf.intro;

import at.kaindorf.intro.pojos.Address;
import at.kaindorf.intro.pojos.SchoolClass;
import at.kaindorf.intro.pojos.Student;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PU_JPA_Intro");
        //Verwaltet die Entities
        EntityManager em = emf.createEntityManager();
        Student student = new Student("5DHIF",1L,"Nico", "Baumann", LocalDate.now());
        //Objekt kommt in den "Persistence context (noch nicht in der Datenbank)
        //em.persist(student);
        Address address = new Address("Kaindorf", "Grazerstr", "220");
        student.setAddress(address);
        address.setStudent(student);

        Student student2 = new Student("5DHIF",2L,"Adrian", "Berner", LocalDate.now().minusDays(1000));
        //em.persist(student2);
        Address address2 = new Address("Graz", "Herrengasse", "10");
        student2.setAddress(address2);
        address2.setStudent(student2);

        SchoolClass sc = new SchoolClass("5DHIF");
        sc.addStudent(student);
        sc.addStudent(student2);
        em.persist(sc);

        //Mit transaction wird das Objekt in die Datenbank gespeichert.
        em.getTransaction().begin();
        em.getTransaction().commit();
        //em.detach(student); --> Hier wird das Objekt aus dem Persistence Context herausgenommen
        //em.remove(student); --> Hier wird das Objekt aus dem Persistence Context gelöscht

        //JPQL-Queries:
        TypedQuery<Student> typedQuery = em.createQuery("SELECT s FROM Student s", Student.class);
        List<Student> students = typedQuery.getResultList();
        students.stream().forEach(System.out::println);

        TypedQuery<Address> typedQuery2 = em.createNamedQuery("Address.GetAll", Address.class);
        typedQuery2.setParameter("street", "%str%");
        List<Address> addresses = typedQuery2.getResultList();
        addresses.stream().forEach(System.out::println);

        //Mit joins
        TypedQuery<Address> typedQuery3 = em.createNamedQuery("Address.GetByClassname", Address.class);
        typedQuery3.setParameter("classname", "5DHIF");
        List<Address> addresses2 = typedQuery3.getResultList();
        addresses2.stream().forEach(System.out::println);

        Query query = em.createNamedQuery("Student.CountByClassname");
        Long number = (Long) query.getSingleResult();
        System.out.println("Number of students " + number);
        em.close();
        emf.close();
    }
}
