package test.elfin.model;

import lombok.Data;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;


@Data
@Entity
@Table(name = "user_request")
public class User implements Serializable {

    @Id
    @GeneratedValue
    @Nullable
    private int id;

    @Column(unique = true)
    private String login;

    @Column(unique = true)
    private String email;

    private boolean confirmed = false;

    private String password;

    private Instant requestTime;
}
