package com.example.player.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import com.example.player.repository.PlayerRepository;
import com.example.player.repository.PlayerJpaRepository;
import com.example.player.model.Player;
import java.util.*;

@Service 
public class PlayerJpaService implements PlayerRepository 
{
    @Autowired 
    private PlayerJpaRepository playerJpaRepository;

    @Override 
    public ArrayList<Player> getPlayers()
    {
        List<Player> playerList=playerJpaRepository.findAll();
        ArrayList<Player> players=new ArrayList<>(playerList);
        return players;
    }

    @Override 
    public Player getPlayerById(int playerId)
    {
        try 
        {
            Player team =playerJpaRepository.findById(playerId).get();
            return team;
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @Override 
    public Player addPlayer(Player team)
    {
        playerJpaRepository.save(team);
        return team;
    }
    @Override 
    public Player updatePlayer(int playerId, Player team)
    {
        try 
        {
            Player newPlayer=playerJpaRepository.findById(playerId).get();
            if(team.getPlayerName()!=null) 
            {
                newPlayer.setPlayerName(team.getPlayerName());
            }
            if(team.getJerseyNumber()>0) 
            {
               newPlayer.setJerseyNumber(team.getJerseyNumber()); 
            }
            if(team.getRole()!=null)
            {
                newPlayer.setRole(team.getRole());
            }
            playerJpaRepository.save(newPlayer);
            return newPlayer;
        }
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override 
    public void deletePlayer(int playerId)
    {
        try 
        {
            playerJpaRepository.deleteById(playerId);
        }  
        catch(Exception e)
        {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }


}
