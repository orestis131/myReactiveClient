package reactive.web.client;

import javax.annotation.PostConstruct;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@ConfigurationProperties("client")
@Component
@Setter
public class BaseClient {

	protected WebClient webClient;

	protected String url;

	@PostConstruct
	protected void init() {
		webClient = WebClient.builder()
				.baseUrl(url)
				.build();
	}
}
