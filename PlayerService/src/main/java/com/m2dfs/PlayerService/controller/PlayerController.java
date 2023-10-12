package com.m2dfs.PlayerService.controller;

import com.m2dfs.PlayerService.domain.Player;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/players")
public class PlayerController {

    private static Map<Integer, Player> playerDB = new HashMap<>();

    static {
        List<Player> lst = new ArrayList<>();
        Player player1 = new Player(1, "Toto", "OK");
        Player player2 = new Player(2, "Titi", "Bléssé");
        Player player3 = new Player(3, "Toto", "OK");
        Player player4 = new Player(4, "Titi", "Exclu");

        playerDB.put(player1.getId(), player1);
        playerDB.put(player2.getId(), player2);
        playerDB.put(player3.getId(), player3);
        playerDB.put(player4.getId(), player4);
    }

    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable int id) {
        return playerDB.get(id);
    }

    @PostMapping
    public Player addPlayer(@RequestBody Player player) {
        playerDB.put(player.getId(), player);
        return player;
    }

    @PutMapping("/{id}")
    public Player updatePlayer(@PathVariable int id, @RequestBody Player player) {
        playerDB.put(id, player);
        return player;
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable int id) {
        playerDB.remove(id);
    }

    @GetMapping
    public List<Player> getAllPlayers() {
        return new ArrayList<>(playerDB.values());
    }
}



