package test.elfin.service;

import test.elfin.model.Email;
import test.elfin.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class EmailService {

    @Value("${mails.path}")
    private String emailsPath;

    private final EmailRepository emailRepository;


    @EventListener(ApplicationReadyEvent.class)
    public void saveAllEmail() {
        Set<Email> emails = getEmailFromFile();
        for (Email e : emails) {
            if (!emailRepository.findByName(e.getName()).isPresent()) {
                emailRepository.save(e).getId();
            }
        }
    }

    public Set<Email> getEmailFromFile() {
        Set<Email> emails = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(emailsPath))) {
            String mail;
            while ((mail = br.readLine()) != null) {
                emails.add(Email.builder().name(mail).build());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return emails;
    }

    public Iterable<Email> getAllEmail() {
        return emailRepository.findAll();
    }

    public Boolean createEmail(String emailDomen) {
        if (emailRepository.findByName(emailDomen).isPresent()) return false;
        return emailRepository.save(Email.builder().name(emailDomen).build()) != null;
    }

    public boolean deleteAllEmail() {
        emailRepository.deleteAll();
        return true;
    }
}
