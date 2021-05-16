package com.example.security;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private static final String USER_NOT_FOUND_PASSWORD = "userNotFoundPassword";

	@Autowired
	@Qualifier("customUserDetailsService")
	UserDetailsService userDetailsService;
	private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	private volatile String userNotFoundEncodedPassword;

	@Override
	public boolean supports(Class<?> authentication) {
		return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
//		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

//		String response = getCaptchaValue();
//		String secretKey = "";
//		if (!isCaptchaValid(secretKey, response)) {
//			logger.debug("Authentication failed: Captcha failed");
//			throw new BadCredentialsException(
//					messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Invalid Captcha"));
//		}

		if (authentication.getCredentials() == null) {
			logger.debug("Authentication failed: no credentials provided");

			throw new BadCredentialsException(
					messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
		}

		String presentedPassword = authentication.getCredentials().toString();

		if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
			logger.debug("Authentication failed: password does not match stored value");

			throw new BadCredentialsException(
					messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
		}
	}

//	private String getCaptchaValue() {
//		// TODO Auto-generated method stub
//		RequestAttributes attribs = RequestContextHolder.getRequestAttributes();
//		if (attribs instanceof ServletRequestAttributes) {
//			HttpServletRequest request = ((ServletRequestAttributes) attribs).getRequest();
//			return request.getParameter("g-recaptcha-response");
//		}
//		return "*";
//	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		prepareTimingAttackProtection();
		try {
			UserDetails loadedUser = userDetailsService.loadUserByUsername(username);
			if (loadedUser == null) {
				throw new BadCredentialsException(messages
						.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
			}
			return loadedUser;
		}

		catch (Exception ex) {
			throw new BadCredentialsException(
					messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
		}

		// return null;
	}

	private void prepareTimingAttackProtection() {
		if (this.userNotFoundEncodedPassword == null) {
			this.userNotFoundEncodedPassword = this.passwordEncoder.encode(USER_NOT_FOUND_PASSWORD);
		}
	}

	private void mitigateAgainstTimingAttack(UsernamePasswordAuthenticationToken authentication) {
		if (authentication.getCredentials() != null) {
			String presentedPassword = authentication.getCredentials().toString();
			this.passwordEncoder.matches(presentedPassword, this.userNotFoundEncodedPassword);
		}
	}

//	public boolean isCaptchaValid(String secretKey, String response) {
//		try {
//			String url = "https://www.google.com/recaptcha/api/siteverify?" + "secret=" + secretKey + "&response="
//					+ response;
//			InputStream res = new URL(url).openStream();
//			BufferedReader rd = new BufferedReader(new InputStreamReader(res, Charset.forName("UTF-8")));
//
//			StringBuilder sb = new StringBuilder();
//			int cp;
//			while ((cp = rd.read()) != -1) {
//				sb.append((char) cp);
//			}
//			String jsonText = sb.toString();
//			res.close();
//
//			JSONObject json = new JSONObject(jsonText);
//			return json.getBoolean("success");
//		} catch (Exception e) {
//			return false;
//		}
//	}
}
