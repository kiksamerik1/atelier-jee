package edu.app.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.app.web.mb.AuthenticationBean;

@WebFilter(filterName="loginFilter")
public class LoginFilter implements Filter {

	public void destroy() {
		System.out.println("-------filter destroyed-------");
	}

	public void doFilter(ServletRequest req, ServletResponse resp ,FilterChain chain) throws IOException, ServletException {
		System.out.println("Login Filter Invoked");
		String filteringStory = "";
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		AuthenticationBean authenticationBean = (AuthenticationBean) request.getSession().getAttribute("authenticationBean");
		if ((authenticationBean != null) && (authenticationBean.isLoggedIn())) {
			filteringStory+="loggedIn="+authenticationBean.isLoggedIn()+" -> let go!";
			System.out.println(filteringStory);
			chain.doFilter(request, response);
		} else {
			filteringStory+="loggedIn=false";
			if (!request.getRequestURL().toString().contains("signin.xhtml")) {
				filteringStory+=" : not asking for signin -> redirecting to signin.xhtml";
				System.out.println(filteringStory);
				response.sendRedirect(request.getContextPath()+ "/faces/signin.xhtml");
			} else {
				System.out.println(filteringStory);
				filteringStory+=" : asking for signin -> let go!";
				chain.doFilter(request, response);
			}
		}
	}

	public void init(FilterConfig config) throws ServletException {
		System.out.println("-------filter intansiated-------");
		System.out.println(config);
		System.out.println(config.getFilterName());
	}

}
