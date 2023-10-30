package com.example.lecturejpa.repository;

import com.example.lecturejpa.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    // select * from course c join course_instructors ci on c.id = course_id;
    Collection<Course> findByInstructors_Id(Long instructorId);
    @Query("select c from Course c join Instructor i where i.id = :instructorId")
    List<Course> findCoursesByInstructorId(@Param("instructorId") Long instructorId);

}
