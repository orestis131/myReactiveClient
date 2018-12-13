package reactive.web.client;

import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@ConfigurationProperties("client")
@Setter
public class MyReactiveClient extends BaseClient {

	protected String path;

	public Mono<Health> getHealth() {
		return webClient
				.get()
				.uri(path)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToMono(Health.class);
	}
}
