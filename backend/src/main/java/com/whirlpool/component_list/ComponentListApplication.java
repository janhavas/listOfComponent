package com.whirlpool.component_list;

import com.whirlpool.component_list.prodcomponents.ProdComponent;
import com.whirlpool.component_list.prodorders.OrderWithComponent;
import com.whirlpool.component_list.prodorders.OrdersWithComponentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableScheduling
public class ComponentListApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComponentListApplication.class, args);
	}



	/*@Bean
	CommandLineRunner runner(OrdersWithComponentRepository repository){
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		return args -> {
			List<ProdComponent> prodComponents = new ArrayList<>();
			ProdComponent prodComponent1 = new ProdComponent(
					"400010609796",
					"POWER CORD VARIABLE",
					"92",
					"19500101",
					"99991231"
			);
			ProdComponent prodComponent2 = new ProdComponent(
					"400011630521",
					"MAIN HARNESS AZ6 TCP4",
					"27",
					"20230329  ",
					"99991231"
			);
			prodComponents.add(prodComponent1);
			prodComponents.add(prodComponent2);

			OrderWithComponent ordersWithComponent = new OrderWithComponent(
					"21455",
					"0302886528",
					"859991653610",
					"01",
					"759991653611",
					"TLAI10B ID EE 104C_TCBK5YR0LL S2",
					timestamp,
					192,
					prodComponents
			);
			repository.insert(ordersWithComponent);
		};
	}*/
}



