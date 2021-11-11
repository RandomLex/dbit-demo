package com.dbit.app;

import com.dbit.dto.ProductDto;
import com.dbit.app.repositories.EntityManagerHelper;
import com.dbit.dto.ResultDto;
import com.dbit.model.examples.Product;
import com.dbit.model.examples.ProductType;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

public class Start {
    public static void main(String[] args) {
//        Configuration cfg = new Configuration().configure();
//        SessionFactory sessionFactory = cfg.buildSessionFactory();

        EntityManager em = EntityManagerHelper.getInstance().getEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();



        //select left outer explicit join with dto
//        TypedQuery<ProductType> query = em.createQuery(
//                "select pt from ProductType pt join fetch pt.products", ProductType.class);
//        query.getResultList().forEach(Start::printWithPrefix);


        //select left outer explicit join with dto
//        TypedQuery<ResultDto> query = em.createQuery(
//                "select new com.dbit.dto.ResultDto(p.name, pt.name, p.price) from Product p, ProductType pt where p.productType = pt and p.price > 1000", ResultDto.class);
//        query.getResultList().forEach(Start::printWithPrefix);


        //select left outer explicit join with dto
//        TypedQuery<ResultDto> query = em.createQuery(
//                "select new com.dbit.dto.ResultDto(p.name, pt.name, p.price) from Product p join p.productType pt", ResultDto.class);
//        query.getResultList().forEach(Start::printWithPrefix);

        //select left outer explicit join
//        TypedQuery<ProductType> query = em.createQuery("select pt from Product p join p.productType pt", ProductType.class);
//        query.getResultList().forEach(Start::printWithPrefix);

        //select left outer implicit join
//        TypedQuery<ProductType> query = em.createQuery("select p.productType from Product p", ProductType.class);
//        query.getResultList().forEach(Start::printWithPrefix);

        //Named Query select several fields with filtering by param
//        TypedQuery<ProductDto> query = em.createNamedQuery("byName", ProductDto.class);
//        query.setParameter("name", "HP");
//        query.getResultList().forEach(Start::printWithPrefix);

        //select several fields with filtering by param
//        TypedQuery<ProductDto> query = em.createQuery("select new com.dbit.dto.ProductDto(p.name, p.price) from Product p where p.name = :name", ProductDto.class);
//        query.setParameter("name", "HP");
//        query.getResultList().forEach(Start::printWithPrefix);

        //select several fields with filtering directly shown
//        TypedQuery<ProductDto> query = em.createQuery("select new com.dbit.dto.ProductDto(p.name, p.price) from Product p where p.name like 'A%'", ProductDto.class);
//        query.getResultList().forEach(Start::printWithPrefix);

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> productRoot = query.from(Product.class);
        ParameterExpression<String> nameExpression = cb.parameter(String.class, "name");
        query.select(productRoot).where(cb.like(productRoot.get("name"), nameExpression));
        em.createQuery(query).setParameter("name", "A%").getResultList().forEach(Start::printWithPrefix);

        //select several fields to dto
//        TypedQuery<ProductDto> query = em.createQuery("select new com.dbit.dto.ProductDto(p.name, p.price) from Product p", ProductDto.class);
//        query.getResultList().forEach(Start::printWithPrefix);

//        //select several fields untyped
//        Query query = em.createQuery("select p.name, p.price from Product p");
//        query.getResultList().stream().flatMap(a -> Arrays.stream((Object[]) a)).forEach(Start::printWithPrefix);
//
        //select field
//        TypedQuery<String> query = em.createQuery("select p.name from Product p", String.class);
//        query.getResultList().forEach(Start::printWithPrefix);

//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Product> query = cb.createQuery(Product.class);
//        Root<Product> productRoot = query.from(Product.class);
//        CompoundSelection<Product> name = cb.construct(Product.class, productRoot.get("name"));
//        query.select(name);
//        em.createQuery(query).getResultList().forEach(product -> printWithPrefix(product.getName()));

//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<String> query = cb.createQuery(String.class);
//        Root<Product> productRoot = query.from(Product.class);
//        query.select(productRoot.get("name"));
//        em.createQuery(query).getResultList().forEach(Start::printWithPrefix);




//        //select simple
//        TypedQuery<Product> query = em.createQuery("select p from Product p", Product.class);
//        query.getResultList().forEach(Start::printWithPrefix);

//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Product> query = cb.createQuery(Product.class);
//        Root<Product> productRoot = query.from(Product.class);
//        query.select(productRoot);
//        em.createQuery(query).getResultList().forEach(Start::printWithPrefix);


        //from
//        TypedQuery<Product> query = em.createQuery("from Product", Product.class);
//        query.getResultList().forEach(Start::printWithPrefix);

//        CriteriaBuilder cb = em.getCriteriaBuilder();
//        CriteriaQuery<Product> query = cb.createQuery(Product.class);
//        query.from(Product.class);
//        em.createQuery(query).getResultList().forEach(Start::printWithPrefix);


        //Mapped
        //It doesn't work because Animal is not an Entity
//        em.createQuery("from Animal ", Animal.class).getResultList().forEach(Start::printWithPrefix);

//        Bird eagle = Bird.builder()
//                .origin("Eagle")
//                .flyable(true)
//                .growing("Nested")
//                .build();
//
//        Fish shark = Fish.builder()
//                .origin("Shark")
//                .skeleton("Cartilaginous")
//                .poisoned(false)
//                .build();
//
//        em.persist(eagle);
//        em.persist(shark);

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
