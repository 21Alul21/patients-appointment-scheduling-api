@Component
public class SubscriptionCheckFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    public SubscriptionCheckFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Authenticate the user using your JWT logic
        UserEntity user = null;
        try {
            user = jwtUtils.authenticateUser(request);
        } catch (Exception e) {
            
            filterChain.doFilter(request, response);
            return;
        }

        if (user != null && user.getOrganization() != null) {
            int duration = user.getOrganization().getSubscriptionDuration();

            if (duration <= 0) {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                response.setContentType("application/json");
                response.getWriter().write("{\"error\": \"Your organization's subscription has expired.\"}");
                return;
            }
        }

        filterChain.doFilter(request, response); // continue if subscription is valid
    }
}
