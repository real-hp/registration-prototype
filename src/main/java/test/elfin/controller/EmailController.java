package test.elfin.controller;

import org.springframework.http.MediaType;
import test.elfin.model.Email;

import test.elfin.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "email", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class EmailController {


    private final EmailService emailService;


    @GetMapping
    public ResponseEntity<Iterable<Email>> getAllVerifEmail() {
        return ResponseEntity.ok(emailService.getAllEmail());
    }

    @PostMapping
    public ResponseEntity<Boolean> createVerifEmail(@RequestParam String email) {
        return ResponseEntity.ok(emailService.createEmail(email));
    }

    @DeleteMapping
    public ResponseEntity<Boolean> deleteAllEmails() {
        return ResponseEntity.ok(emailService.deleteAllEmail());
    }


}
