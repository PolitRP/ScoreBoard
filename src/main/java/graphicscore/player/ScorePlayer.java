package graphicscore.player;

import cn.nukkit.Player;
import graphicscore.api.ScoreFactory;
import graphicscore.placeholder.PlaceholderAPI;
import graphicscore.scoreboard.Scoreboard;

import java.util.HashMap;
import java.util.Map;

public class ScorePlayer {
    private final Player player;
    private Scoreboard scoreboard;

    public ScorePlayer(Player player, Scoreboard scoreboard) {
        this.player = player;
        this.scoreboard = scoreboard;
        refresh();
    }

    public void update(String params) {
        Integer line = scoreboard.getPlaceholderParamsLine(params);
        if (line == null) return;
        ScoreFactory.editLine(player, line, PlaceholderAPI.setPlaceholders(player, scoreboard.getLines().get(line)));
    }

    private void refresh() {
        Map<Integer, String> lines = new HashMap<>();
        for (int i = 0; i < scoreboard.getLines().size(); i++) {
            lines.put(i, PlaceholderAPI.setPlaceholders(player, scoreboard.getLines().get(i)));
        }

        ScoreFactory.sendScore(player, scoreboard.getTitle());
        ScoreFactory.setLines(player, lines);
    }

    public void changeScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
        ScoreFactory.removeToPlayer(player);
        refresh();
    }
}


