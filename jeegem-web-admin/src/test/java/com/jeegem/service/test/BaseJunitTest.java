package com.jeegem.service.test;

import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)    
@ContextConfiguration(locations = {"file:src/main/resources/spring/spring-*.xml"})
public class BaseJunitTest {  
      
}  