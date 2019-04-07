package stackjava.com.sbfacebook.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;
import com.restfb.types.Post;
import stackjava.com.sbfacebook.db.UserShortTokenRepository;
import stackjava.com.sbfacebook.model.UserShortToken;

@Service
public class RunnableCallPostFacebookService {

	@Autowired
	private UserShortTokenRepository userShortTokenRepository;

	public List<Post> run(String sessionValue) throws ClientProtocolException, IOException {

		FacebookClient fbClient = new DefaultFacebookClient(sessionValue, Version.LATEST);
		List<Post> newPost = new ArrayList<>();
		List<UserShortToken> listUser = userShortTokenRepository.findAll();
		UserShortToken userShortToken = listUser.stream().filter(c -> c.getIduser_token() == 2).findAny().get();
		Connection<Post> postFeed = fbClient.fetchConnection("273839346438155" + "/feed", Post.class);
		List<Post> listPost = postFeed.getData();
		String postId = "";
		for (Post post : listPost) {

			if (userShortToken.getShort_token().equals(post.getId())) {
				if (!postId.isEmpty()) {
					userShortToken.setShort_token(postId);
					userShortTokenRepository.save(userShortToken);
				}
				break;
			}
			postId = post.getId();
			newPost.add(post);
		}
		return newPost;

	}

}
