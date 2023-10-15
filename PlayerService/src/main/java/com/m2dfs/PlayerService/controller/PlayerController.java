package com.m2dfs.PlayerService.controller;

import com.m2dfs.PlayerService.domain.Player;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/players")
@EnableCircuitBreaker
@EnableHystrixDashboard
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

    @ApiOperation(value = "Get player info")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
    @GetMapping("/{id}")
    public Player getPlayerById(@PathVariable int id) {
        return playerDB.get(id);
    }

    @ApiOperation(value = "Add player info")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
    @PostMapping
    public Player addPlayer(@RequestBody Player player) {
        playerDB.put(player.getId(), player);
        return player;
    }

    @ApiOperation(value = "Update player info")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
    @PutMapping("/{id}")
    public Player updatePlayer(@PathVariable int id, @RequestBody Player updatedPlayer) {
        Player existingPlayer = playerDB.get(id);

        // Existing player
        if (existingPlayer == null) {
            throw new IllegalArgumentException("Player " + id + " not found!");
        }

        // Update name
        String updatedName = updatedPlayer.getName();
        if (updatedName != null && !updatedName.isEmpty()) {
            existingPlayer.setName(updatedName);
        }

        // Update status
        String updatedStatus = updatedPlayer.getStatus();
        if (updatedStatus != null && !updatedStatus.isEmpty()) {
            existingPlayer.setStatus(updatedStatus);
        }

        return existingPlayer;
    }


    @ApiOperation(value = "Delete player")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable int id) {
        playerDB.remove(id);
    }

    @ApiOperation(value = "Get list of all players")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 401, message = "Not authorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not found") })
    @GetMapping
    public List<Player> getAllPlayers() {
        return new ArrayList<>(playerDB.values());
    }
}



