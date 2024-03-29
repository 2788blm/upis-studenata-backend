package com.dodeka.upisstudenatabackend.repositories;

import com.dodeka.upisstudenatabackend.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {



}
