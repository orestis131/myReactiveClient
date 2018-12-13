package reactive.web.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Runner implements CommandLineRunner {

	@Autowired
	protected MyReactiveClient myReactiveClient;

	@Override
	public void run(String... args) throws Exception {
//		myReactiveClient
//				.getHealth()
//				.subscribe(System.out::println);
	}
}
