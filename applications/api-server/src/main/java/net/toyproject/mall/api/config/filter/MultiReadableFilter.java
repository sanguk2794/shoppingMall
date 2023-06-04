package net.toyproject.mall.api.config.filter;

import org.apache.commons.io.IOUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.*;

/**
 * Wrap with Multi-Readable request in Filter. To allow the requestBody to be read over and over again.
 */
public class MultiReadableFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
            throws IOException, ServletException {
        
        final MultiReadableHttpServletRequest multiReadableRequest = new MultiReadableHttpServletRequest((HttpServletRequest) request);
        chain.doFilter(multiReadableRequest, response);
    }

    @Override
    public void destroy() {
        
    }
    
    public static class MultiReadableHttpServletRequest extends HttpServletRequestWrapper {
        
        private ByteArrayOutputStream cachedBytes;
        
        public MultiReadableHttpServletRequest(HttpServletRequest request) {
            super(request);
        }

        @Override
        public ServletInputStream getInputStream() throws IOException {
            if (cachedBytes == null) {
                cacheInputStream();
            }

            return new CachedServletInputStream();
        }

        @Override
        public BufferedReader getReader() throws IOException {
            return new BufferedReader(new InputStreamReader(getInputStream()));
        }

        private void cacheInputStream() throws IOException {
            cachedBytes = new ByteArrayOutputStream();
            IOUtils.copy(super.getInputStream(), cachedBytes);
        }
        
        public class CachedServletInputStream extends ServletInputStream {
            
            private final ByteArrayInputStream input;

            public CachedServletInputStream() {
                input = new ByteArrayInputStream(cachedBytes.toByteArray());
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener listener) {
            }

            @Override
            public int read() {
                return input.read();
            }
        }
    }
}
