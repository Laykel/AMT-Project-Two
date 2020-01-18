package ch.heigvd.amt.stalkerlog.filters;

import ch.heigvd.amt.stalkerlog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(1)
public class UserAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    UserService userService;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwt = request.getHeader("Authorization");
        if (jwt == null) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "The Authorization header must be set");
            return;
        }

        String userId = userService.decodeJWTString(jwt);
        if (userId == null) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Please provide a valid token");
            return;
        }

        request.setAttribute("userId", userId);

        chain.doFilter(request, response);
    }
}
