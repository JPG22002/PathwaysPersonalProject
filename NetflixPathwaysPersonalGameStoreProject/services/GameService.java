import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VideoGameService {

    @Autowired
    private GameRepository gameRepo;

    public List<Game> getAllGames() {
        return gameRepo.findAll();
    }

    public Game getGameById(long id) {
        return gameRepo.findById(id).orElseThrow(() -> new NotFoundException("Game not found with id: " + id));
    }

    public Game createGame(Game game) {
        return gameRepo.save(game);
    }

    public Game modifyGame(long id, Game updatedGame) {
        Game existingGame = getGameById(id);
        existingGame.setTitle(updatedGame.getTitle());
        existingGame.setEsrbRating(updatedGame.getEsrbRating());
        // Additional fields to be updated can be added here
        return gameRepo.save(existingGame);
    }

    public void removeGame(long id) {
        gameRepo.deleteById(id);
    }
}
