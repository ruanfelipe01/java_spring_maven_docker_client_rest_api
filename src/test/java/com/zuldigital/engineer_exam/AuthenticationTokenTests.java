package com.zuldigital.engineer_exam;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationTokenTests {

	private AuthenticationToken authenticationToken;

	@SuppressWarnings("static-access")
	@Test
	public void checkAuthorization()throws Exception 
	{
		authenticationToken = new AuthenticationToken();

		HttpClient client = null;
		HttpPost post = null;
		HttpResponse response = null;

		client = HttpClientBuilder.create().build();

		post = new HttpPost(authenticationToken.TOKEN_URL);

		post.setEntity(new StringEntity(authenticationToken.TOKEN_REQUEST_JSON));
		post.setHeader(HttpHeaders.CONTENT_TYPE, authenticationToken.APPLICATION_JSON);
		post.setHeader(HttpHeaders.ACCEPT, authenticationToken.APPLICATION_JSON);

		response = client.execute(post);
		assertThat(authenticationToken.getAuthorization(client, post, response), is(true));
		System.out.println("Authorization test was executed");
	}
}

