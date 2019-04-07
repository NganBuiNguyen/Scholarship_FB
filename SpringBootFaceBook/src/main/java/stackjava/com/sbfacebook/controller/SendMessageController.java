package stackjava.com.sbfacebook.controller;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.MessagingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import stackjava.com.sbfacebook.db.SystemRepository;
import stackjava.com.sbfacebook.model.FirebaseResponse;
import stackjava.com.sbfacebook.model.Phone;
import stackjava.com.sbfacebook.service.AndroidPushNotificationsService;

@RestController
public class SendMessageController {

	public static String accessToken;

	@Autowired
	AndroidPushNotificationsService androidPushNotificationsService;
	
	@Autowired
	SystemRepository repository;

	@RequestMapping(value = "/sendMessage", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<String> send() throws JSONException, MessagingException, ClientProtocolException, IOException, URISyntaxException {

			List<Phone> listIdDevice = repository.findAll();
			List<String> listToken = listIdDevice.stream().map(Phone::getToken).collect(Collectors.toList());
			JSONObject body = new JSONObject();
			body.put("registration_ids", listToken);
			body.put("priority", "high");

			JSONObject data = new JSONObject();
			data.put("message", "Hello");

			body.put("data", data);

			JSONObject notification = new JSONObject();
			notification.put("body", "This is test");
			notification.put("title", "Test again");

			body.put("notification", notification);
			HttpEntity<String> request = new HttpEntity<>(body.toString());

			CompletableFuture<FirebaseResponse> pushNotification = androidPushNotificationsService.send(request);
			CompletableFuture.allOf(pushNotification).join();

			try {
				FirebaseResponse firebaseResponse = pushNotification.get();
				if (firebaseResponse.getSuccess() == 1) {
				} else {
				}
				return new ResponseEntity<>(firebaseResponse.toString(), HttpStatus.OK);

			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		return new ResponseEntity<>("the push notification cannot be send.", HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/saveToken", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	public Phone saveToken(@RequestParam(value = "token") String token) {

		if (token == null)
			return null;
		Phone phone = new Phone();
		phone.setToken(token);
		return repository.save(phone);

	}

}
