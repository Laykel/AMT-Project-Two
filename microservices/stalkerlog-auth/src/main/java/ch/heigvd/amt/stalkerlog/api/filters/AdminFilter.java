package ch.heigvd.amt.stalkerlog.api.filters;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Admin filter: checks whether the user is admin
 *
 * @author Alisons Savary, Luc Wachter
 */
@Order(2)
public class AdminFilter extends OncePerRequestFilter {
    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        boolean isAdmin = Boolean.parseBoolean((String) request.getAttribute("isAdmin"));

        if (!isAdmin) {
            response.sendError(HttpStatus.UNAUTHORIZED.value(), "You must be an admin to perform this action");
            return;
        }

        request.setAttribute("isAdmin", true);

        chain.doFilter(request, response);
    }
}
