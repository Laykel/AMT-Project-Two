package ch.heigvd.amt.stalkerlog.filters;

import ch.heigvd.amt.stalkerlog.entities.UserEntity;
import ch.heigvd.amt.stalkerlog.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Order(2)
public class AdminFilter extends OncePerRequestFilter {
    @Autowired
    UserRepository userRepository;

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Long userId = Long.parseLong((String) request.getAttribute("userId"));

        Optional<UserEntity> maybeUser = userRepository.findById(userId);
        if (maybeUser.isPresent()) {
            UserEntity user = maybeUser.get();

            // Check that the user is admin
            if (!user.isAdmin()) {
                response.sendError(HttpStatus.UNAUTHORIZED.value(), "You must be an admin to perform this action");
                return;
            }
        }

        request.setAttribute("isAdmin", true);

        chain.doFilter(request, response);
    }
}
