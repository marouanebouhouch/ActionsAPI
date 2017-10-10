package com.actions.RestService;

import com.actions.dao.CompanyRepository;
import com.actions.dao.TagRepository;
import com.actions.entities.Company;
import com.actions.entities.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class CompanyRestService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private TagRepository tagRepository;

    @RequestMapping(value = "/companies/{company_id}/removetag/{tag_id}", method = RequestMethod.PUT)
    public ResponseEntity<Company> removeTagFromCompany(@PathVariable("company_id") Long company_id,
                                                        @PathVariable("tag_id") Long tag_id){
        if (companyRepository.exists(company_id)){
            Company c = companyRepository.findOne(company_id);
            if(tagRepository.exists(tag_id)){
                Tag tag = tagRepository.findOne(tag_id);
                c.getTags().remove(tag);
                companyRepository.save(c);
            }
            return new ResponseEntity<Company>(c, HttpStatus.OK);
        }
        else
            return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/companies/{company_id}/attachtag/{tag_id}", method = RequestMethod.PUT)
    public ResponseEntity<Company> attachTagToCompany(@PathVariable("company_id") Long company_id,
                                                      @PathVariable("tag_id") Long tag_id){
        if (companyRepository.exists(company_id)){
            Company c = companyRepository.findOne(company_id);
            if(tagRepository.exists(tag_id)){
                Tag tag = tagRepository.findOne(tag_id);
                if(c.getTags().indexOf(tag) == -1){
                    c.getTags().add(tag);
                    companyRepository.save(c);
                }
            }
            return new ResponseEntity<Company>(c, HttpStatus.OK);
        }
        else
            return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);
    }
}
