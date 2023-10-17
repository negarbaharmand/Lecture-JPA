package com.example.lecturejpa.dao;

import com.example.lecturejpa.entity.Student;
import com.example.lecturejpa.exceptions.StudentNotFoundException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

// When working with dao layer we can use @Repository instead of @Component when we use Spring framework for instantiating class
@Repository
public class StudentDaoImpl implements StudentDao {

    //@PersistenceContext is to instantiate the EntityManager interface
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Student persist(Student student) {
        entityManager.persist(student);  //insert into student(id, first_name, last_name, Status, create_date) values(?, ?, ?, ?, ?, ?)
        return student;
    }

    @Override
    public Optional<Student> findById(String id) {
        // select * from student where id = ?
        //find works with only primary key
        Student foundStudent = entityManager.find(Student.class, id);
        return Optional.ofNullable(foundStudent);
    }

    @Override
    public Optional<Student> findByEmail(String email) {
        // We need to create a Hibernate query because find only works for primary key which is id
        return entityManager
                .createQuery("select s from Student s where s.email = :em", Student.class)
                .setParameter("em", email)
                .getResultList()
                .stream()
                .findFirst();
    }

    @Override
    public Collection<Student> findByFirstNameContains(String firstName) {
        return entityManager
                .createQuery("select s from Student s where s.firstName like :name", Student.class)
                .setParameter("name", "%" + firstName + "%")  // Using "%" for a partial match
                .getResultList();
    }

    @Override
    public Collection<Student> findAll() {
        return entityManager.createQuery("select s from Student s", Student.class)
                .getResultList();
    }

    @Override
    public void update(Student student) {
        entityManager.merge(student);
    }

    @Override
    public void remove(String id) {
        Student foundStudent = entityManager.find(Student.class, id);
        if (foundStudent == null) {
            throw new StudentNotFoundException("Student with ID " + id + " not found");
        }
        entityManager.remove(foundStudent);
    }
}
