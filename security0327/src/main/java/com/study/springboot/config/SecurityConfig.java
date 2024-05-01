package com.study.springboot.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
//@Configuration 어노테이션이 적용된 클래스는 스프링 컨테이너에서 빈(Bean)으로 관리됨.
// 스프링 컨테이너는 이 클래스에서 정의된 빈들을 생성하고, 의존성 주입 등의 관리를 수행함.
@EnableWebSecurity
// WebSecurityConfigurerAdapter 클래스를 상속받은 구성 클래스 내에서는 configure() 메서드를 재정의하여
// 웹 보안 구성을 변경할 수 있음. 이 메서드에서는 인증, 권한 부여, 접근 제어 등을 구성할 수 있음.
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //HTTP 요청에 대한 보안 규칙을 설정하는 데 사용
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/member/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/**").permitAll()
                .and()
                .formLogin();
                //.loginPage("/login");
        //.antMatchers() 메서드는 URL 패턴을 지정하고 해당 패턴에 대한 권한을 설정하는 데 사용
        // "/admin/" URL 패턴에 대한 권한은 "ADMIN" 권한을 가진 사용자만 접근할 수 있도록 설정
        // "/member/" URL 패턴에 대한 권한은 "USER" 또는 "ADMIN" 권한을 가진 사용자만 접근할 수 있도록 설정
        //.antMatchers("/**").permitAll()는 다른 모든 URL 패턴에 대해 모든 사용자가 접근할 수 있도록 허용
        //.formLogin()은 로그인 폼을 활성화하고, 로그인 페이지를 지정하는 데 사용
        // 이 경우 "/login" 페이지가 로그인 페이지로 설정됩니다.
        //마지막으로 .anyRequest().authenticated()를 사용하여, 인증된 사용자만이
        // 다른 모든 요청에 접근할 수 있도록 설정 (위 코드에는 생략되어 있음)

    }

    //메서드에 @Autowired를 붙이면, 해당 메서드에서 필요로 하는 빈(bean)을 자동으로 주입받아서 사용할 수 있음.
    // 아래의 configureGlobal() 메서드는 AuthenticationManagerBuilder 객체를 인자로 받아야 하는데, @Autowired를 사용하여
    // AuthenticationManagerBuilder 객체를 자동으로 주입받을 수 있음.

    //즉, @Autowired를 사용하면 코드 상에서 객체를 생성하거나 초기화하지 않아도, Spring이 자동으로 필요한 객체를 생성하고, 의존성을 주입해줌.
    //이는 코드를 간결하고, 유지보수를 쉽게 해주는 장점이 있음.
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("1234")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder().encode("1234")).roles("ADMIN");
        //inMemoryAuthentication()을 호출하여 메모리에 저장된 사용자 정보를 이용하여 인증 처리를 수행하도록 설정
        // 사용자 정보는 withUser() 메서드를 호출하여 추가할 수 있음.
        // password() 메서드는 해당 사용자의 비밀번호를 설정하며, {noop}은 암호화 방식을 나타낼 수 있음.
        // noop 방식을 사용하여 암호화를 수행하지 않도록 설정할 수도 있음. roles() 메서드는 해당 사용자의 권한 정보를 설정
        //password("{noop}1234") 사용해 볼 것
    }

    //@Bean 어노테이션이 사용된 것은, 해당 메서드가 빈으로 등록되어야 함을 나타냄. 
    // passwordEncoder() 메서드는 BCryptPasswordEncoder 객체를 반환함.
    // 이 메서드를 호출하여 BCryptPasswordEncoder 객체를 얻을 수 있음.
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
