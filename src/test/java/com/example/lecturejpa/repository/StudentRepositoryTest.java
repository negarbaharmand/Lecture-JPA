package com.example.lecturejpa.repository;

import com.example.lecturejpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
public class StudentRepositoryTest {
    @Autowired
    StudentRepository studentRepository;

    @Test
    @Transactional
    public void testSaveAndFindBy() {
        Student student = new Student("Test", "Testian" , "test@gmail.com");
        Student savedStudent = studentRepository.save(student);
        assertNotNull(savedStudent);
        assertNotNull(savedStudent.getId());

        Optional<Student> foundStudent = studentRepository.findById(savedStudent.getId());
        assertTrue(foundStudent.isPresent());
    }
}
