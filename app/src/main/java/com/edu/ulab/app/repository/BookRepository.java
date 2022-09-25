package com.edu.ulab.app.repository;

import com.edu.ulab.app.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface BookRepository extends CrudRepository<BookEntity, Long> {

}
