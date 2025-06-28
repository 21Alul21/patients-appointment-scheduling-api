@Configuration
public class PasswordEncryptionConfig{
  @Bean
  public PasswordEncoder passwordEncoder(){
      return new BcryptPasswordEncoder();
    {

}
