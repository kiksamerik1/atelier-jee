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

@WebFilter(filterName="adminFilter")
public class AdminFilter implements Filter {

	public void destroy() {
		System.out.println("-------filter destroyed-------");
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		System.out.println("Admin Filter Invoked");
		String filteringStory = "";
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response =(HttpServletResponse) resp;
		AuthenticationBean authenticationBean = (AuthenticationBean) request.getSession().getAttribute("authenticationBean");
		String userType = authenticationBean.getUserType();
		if (userType.equals("Admin")) {
			filteringStory+="loggedIn as Admin --> let go";
			System.out.println(filteringStory);
			chain.doFilter(request, response);
		} else {
			filteringStory+="loggedIn but not as an Admin --> redirect to signin";
			System.out.println(filteringStory);
			response.sendRedirect(request.getContextPath()+ "/faces/signin.xhtml");
		}
		
	}

	public void init(FilterConfig config) throws ServletException {
		System.out.println("-------filter intansiated-------");
		System.out.println(config);
		System.out.println(config.getFilterName());
	}

}
