package ru.itis.configservice;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

@Component
public class DebugFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        if (!(servletRequest instanceof HttpServletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        StringBuilder sb = new StringBuilder();
        sb.append(req.getMethod()).append(" ").append(req.getContextPath()).append(req.getRequestURL().toString()).append("\n");
        printMap(getHeaders(req), sb);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private void printMap(Map<String, String> map, StringBuilder sb) {
        map.forEach((k, v) -> sb.append(k).append(": ").append(v).append("\n"));
    }

    private TreeMap<String, String> getHeaders(HttpServletRequest req) {
        TreeMap<String, String> headers = new TreeMap<>();
        req.getHeaderNames().asIterator().forEachRemaining(h -> headers.put(h, req.getHeader(h)));
        return headers;
    }
}
