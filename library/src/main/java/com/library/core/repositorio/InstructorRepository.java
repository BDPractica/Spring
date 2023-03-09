package com.library.core.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.library.core.entidades.Instructor;

@Repository("com.library.core.repositorio.InstructorRepository")
public interface InstructorRepository extends JpaRepository<Instructor,Long>{

}
