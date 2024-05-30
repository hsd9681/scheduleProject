package org.sparta.scheduleproject.service;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.sparta.scheduleproject.dto.LoginRequestDto;
import org.sparta.scheduleproject.dto.LoginResponseDto;
import org.sparta.scheduleproject.dto.SignupRequestDto;
import org.sparta.scheduleproject.dto.SignupResponseDto;
import org.sparta.scheduleproject.entity.User;
import org.sparta.scheduleproject.entity.UserRoleEnum;
import org.sparta.scheduleproject.exception.DuplicateUsernameException;
import org.sparta.scheduleproject.exception.LoginFailedException;
import org.sparta.scheduleproject.jwt.JwtUtil;
import org.sparta.scheduleproject.repository.UserRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    public UserService(UserRepository userRepository,JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    public SignupResponseDto signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new DuplicateUsernameException("중복된 username 입니다", HttpServletResponse.SC_BAD_REQUEST);
        }

        // email 중복확인
        String email = requestDto.getEmail();
        Optional<User> checkEmail = userRepository.findByEmail(email);
        if (checkEmail.isPresent()) {
            throw new IllegalArgumentException("중복된 Email 입니다.");
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록
        User user = new User(username, password, email, role);
        User usersave = userRepository.save(user);
        return new SignupResponseDto(usersave);
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto, HttpServletResponse res) {
        String username = loginRequestDto.getUsername();
        String password = loginRequestDto.getPassword();

        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new LoginFailedException("회원을 찾을 수 없습니다", HttpServletResponse.SC_BAD_REQUEST)
        );
        if (!password.equals(user.getPassword())) {
            throw new LoginFailedException("비밀번호가 틀렸습니다", HttpServletResponse.SC_BAD_REQUEST);
        }


        String token = jwtUtil.createToken(user.getUsername(),user.getRole());
        jwtUtil.addJwtToCookie(token, res);

        log.info("로그인 성공: {}님", user.getUsername());

        return new LoginResponseDto(user);
    }
}
