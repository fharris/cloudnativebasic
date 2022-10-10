package com.fharris.cloudnativebasic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.text.NumberFormat;

@SpringBootTest
class CloudnativebasicApplicationTests {
	@Autowired
	private ApplicationContext ctx;

	@Autowired @Qualifier("defaultCurrencyFormat")
	private NumberFormat nf;

	@Test
	public void defaultCurrency(){
		double amount = 12345678.901234;
		System.out.println(nf.format(amount));
	}

	@Test
	public void germanCurrency(){
		double amount = 12345678.901234;
		NumberFormat deutshNF = ctx.getBean("germanCurrencyFormat", NumberFormat.class);
		System.out.println(deutshNF.format(amount));
	}

	@Test
	public void contextLoads() {
		int countBeans = ctx.getBeanDefinitionCount();
		System.out.println("There are " + countBeans + " Beans Count in the Application Context:wq" +
				"");

		for (String name: ctx.getBeanDefinitionNames()){
			System.out.println(name);
		}
	}

}
