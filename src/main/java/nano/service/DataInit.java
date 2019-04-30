package nano.service;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import nano.repository.AccountRepository;

@Component
public class DataInit {
	@Autowired
	AccountRepository ser;
    
    @PostConstruct
    public void run() {
//        Account acc = new Account();
//        acc.setUsername("admin");
//        acc.setPassword("admin");
//        acc.setPassword("admin");
//        acc.setRole("ADMIN");
//        ser.save(acc);
//        acc = new Account();
//        acc.setUsername("emp");
//        acc.setPassword("emp");
//        acc.setPassword("emp");
//        acc.setRole("EMP");
//        ser.save(acc);
    }
    
    
}
