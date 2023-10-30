package com.example.lecturejpa.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
public class Student {

    // For generating Integer ID: @GeneratedValue(strategy = GenerationType.IDENTITY)
    //All the annotations should be imported from Jakarta persistence not the spring framework
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(updatable = false)
    private String id;

    @Column(nullable = false, length = 100)
    @Setter
    private String firstName;
    @Column(nullable = false, length = 100)
    @Setter
    private String lastName;

    @Column(nullable = false, unique = true)
    @Setter
    private String email;

    private boolean status;
    private LocalDateTime createDate;


    @Setter
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "student")
    private Set<Course> courses = new HashSet<>();

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = true;
        this.createDate = LocalDateTime.now();
    }

        public void addCourse(Course course) {
        courses.add(course);
        course.setStudent(this);
        }
        public void removeCourse(Course course) {
        courses.remove(course);
        course.setStudent(null);
        }
}
