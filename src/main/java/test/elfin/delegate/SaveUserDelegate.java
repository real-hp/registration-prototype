package test.elfin.delegate;

import at.favre.lib.crypto.bcrypt.BCrypt;
import test.elfin.model.User;
import test.elfin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveUserDelegate implements JavaDelegate {

    private final UserRepository userRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        User user = (User) delegateExecution.getVariable("user");
        String encodedPassword = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }
}
