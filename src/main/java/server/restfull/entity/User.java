package server.restfull.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity(name = "usersdetails")
@Table(name = "usersdetails")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private String id;
    private String email;
    private String phone;
    private String source;

    public String toString() {
        return this.id + ',' + this.getEmail() + ',' + this.getPhone() + ',' + this.getSource();
    }
};