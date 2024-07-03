package es.guesstheword.repository;

import es.guesstheword.entity.Leaderboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaderboardRepo extends JpaRepository<Leaderboard, Integer> {
    Leaderboard findLeaderboardByUserId(int id);
}
