package com.example.lecturejpa.repository;

import com.example.lecturejpa.entity.Student;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    // already contains all the basic crud operations for Student entity accessed by primary key
    // select * from student where first_name = 'test'; (We have some name conditions)
    List<Student> findByFirstName(String firstName);

    //When we don't use name conventions and write our custom method we need to attach our query to the method:
    @Query("Select s from Student s where s.firstName = :fn")
    List<Student> findStudentByFirstName(Student firstName);

    List<Student> findByFirstNameContains(String firstName);

    List<Student> findByStatusTrue();

    Student findByEmailIgnoreCase(String email);

    //update student det status = true where id = :id;
    @Modifying
    @Query("update Student s set s.status = true where s.id = :id")
    void updateStudentStatusTrue(@Param("studentId") String studentId);

}
