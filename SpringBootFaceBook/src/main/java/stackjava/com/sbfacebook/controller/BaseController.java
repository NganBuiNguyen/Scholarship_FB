package stackjava.com.sbfacebook.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.client.ClientProtocolException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import stackjava.com.sbfacebook.common.RestFB;
import stackjava.com.sbfacebook.db.UserShortTokenRepository;
import stackjava.com.sbfacebook.model.UserShortToken;

@Controller
public class BaseController {

	@Autowired
	private RestFB restFb;
	
	@Autowired
	private UserShortTokenRepository repository;

	@RequestMapping(value = { "/", "/login" })
	public String login() {
		return "login";
	}

	private String code;

	@RequestMapping("/login-facebook")
	public String loginFacebook(HttpServletRequest request) throws ClientProtocolException, IOException {

		code = request.getParameter("code");
		if (code == null || code.isEmpty()) {
			return "redirect:/login?facebook=error";
		}

		String accessToken = restFb.getToken(code);
		
		UserShortToken userShortToken = new UserShortToken();
		userShortToken.setIduser_token(1);
		userShortToken.setShort_token(accessToken);
		repository.save(userShortToken);

		request.getSession().setAttribute("code", code);
		System.out.println("Session code " + request.getSession().getAttribute("code"));
		com.restfb.types.User user = restFb.getUserInfo(accessToken);
		UserDetails userDetail = restFb.buildUser(user);

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetail, null,
				userDetail.getAuthorities());
		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return "redirect:/user";
	}

	@RequestMapping("/user")
	public String user() {
		return "user";
	}

	@RequestMapping("/admin")
	public String admin() {
		return "admin";
	}

	@RequestMapping("/403")
	public String accessDenied() {
		return "403";
	}

}

