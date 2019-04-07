package stackjava.com.sbfacebook.config;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restfb.types.Post;

import stackjava.com.sbfacebook.controller.SendMessageController;
import stackjava.com.sbfacebook.db.UserShortTokenRepository;
import stackjava.com.sbfacebook.model.UserShortToken;
import stackjava.com.sbfacebook.service.RunnableCallPostFacebookService;

@Component
public class SchedulerConfig {

	@Autowired
	SimpMessagingTemplate template;

	@Autowired
	RunnableCallPostFacebookService service;

	@Autowired
	private Environment env;

	@Autowired
	UserShortTokenRepository repository;

	@Autowired
	SendMessageController sendMessage;

	@Scheduled(fixedDelay = 3000)
	public void sendAdhocMessages()
			throws MessagingException, ClientProtocolException, IOException, URISyntaxException {
		String token = getLongToken();
		// template.convertAndSend("/topic/user", new
		// UserResponse(service.run(token)));
		List<Post> list = service.run(token);
		System.out.println(list.size());
		if (!list.isEmpty()) {
			try {
				sendMessage.send();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private String getLongToken() throws ClientProtocolException, IOException {

		UserShortToken userShortToken = repository.findAll().get(0);
		String shortToken = userShortToken.getShort_token();

		String link = String.format(env.getProperty("facebook.grapth.path"), env.getProperty("facebook.app.id"),
				env.getProperty("facebook.app.secret"), shortToken);
		String response = Request.Get(link).execute().returnContent().asString();
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(response).get("access_token");
		return node.textValue();
	}

}
