package dev.sumuks.Newspaper.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(1)
@Slf4j
class TenantFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        String tenantName = req.getHeader("TenantID");
        String category = req.getParameter("category");
        TenantContext.setCurrentTenant(tenantName);
        log.info("Current Tenant : {}, Category : {}", tenantName, category);

        try {
            chain.doFilter(request, response);
        } finally {
            TenantContext.setCurrentTenant("");
        }
    }
}
