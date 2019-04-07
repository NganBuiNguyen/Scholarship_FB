package stackjava.com.sbfacebook.service;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
 
import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import stackjava.com.sbfacebook.model.FirebaseResponse;
 
@Service
public class AndroidPushNotificationsService {
 
  private static final String FIREBASE_SERVER_KEY = "AAAAwMFgs5Y:APA91bEZt8KGWbEv_pU_NGnwIec5GMdkEkiuV41cVgSfOC0zmWQ-nYv9F_N0gI7wzU7pkTkIhJ1bYBGne4QQdMYxp34Je3hm1Vo_2HXTQBAm0jM3AHQsNbuVyf8xkR5jHYe0ZE4old4M";
  private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";
  
  @Async
  public CompletableFuture<FirebaseResponse> send(HttpEntity<String> entity) {
 
    RestTemplate restTemplate = new RestTemplate();
 
    /**
    https://fcm.googleapis.com/fcm/send
    Content-Type:application/json
    Authorization:key=FIREBASE_SERVER_KEY*/
 
    ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
    interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + FIREBASE_SERVER_KEY));
    interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json"));
    restTemplate.setInterceptors(interceptors);
 
    FirebaseResponse firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, FirebaseResponse.class);
 
    return CompletableFuture.completedFuture(firebaseResponse);
  }
}