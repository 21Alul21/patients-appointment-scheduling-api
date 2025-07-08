@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity u = repo.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new User(u.getEmail(), u.getPassword(),
            List.of(new SimpleGrantedAuthority("ROLE_" + u.getRole().name())));
    }
}
