package com.example.lecturejpa.dao;

import com.example.lecturejpa.entity.Student;
import jakarta.transaction.Transactional;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;


@SpringBootTest
@Transactional
@Rollback
public class StudentDaoImplTest {

    @Autowired
    StudentDaoImpl studentDao;

    @Test
    public void testPersistStudent() {
        // Arrange
        Student student = new Student("John", "Doe", "john@test.se");

        // Act
        Student insertedStudent = studentDao.persist(student);

        // Assert
        assertNotNull(insertedStudent);
    }
    @Test
    public void testFindStudentById() {
        // Arrange
        Student student = new Student("Negar", "Baharmand", "negar@test.se");
        Student insertedStudent = studentDao.persist(student);

        // Act
        Optional<Student> foundStudent = studentDao.findById(insertedStudent.getId());

        // Assert
        assertTrue(foundStudent.isPresent());
        assertEquals(insertedStudent.getId(), foundStudent.get().getId());
        assertEquals("Negar", foundStudent.get().getFirstName());
        assertEquals("Baharmand", foundStudent.get().getLastName());
        assertEquals("negar@test.se", foundStudent.get().getEmail());
    }

}
