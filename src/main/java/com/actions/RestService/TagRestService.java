package com.actions.RestService;

import com.actions.dao.CompanyRepository;
import com.actions.dao.TagRepository;
import com.actions.entities.Company;
import com.actions.entities.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class TagRestService {

    @Autowired
    private TagRepository tagRepository;

    @RequestMapping(value = "/tags/exists", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean tagExists(@RequestParam("label") String label){
        if(tagRepository.countTagsByLabel(label.trim()) > 0) return true;
        return false;
    }
}
