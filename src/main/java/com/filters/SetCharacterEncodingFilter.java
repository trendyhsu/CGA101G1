package com.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;



public class SetCharacterEncodingFilter implements Filter {
    // ----------------------------------------------------- Instance Variables
    /**
     * The default character encoding to set for requests that pass through
     * this filter.
     */
    protected String encoding = null;
    /**
     * The filter configuration object we are associated with.  If this value
     * is null, this filter instance is not currently configured.
     */
    protected FilterConfig filterConfig = null;
    /**
     * Should a character encoding specified by the client be ignored?
     */
    protected boolean ignore = true;
    // --------------------------------------------------------- Public Methods
 
    @Override
    public void destroy() {

        this.encoding = null;
        this.filterConfig = null;

    }
   
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain)
        throws IOException, ServletException {

        // Conditionally select and set the character encoding to be used
        if (ignore || (request.getCharacterEncoding() == null)) {
            String characterEncoding = selectEncoding(request);
            if (characterEncoding != null)
                request.setCharacterEncoding(characterEncoding);
        }

        // Pass control on to the next filter
        chain.doFilter(request, response);

    }

    @Override
    public void init(FilterConfig fConfig) throws ServletException {

        this.filterConfig = fConfig;
        this.encoding = fConfig.getInitParameter("encoding");
        String value = fConfig.getInitParameter("ignore");
        if (value == null)
            this.ignore = true;
        else if (value.equalsIgnoreCase("true"))
            this.ignore = true;
        else if (value.equalsIgnoreCase("yes"))
            this.ignore = true;
        else
            this.ignore = false;
    }

    // ------------------------------------------------------ Protected Methods

    protected String selectEncoding(ServletRequest request) {

        return (this.encoding);

    }


}
