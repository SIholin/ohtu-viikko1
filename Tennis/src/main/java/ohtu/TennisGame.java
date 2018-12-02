package ohtu;

public class TennisGame {

    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if ("player1".equals(playerName)) {
            m_score1 += 1;
        } else {
            m_score2 += 1;
        }
    }

    public String itIsDraw() {
        if (null == playersPoints(m_score1)) {
            return "Deuce";
        } else {
            return playersPoints(m_score1) + "-All";
        }
    }

    public String whoIsTheWinner() {
        int minusResult = m_score1 - m_score2;
        if (minusResult == 1) {
            return "Advantage player1";
        } else if (minusResult == -1) {
            return "Advantage player2";
        } else if (minusResult >= 2) {
            return "Win for player1";
        } else {
            return "Win for player2";
        }
    }

    public String playersPoints(int points) {
        switch (points) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
        }
        return null;
    }

    public String stillGoing() {
        return playersPoints(m_score1) + "-" + playersPoints(m_score2);
    }

    public String getScore() {
        if (m_score1 == m_score2) {
            return itIsDraw();
        } else if (m_score1 >= 4 || m_score2 >= 4) {
            return whoIsTheWinner();
        } else {
            return stillGoing();
        }
    }
}
