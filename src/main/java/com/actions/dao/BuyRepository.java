package com.actions.dao;

import com.actions.entities.Buy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("*")
@RepositoryRestResource
public interface BuyRepository extends CrudRepository<Buy,Long> {
    public List<Buy> getAllByCompany_Id(Long company_id);
}
