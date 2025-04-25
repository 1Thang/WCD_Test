package exam.football.player;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import exam.football.club.JpaClub;
@Controller
@RequestMapping("/players")
public class PlayerController {

    @Autowired
    private JpaPlayer jpaPlayer;
@Autowired
private JpaClub jpaClub;

    // Display list of all players
    @GetMapping("/list")
    public String getAllPlayers(Model model) {
        List<Player> players = jpaPlayer.findAll();
        
        model.addAttribute("players", players);
        model.addAttribute("clubs", jpaClub.findAll());
        return "PlayerList/list";  // Updated to PlayerList folder
    }

    // Show the form to add a new player
    @GetMapping("/add")
    public String showAddPlayerForm(Model model) {
        model.addAttribute("player", new Player());
        model.addAttribute("clubs", jpaClub.findAll()); 
        return "PlayerList/add";  // Updated to PlayerList folder
    }

    // Handle the form submission to add a new player
    @PostMapping("/add")
    public String addPlayer(@ModelAttribute("player") Player player, BindingResult result, Model model) {
        if (player.getName().length() < 2 || player.getName().length() > 32) {
            result.rejectValue("name", "error.name", "Name must be between 2 and 32 characters.");
        }
        if (player.getWeight() < 55 || player.getWeight() > 85) {
            result.rejectValue("weight", "error.weight", "Weight must be between 55 and 85 kg.");
        }
        if (player.getHeight() < 1.65 || player.getHeight() > 1.85) {
            result.rejectValue("height", "error.height", "Height must be between 1.65 and 1.85 meters.");
        }
    
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "Validation failed. Please correct the errors and try again.");
            return "error"; // Redirect to the error page
        }
    
        try {
            jpaPlayer.save(player); // Save the player to the database
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while saving the player. Please try again.");
            return "error"; // Redirect to the error page
        }
    
        return "redirect:/players/list"; // Redirect to the player list after adding
    }

    // Show the form to edit an existing player
    @GetMapping("/edit/{id}")
    public String showEditPlayerForm(@PathVariable int id, Model model) {
        Player player = jpaPlayer.findById(id).orElse(null);
        model.addAttribute("clubs", jpaClub.findAll());

        if (player == null) {
            return "redirect:/players/list";  // Redirect if player not found
        }
        model.addAttribute("player", player);
        return "PlayerList/edit";  // Updated to PlayerList folder
    }

    // Handle the form submission to edit an existing player
    @PostMapping("/edit/{id}")
    public String editPlayer(@PathVariable int id, @ModelAttribute("player") Player player, BindingResult result, Model model) {
        // Manually validate the fields
        if (player.getName().length() < 2 || player.getName().length() > 32) {
            result.rejectValue("name", "error.name", "Name must be between 2 and 32 characters.");
        }
        if (player.getWeight() < 55 || player.getWeight() > 85) {
            result.rejectValue("weight", "error.weight", "Weight must be between 55 and 85 kg.");
        }
        if (player.getHeight() < 1.65 || player.getHeight() > 1.85) {
            result.rejectValue("height", "error.height", "Height must be between 1.65 and 1.85 meters.");
        }
    
        if (result.hasErrors()) {
            model.addAttribute("errorMessage", "Validation failed. Please correct the errors and try again.");
            return "error"; // Redirect to the error page
        }
    
        player.setId(id); // Ensure the ID remains unchanged
        try {
            jpaPlayer.save(player); // Update the player in the database
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while saving the player. Please try again.");
            return "error"; // Redirect to the error page
        }
    
        return "redirect:/players/list"; // Redirect to the player list after updating
    }

    // Delete a player by ID
    @GetMapping("/delete/{id}")
    public String deletePlayer(@PathVariable int id) {
        jpaPlayer.deleteById(id);  // Delete the player from the database
        return "redirect:/players/list";  // Redirect to the player list
    }

    @GetMapping("/search")
    public String searchPlayers(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "clubId", required = false) Integer clubId,
            @RequestParam(value = "active", required = false) Boolean active,
            Model model) {
        
        List<Player> players = jpaPlayer.findAll(); // Start with all players
    
        // Filter by name if provided
        if (name != null && !name.isEmpty()) {
            players = players.stream()
                    .filter(player -> player.getName().toLowerCase().contains(name.toLowerCase()))
                    .toList();
        }
    
        // Filter by club if provided
        if (clubId != null) {
            players = players.stream()
                    .filter(player -> player.getClub().getId() == clubId)
                    .toList();
        }
    
        // Filter by active status if provided
        if (active != null) {
            players = players.stream()
                    .filter(player -> player.isActive() == active)
                    .toList();
        }
    
        model.addAttribute("players", players);
        model.addAttribute("clubs", jpaClub.findAll()); // Pass clubs for dropdown
        return "PlayerList/list"; // Reuse the list view
    }
}

