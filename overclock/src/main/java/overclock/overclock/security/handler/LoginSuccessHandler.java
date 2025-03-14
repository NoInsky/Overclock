package overclock.overclock.security.handler;

import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import overclock.overclock.security.dto.AuthMemberDTO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Log4j2
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
  private PasswordEncoder encoder;

  public LoginSuccessHandler(PasswordEncoder enc) {
    encoder = enc;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
      Authentication authentication) throws IOException, ServletException {
    log.info("onAuthenticationSuccess");
    AuthMemberDTO dto = (AuthMemberDTO) authentication.getPrincipal();
    boolean fromSocial = dto.isAuth();
    log.info("isFromSocial : " + fromSocial);
    boolean passResult = encoder.matches("12", dto.getPassword());
    log.info("fromSocial && passResult: " + (fromSocial && passResult));
    if (fromSocial && passResult) {
      redirectStrategy.sendRedirect(request, response, "/member/modify?from=auth");
      return;
    }
    List<String> roleNames = new ArrayList<>();
    dto.getAuthorities().forEach(new Consumer<GrantedAuthority>() {
      @Override
      public void accept(GrantedAuthority t) {
        roleNames.add(t.getAuthority());
      }
    });
    String forward = "";
    System.out.println(">>>" + roleNames);
    if (roleNames.contains("ROLE_MEMBER"))
      forward = "/sample/all";
    if (roleNames.contains("ROLE_ADMIN"))
      forward = "/sample/admin";
    redirectStrategy.sendRedirect(request, response, forward);
  }
}
