package reactive.web.client.test;

import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import reactive.web.client.Health;
import reactive.web.client.MyReactiveClient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@AutoConfigureWebTestClient
public class MyReactiveClientTest {

	@Autowired
	private MyReactiveClient myReactiveClient;

	private WebTestClient myReactiveTestClient;

	private String url = "http://apigatewayj.lnlo.qa.l10.intralot.com";

	private String path = "/health";

	@Before
	public void initializeClient() {

		myReactiveTestClient = WebTestClient
				.bindToServer()
				.baseUrl(url)
				.filter()
				.build();
	}

	@Test
	public void testHealth() throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(1);
		myReactiveClient
				.getHealth()
				.subscribe(e -> log.info(e.getStatus()),
						e -> log.error(e.getLocalizedMessage()),
						countDownLatch::countDown);
		countDownLatch.await();
	}

	@Test
	public void testHealth2() {

		Health expectedStatus = new Health();
		expectedStatus.setStatus("UP");

		myReactiveTestClient
				.get()
				.uri(path)
				.accept(MediaType.APPLICATION_JSON)
				.exchange()
				.expectStatus()
				.isOk()
				.expectBody(Health.class)
				.isEqualTo(expectedStatus)
				.consumeWith(r -> assertEquals(r.getResponseBody().getStatus(),"UP"));
	}
}
