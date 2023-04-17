package test.elfin.delegate;

import test.elfin.model.User;
import test.elfin.repository.EmailRepository;
import lombok.AllArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CheckDomainDelegate implements JavaDelegate {

    public final EmailRepository emailRepository;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        User user = (User) delegateExecution.getVariable("user");

        String emailDomain = user.getEmail().split("@")[1];
        boolean present = emailRepository.findByName(emailDomain).isPresent();

        delegateExecution.setVariable("isValidEmail", present);
    }
}
