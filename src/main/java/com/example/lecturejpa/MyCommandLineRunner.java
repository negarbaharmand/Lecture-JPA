package com.example.lecturejpa;

import com.example.lecturejpa.dao.StudentDao;
import com.example.lecturejpa.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// We can Use this class for running the program to see the CRUD operation if we don't have junit tests
// Or we can create the admin user here so when er run the app there is one user already created
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    @Autowired
    StudentDao studentDao;

    @Override
    public void run(String... args) throws Exception {
        // Student student = new Student("Test", "Testsson", "test@test.se");
        // studentDao.persist(student);
    }
}
