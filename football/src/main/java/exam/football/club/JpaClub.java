package exam.football.club;

import org.springframework.data.jpa.repository.JpaRepository;

public interface  JpaClub extends JpaRepository<Club, Integer> {
    Club findByName(String name);
    
}
