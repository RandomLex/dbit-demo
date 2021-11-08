package com.dbit.app;

import com.dbit.app.repositories.EntityManagerHelper;
import com.dbit.model.hierarchy.table.Animal;
import com.dbit.model.hierarchy.table.Bird;
import com.dbit.model.hierarchy.table.Fish;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class Start {
    public static void main(String[] args) {
//        Configuration cfg = new Configuration().configure();
//        SessionFactory sessionFactory = cfg.buildSessionFactory();

        EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();




        //Mapped
        //It doesn't work because Animal is not an Entity
//        em.createQuery("from Animal ", Animal.class).getResultList().forEach(Start::printWithPrefix);

        Bird eagle = Bird.builder()
                .origin("Eagle")
                .flyable(true)
                .growing("Nested")
                .build();

        Fish shark = Fish.builder()
                .origin("Shark")
                .skeleton("Cartilaginous")
                .poisoned(false)
                .build();

        em.persist(eagle);
        em.persist(shark);

        // Many to Many


//        em.createQuery("from Tag ", Tag.class).getResultList().forEach(Start::printWithPrefix);
//        Post newYearPost = Post.builder()
//                .name("Happy New Year")
//                .tags(new HashSet<>())
//                .build();
//
//        Post christmasPost = Post.builder()
//                .name("Marry Christmas")
//                .tags(new HashSet<>())
//                .build();
//
//        Tag holiday = Tag.builder()
//                .name("Holiday")
//                .posts(new HashSet<>())
//                .build();
//
//        Tag favorite = Tag.builder()
//                .name("Favorite")
//                .posts(new HashSet<>())
//                .build();
//
//        newYearPost.addTag(holiday);
//        newYearPost.addTag(favorite);
//
//        christmasPost.addTag(holiday);
//
//        em.persist(newYearPost);
//        em.persist(christmasPost);

        // remove one of post
//        Post newYear = em.find(Post.class, 3L);
//        // -----BEGIN to remove one tag
//        Optional<Tag> favoriteTag = newYear.getTags().stream().filter(tag -> "Holiday".equals(tag.getName())).findAny();
//        favoriteTag.ifPresent(tag -> {
//            newYear.removeTag(tag);
//            em.merge(newYear);
//        });
        // -----END to remove one tag

        // -----BEGIN to remove one post completely
//        newYear.getTags().clear();
//        em.merge(newYear);
//        em.remove(newYear);
        // -----END to remove one post completely

        //One-To-One
//        em.createQuery("from Person ", Person.class).getResultList().forEach(Start::printWithPrefix);

//        Credentials credentials;
//        Person alex = Person.builder()
//                .name("Alex")
//                .credentials(credentials = Credentials.builder()
//                        .login("alex")
//                        .password("asd")
//                        .build())
//                .build();
//        credentials.setPerson(alex);
//        em.persist(alex);

        //Embedded Class
//        Car bmw = Car.builder()
//                .model("BMW")
//                .releaseDate(Date.from(LocalDate.of(2000, 10, 3).atStartOfDay(ZoneId.systemDefault()).toInstant()))
//                .engine(Engine.DIESEL)
//                .audioSystem(AudioSystem.builder()
//                        .musicPower(300)
//                        .musicName("Sony")
//                        .speakers(6)
//                        .build())
//                .build();
//        em.persist(bmw);

        //Enum
//        Car bmw = Car.builder()
//                .model("BMW")
//                .releaseDate(Date.from(LocalDate.of(2000, 10, 3).atStartOfDay(ZoneId.systemDefault()).toInstant()))
//                .engine(Engine.DIESEL)
//                .build();
//
//        Car lada = Car.builder()
//                .model("Lada")
//                .releaseDate(Date.from(LocalDate.of(2001, 12, 5).atStartOfDay(ZoneId.systemDefault()).toInstant()))
//                .engine(Engine.PETROL)
//                .build();
//        em.persist(bmw);
//        em.persist(lada);

        // Complex key
//        em.createQuery("from Report ", Report.class).getResultList().forEach(Start::printWithPrefix);

//        Report mfn = Report.builder()
//                .id(ReportKey.builder()
//                        .name("Налоги")
//                        .type("Декларация")
//                        .build())
//                .recipient("MFN")
//                .build();
//
//        Report gai = Report.builder()
//                .id(ReportKey.builder()
//                        .name("Оплата")
//                        .type("Штраф")
//                        .build())
//                .recipient("GAI")
//                .build();


//        Report mfn = Report.builder()
//                .name("Налоги")
//                .type("Декларация")
//                .recipient("MFN")
//                .build();
//
//        Report gai = Report.builder()
//                .name("Оплата")
//                .type("Штраф")
//                .recipient("GAI")
//                .build();
//        em.persist(mfn);
//        em.persist(gai);


        // Date conversion

//        em.createQuery("from Car ", Car.class).getResultList().forEach(Start::printWithPrefix);

//        Car bmw = Car.builder()
//                .model("BMW")
//                .releaseDate(Date.from(LocalDate.of(2000, 10, 3).atStartOfDay(ZoneId.systemDefault()).toInstant()))
//                .build();
//
//        Car lada = Car.builder()
//                .model("Lada")
//                .releaseDate(Date.from(LocalDate.of(2001, 12, 5).atStartOfDay(ZoneId.systemDefault()).toInstant()))
//                .build();
//        em.persist(bmw);
//        em.persist(lada);


//        Employee employee = em.find(Employee.class, 1);
//        printWithPrefix(employee);
//
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
