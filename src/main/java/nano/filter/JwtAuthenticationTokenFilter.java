package nano.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import nano.entity.Account;
import nano.repository.AccountRepository;
import nano.service.JwtService;

public class JwtAuthenticationTokenFilter extends UsernamePasswordAuthenticationFilter {

	private final static String TOKEN_HEADER = "authorization";

	@Autowired
	private JwtService jwt;

	@Autowired
	private AccountRepository rep;
	
	@Autowired
	private UserDetailsService ser;

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		String authToken = httpRequest.getHeader(TOKEN_HEADER);
		if(authToken != null) {
			if(jwt.validateTokenLogin(authToken)) {
				String username = jwt.getUsernameFromToken(authToken);
				Account acc = rep.findByUsername(username);
				if(acc != null) {
					UserDetails userDetails = ser.loadUserByUsername(username);
					UsernamePasswordAuthenticationToken authenticationToken = 
							new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
			        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				}
			}else {
				System.out.println("RIP Token");
			}
		}
		chain.doFilter(req, res);
	}
}
