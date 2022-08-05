package com.afridevteam.gestionstock.config;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.Permission;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FlickConfiguration {
    @Value("${flickr.apikey}")
    private String apiKey;

    @Value("${flickr.apisecret}")
    private String apiSecret;

    @Value("${flickr.appKey}")
    private String appKey;

    @Value("${flickr.appSecret}")
    private String appSecret;

   /* @Bean
    public Flickr getFlick() throws IOException, ExecutionException, InterruptedException, FlickrException {
        Flickr flickr = new Flickr(apiKey, apiSecret, new REST());
        OAuth10aService service = new ServiceBuilder(apiKey).apiSecret(apiSecret).build(FlickrApi.instance((FlickrApi.FlickrPerm.WRITE)));
import org.springframework.context.annotation.Configuration;
        final Scanner scanner = new Scanner(System.in);

        final OAuth1RequestToken requestToken = service.getRequestToken();
        final String authUrl = service.getAuthorizationUrl(requestToken);

        System.out.println(authUrl);
        System.out.println("Coller ici >>");

        final String authVerifier = scanner.nextLine();
        OAuth1AccessToken accessToken = service.getAccessToken(requestToken, authVerifier);

        System.out.println(accessToken.getToken());
        System.out.println(accessToken.getTokenSecret());

        Auth auth = flickr.getAuthInterface().checkToken(accessToken);

        System.out.println("-------------------------------------");

        System.out.println(auth.getToken());
        System.out.println(auth.getTokenSecret());


        return flickr;
    }*/

    @Bean(name = "getFlickr2")
    public Flickr getFlickr2() {
        Flickr flickr = new Flickr(apiKey, apiSecret, new REST());
        Auth auth = new Auth();
        auth.setPermission(Permission.READ);
        auth.setToken(appKey);
        auth.setTokenSecret(appSecret);
        RequestContext requestContext = RequestContext.getRequestContext();
        requestContext.setAuth(auth);
        flickr.setAuth(auth);
        return flickr;

    }
}
