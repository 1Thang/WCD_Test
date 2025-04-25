package exam.football.player;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import exam.football.club.Club;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@Entity 
@Getter
@Setter
public class Player {
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 2, max = 32, message = "Name should be between 2 and 32 characters.")
    private String name;

    @Min(55)
    private int weight;

    @DecimalMin("1.65")
    private float height;

    @Pattern(regexp = "A|B|O", message = "Blood type must be A, B, or O.")
    private String bloodType;

    private boolean active;

    @Past
     @DateTimeFormat(pattern = "yyyy-MM-dd") 
    private Date dateOfBirth;

        @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;
}
