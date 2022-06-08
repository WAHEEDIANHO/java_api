package com.nacoss.tutorial.model.repositories;

import com.nacoss.tutorial.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepositry extends JpaRepository<Student, Long> {
}
