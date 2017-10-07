package com.actions.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.actions.entities.Company;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("*")
@RepositoryRestResource
public interface CompanyRepository extends CrudRepository<Company,Long> {
}
