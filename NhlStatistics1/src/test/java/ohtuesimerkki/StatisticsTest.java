/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtuesimerkki;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author ihqsanna;
 */
public class StatisticsTest {
    
    Reader readerStub = new Reader() {
 
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();
 
            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));
 
            return players;
        }
    };
 
    Statistics stats;

    @Before
    public void setUp(){
        // luodaan Statistics-olio joka käyttää "stubia"
        stats = new Statistics(readerStub);
    }  
    
    @Test
    public void findCorrectPlayer() {
        
        assertEquals(stats.search("Semenko").toString(), readerStub.getPlayers().get(0).toString());
    }
    @Test
    public void dontFindPlayer() {
        assertNull(stats.search("Sanna"));
    }
    
    @Test
    public void correctTeam() {
        List<Player> players2 = new ArrayList<Player>();
        
        for (Player player : readerStub.getPlayers()) {
            if (player.getTeam().equals("EDM")) {
                players2.add(player);
            }
            
        }
        assertTrue(stats.team("EDM").equals(players2));
    }
    
    @Test
    public void bestPlayers() {
        
        assertTrue(stats.topScorers(4).get(0).equals(readerStub.getPlayers().get(4)));
        
    }
    
    
}
