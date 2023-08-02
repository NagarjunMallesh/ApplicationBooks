package com.project.Application.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.Application.Model.Books;

public interface AppRepository extends JpaRepository<Books, Long> {

}
