package ch.heigvd.amt.stalkerlog.api.filters;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Define which filters should be used depending on the route
 *
 * @author Alison Savary, Luc Wachter
 */
@Configuration
public class FilterRouter {
    @Bean
    public FilterRegistrationBean<UserAuthenticationFilter> userAuthenticationFilter() {
        FilterRegistrationBean<UserAuthenticationFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new UserAuthenticationFilter());
        registrationBean.addUrlPatterns("/users/*", "/registration/*");

        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<AdminFilter> adminFilter() {
        FilterRegistrationBean<AdminFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new AdminFilter());
        registrationBean.addUrlPatterns("/registration/*");

        return registrationBean;
    }
}
