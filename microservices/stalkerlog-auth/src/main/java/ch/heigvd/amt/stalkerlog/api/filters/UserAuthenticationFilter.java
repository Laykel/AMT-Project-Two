package ch.heigvd.amt.stalkerlog.api.filters;

import ch.heigvd.amt.stalkerlog.api.util.AuthUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * First filter: checks whether the user sent a valid token
 *
 * @author Alisons Savary, Luc Wachter
 */
@Order(1)
public class UserAuthenticationFilter extends OncePerRequestFilter {
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String jwt = request.getHeader("Authorization");
        if (jwt == null) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "The Authorization header must be set");
            return;
        }

        Jws<Claims> jws = AuthUtils.decodeJWTString(jwt);

        if (jws == null) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Please provide a valid token");
            return;
        }

        request.setAttribute("userId", jws.getBody().get("userId", Long.class));
        request.setAttribute("isAdmin", jws.getBody().get("role", Boolean.class));

        chain.doFilter(request, response);
    }
}
