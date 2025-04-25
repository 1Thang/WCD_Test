package exam.football.player;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface  JpaPlayer extends JpaRepository<Player, Integer> {
    Player findByName(String name); 
    List<Player> findByNameContainingIgnoreCase(String name);
}
