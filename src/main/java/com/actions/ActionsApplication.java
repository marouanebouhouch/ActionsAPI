package com.actions;

import com.actions.dao.*;
import com.actions.entities.*;
import com.github.javafaker.Faker;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import java.time.DateTimeException;
import java.util.*;

@SpringBootApplication
public class ActionsApplication implements CommandLineRunner{

	@Autowired
	private CompanyRepository companyRepository;
	@Autowired
	private BuyRepository buyRepository;
	@Autowired
	private SellRepository sellRepository;
	@Autowired
	private TagRepository tagRepository;

	public static void main(String[] args) {
		SpringApplication.run(ActionsApplication.class, args);
	}

	@Override
	@Transactional
	public void run(String... strings) throws Exception {
		Faker faker = new Faker();

		for(int i=0;i<20;i++){
			Tag t = new Tag(faker.company().buzzword());
			tagRepository.save(t);
		}
		for(int i=1;i<100;i++){
			Company c = new Company();
			c.setName(faker.company().name()+' '+faker.company().suffix());
			c.setAddress(faker.address().streetAddressNumber()+' '+faker.address().streetName()+ ", " + faker.address().city() + ' ' + faker.address().country().toUpperCase());
			c.setTurnover(faker.number().numberBetween(10000,10000000));
			c.setCatchphrase(faker.company().catchPhrase());
			c.setIndustry(faker.company().industry());
			c.setLatitude(Double.parseDouble(faker.address().latitude()));
			c.setLongitude(Double.parseDouble(faker.address().longitude()));
			c.setLogo(faker.company().logo());
			c.setUrl(faker.company().url());
			c.setCeo(faker.name().fullName());

			//Tags
			List<Integer> tagsId = generateRandomNumbers(1,20,faker.number().numberBetween(1,20));
			List<Tag> tags = new ArrayList<Tag>();
			for (int id: tagsId) {
				tags.add(tagRepository.findOne(Integer.toUnsignedLong(id)));
			}

			c.setTags(tags);
			companyRepository.save(c);
			for(int j=0;j<=i;j++,j++){
				Buy b = new Buy();
				b.setCompany(c);
				b.setDate_action(faker.date().between(new Date(0),new Date()));
				b.setNb_actions(faker.number().numberBetween(10,100));
				b.setPrice_action(faker.number().numberBetween(100,10000));
				buyRepository.save(b);
				Sell s = new Sell();
				s.setCompany(c);
				s.setDate_action(faker.date().between(new Date(0),new Date()));
				s.setNb_actions(j);
				s.setPrice_action(faker.number().numberBetween(100,10000));
				sellRepository.save(s);
			}
		}
	}

	public ArrayList<Integer> generateRandomNumbers(int min,int max,int nb){
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Integer> toReturn = new ArrayList<Integer>();
		for (int i=min;i<max;i++)
			list.add(i);
		Collections.shuffle(list);
		for(int i=0;i<nb;i++)
			toReturn.add(list.get(i));
		return toReturn;
	}
}
