package at.kaindorf.console;

import at.kaindorf.beans.*;

import javax.persistence.*;
import javax.xml.bind.JAXB;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataHandler {
    private static EntityManagerFactory emf;
    private static EntityManager em;

    public static void open() {
        emf = Persistence.createEntityManagerFactory("PU_schooldb");
        em = emf.createEntityManager();
    }

    public static void close() {
        em.close();
        emf.close();
    }

    public static void importTables() {
        Path path = Paths.get(System.getProperty("user.dir"), "src", "main", "resources", "schooldata.xml");
        School school = JAXB.unmarshal(path.toString(), School.class);
        List<Teacher> teachers = school.getClassTeachers();
        List<ClassTeacher> classTeachers = new ArrayList<>();

        teachers.forEach(teacher -> {
            Room room;
            if (teacher.getRoom().charAt(0) == '1') {
                room = new Room(
                        teacher.getRoom(),
                        Floor.GROUND
                );
            } else {
                room = new Room(
                        teacher.getRoom(),
                        Floor.FIRST
                );
            }

            Classname classname = new Classname(
                    teacher.getClassName(),
                    Integer.parseInt(teacher.getClassName().charAt(0) + ""),
                    teacher.getSize()
            );

            room.setClassname(classname);
            classname.setRoom(room);

            ClassTeacher classTeacher = new ClassTeacher(
                    teacher.getInitials(),
                    teacher.getFirstname(),
                    teacher.getLastname(),
                    teacher.getTitle()
            );
            classname.setClassTeacher(classTeacher);
            classTeacher.setClassname(classname);
            classTeachers.add(classTeacher);
        });

        em.getTransaction().begin();
        classTeachers.forEach(classTeacher -> {
            em.persist(classTeacher);
        });
        em.getTransaction().commit();
    }

    public static void queries() {
        //Room NamedQueries
        TypedQuery<Room> typedQuery = em.createNamedQuery("Room.findByName", Room.class);
        typedQuery.setParameter("name", "2.7.4");
        List<Room> roomList = typedQuery.getResultList();
        roomList.forEach(System.out::println);
        System.out.println("\n");

        TypedQuery<Room> typedQuery1 = em.createNamedQuery("Room.findAll", Room.class);
        List<Room> roomList1 = typedQuery1.getResultList();
        roomList1.forEach(System.out::println);
        System.out.println("\n");

        TypedQuery<Room> typedQuery2 = em.createNamedQuery("Room.findByFloor", Room.class);
        typedQuery2.setParameter("floor", Floor.GROUND);
        List<Room> roomList2 = typedQuery2.getResultList();
        roomList2.forEach(System.out::println);
        System.out.println("\n");

        Query query = em.createNamedQuery("Room.countAll");
        Long amountOfRooms = (Long) query.getSingleResult();
        System.out.println(amountOfRooms + "\n");

        //ClassTeacher NamedQueries
        TypedQuery<ClassTeacher> classTeacherTypedQuery = em.createNamedQuery("ClassTeacher.findByName", ClassTeacher.class);
        classTeacherTypedQuery.setParameter("lastname", "Taucher");
        List<ClassTeacher> classTeachers = classTeacherTypedQuery.getResultList();
        classTeachers.forEach(System.out::println);
        System.out.println("\n");

        TypedQuery<ClassTeacher> classTeacherTypedQuery1 = em.createNamedQuery("ClassTeacher.findByClassname", ClassTeacher.class);
        classTeacherTypedQuery1.setParameter("classname", "9DHIF");
        List<ClassTeacher> classTeachers1 = classTeacherTypedQuery1.getResultList();
        classTeachers1.forEach(System.out::println);
        System.out.println("\n");

        TypedQuery<ClassTeacher> classTeacherTypedQuery2 = em.createNamedQuery("ClassTeacher.findByGrade", ClassTeacher.class);
        classTeacherTypedQuery2.setParameter("grade", 1);
        List<ClassTeacher> classTeachers2 = classTeacherTypedQuery2.getResultList();
        classTeachers2.forEach(System.out::println);
        System.out.println("\n");

        Query query1 = em.createNamedQuery("ClassTeacher.countAll", ClassTeacher.class);
        Long amountOfTeacher = (Long) query1.getSingleResult();
        System.out.println(amountOfTeacher + "\n");

        //Classname NamedQueries
        TypedQuery<Classname> classnameTypedQuery = em.createNamedQuery("Classname.findByName", Classname.class);
        classnameTypedQuery.setParameter("name", "3DHIF");
        List<Classname> classnames = classnameTypedQuery.getResultList();
        classnames.forEach(System.out::println);
        System.out.println("\n");

        TypedQuery<Classname> classnameTypedQuery1 = em.createNamedQuery("Classname.findAll", Classname.class);
        List<Classname> classnames1 = classnameTypedQuery1.getResultList();
        classnames1.forEach(System.out::println);
        System.out.println("\n");

        TypedQuery<Classname> classnameTypedQuery2 = em.createNamedQuery("Classname.findByFloor", Classname.class);
        classnameTypedQuery2.setParameter("floor", Floor.FIRST);
        List<Classname> classnames2 = classnameTypedQuery2.getResultList();
        classnames2.forEach(System.out::println);
        System.out.println("\n");

        Query query2 = em.createNamedQuery("Classname.countAll", Classname.class);
        query2 = em.createNamedQuery("Classname.countAll", Classname.class);
        Long amountOfClasses = (Long) query2.getSingleResult();
        System.out.println(amountOfClasses + "\n");
    }

    public static void main(String[] args) {
        open();
        importTables();
        queries();
        close();
    }
}
