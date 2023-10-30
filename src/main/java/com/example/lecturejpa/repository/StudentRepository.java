package com.example.lecturejpa.repository;

import com.example.lecturejpa.entity.Student;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    // already contains all the basic crud operations for Student entity

}
