package com.dbit.app;

import com.dbit.app.repositories.EntityManagerHelper;
import com.dbit.model.City;
import com.dbit.model.Department;
import com.dbit.model.Employee;
import com.dbit.model.Title;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;

public class Start {
    public static void main(String[] args) {
//        Configuration cfg = new Configuration().configure();
//        SessionFactory sessionFactory = cfg.buildSessionFactory();

        EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();


        Employee employee = em.find(Employee.class, 1);
        printWithPrefix(employee);

        //One-To-Many
//        Department department = em.find(Department.class, 1);
//        printWithPrefix(department);


        //One-To-One
//        Employee employee = em.find(Employee.class, 1);

//        printWithPrefix(employee);

//        em.remove(title);

//        Employee newEmployee = new Employee()
//                .withName("Джон")
//                .withTitle(new Title()
//                        .withName("CTO"));
//        em.persist(newEmployee);
//
//        TypedQuery<Employee> employeesQuery = em.createQuery("from Employee", Employee.class);
//        List<Employee> employees = employeesQuery.getResultList();
//        employees.stream().forEach(System.out::println);



//        Title managerTitle = new Title().withName("Менеджер");
//        em.persist(managerTitle);
//
//        TypedQuery<Title> oneTitleQuery = em.createQuery("from Title where name = 'Менеджер'", Title.class);
//        Title title = oneTitleQuery.getSingleResult();

//        Employee title = em.find(Employee.class, 1);

//        System.out.println("!!!" + title);

        tx.commit();
        em.close();


//        sessionFactory.close();

//        Session session = sessionFactory.openSession();
//        Transaction tx = session.beginTransaction();
//
//        Title title = session.find(Title.class, 1);
//        System.out.println("!!!" + title);
//
//        tx.commit();
//        session.close();
    }

    private static void printWithPrefix(Object obj) {
        System.out.println("!!!" + obj);
    }
}
