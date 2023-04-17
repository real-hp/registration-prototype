package test.elfin.service;

import test.elfin.model.User;
import test.elfin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final RuntimeService runtimeService;

    public String registerUser(User user) {
        checkUser(user);

        Map<String, Object> userVariables = new HashMap<>();

        user.setRequestTime(Instant.now());
        userVariables.put("user", user);

        runtimeService.startProcessInstanceByKey("registration", userVariables);
        return "Заявка на регистрацию отправлена";
    }


    public void checkUser(User user) {
        boolean isLoginUsed = userRepository.findByLogin(user.getLogin()).isPresent();
        boolean isEmailUsed = userRepository.findByEmail(user.getEmail()).isPresent();

        if (isLoginUsed || isEmailUsed) {
            throw new RuntimeException("Логин или емайл уже используются");
        }
    }

    public List<User> getAllRequest() {
        return userRepository.findAll();
    }
}
