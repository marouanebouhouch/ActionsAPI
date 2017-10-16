package com.actions.RestService;

import com.actions.dao.BuyRepository;
import com.actions.dao.CompanyRepository;
import com.actions.dao.SellRepository;
import com.actions.dao.TagRepository;
import com.actions.entities.Buy;
import com.actions.entities.Company;
import com.actions.entities.Sell;
import com.actions.entities.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/companies")
public class CompanyRestService {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private SellRepository sellRepository;
    @Autowired
    private BuyRepository buyRepository;

    @RequestMapping(value = "/{company_id}/removetag/{tag_id}", method = RequestMethod.PUT)
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
        return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{company_id}/attachtags", method = RequestMethod.PUT)
    public ResponseEntity<Company> attachTagToCompany(@PathVariable("company_id") Long company_id,
                                                      @RequestBody Tag[] tags){
        if (companyRepository.exists(company_id)){
            Company c = companyRepository.findOne(company_id);
            for(Tag tag: tags){
                if(c.getTags().indexOf(tag) == -1)
                    c.getTags().add(tag);
            }
            companyRepository.save(c);
            return new ResponseEntity<Company>(c, HttpStatus.OK);
        }
        return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{company_id}/removeTags", method = RequestMethod.PUT)
    public ResponseEntity<Company> removeAllTags(@PathVariable("company_id") Long company_id){
        if(companyRepository.exists(company_id)){
            Company c = companyRepository.findOne(company_id);
            c.getTags().removeAll(c.getTags());
            companyRepository.save(c);
            return new ResponseEntity<Company>(c, HttpStatus.OK);
        }
        return new ResponseEntity<Company>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{company_id}/sells", method = RequestMethod.GET)
    public ResponseEntity<List<Sell>> getSellsByCompany(@PathVariable("company_id")Long company_id){
        if (companyRepository.exists(company_id)){
            List<Sell> sells = sellRepository.getAllByCompany_Id(company_id);
            return new ResponseEntity<List<Sell>>(sells, HttpStatus.OK);
        }
        return new ResponseEntity<List<Sell>>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{company_id}/buys", method = RequestMethod.GET)
    public ResponseEntity<List<Buy>> getbuysByCompany(@PathVariable("company_id")Long company_id){
        if (companyRepository.exists(company_id)){
            List<Buy> buys = buyRepository.getAllByCompany_Id(company_id);
            return new ResponseEntity<List<Buy>>(buys, HttpStatus.OK);
        }
        return new ResponseEntity<List<Buy>>(HttpStatus.NO_CONTENT);
    }
}
