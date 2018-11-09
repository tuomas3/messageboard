package com.buutcamp.config;

import com.buutcamp.database.HibernateMethods;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

public class WebMvcInit implements WebApplicationInitializer {
    private HibernateMethods hibernateMethods;

    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(WebMvcConfig.class);

        ServletRegistration.Dynamic registration =
                servletContext.addServlet("javaMvcConfig", new DispatcherServlet(ctx));
        registration.addMapping("/");
        registration.setLoadOnStartup(1);

        addFilter(servletContext);
    }

    private void addFilter(ServletContext servletContext){
        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encoding-filter", new CharacterEncodingFilter());
        encodingFilter.setInitParameter("encoding", "UTF-8");
        encodingFilter.setInitParameter("forceEncoding", "true");
        encodingFilter.addMappingForUrlPatterns(null, false, "/*");
    }
}
