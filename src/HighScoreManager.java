import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HighScoreManager {
	private static final String HIGHSCORE_FILE = "highscore.txt";
	private int highScore;
	//default constructor that reads previous stored file with the highscore
	public HighScoreManager() {
		highScore = loadHighScore();
	}
	//what reads the file
	public int loadHighScore() {
        try (BufferedReader reader = new BufferedReader(new FileReader(HIGHSCORE_FILE))) {
            String line = reader.readLine();
            if (line != null) {
                return Integer.parseInt(line);
            }
        } catch (IOException e) {
            // do stuff with exception
            e.printStackTrace();
        }
        return 0; // last 
    }
//stores the high score into the file
    public void saveHighScore(int score) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(HIGHSCORE_FILE))) {
            writer.write(String.valueOf(score));
        } catch (IOException e) {
            // Handle file IO exception
            e.printStackTrace();
        }
    }
//getter
    public int getHighScore() {
        return highScore;
    }
//condition for updating
    public void updateHighScore(int wins) {
        if (wins > highScore) {
            highScore = wins;
            saveHighScore(highScore);
        }
    }
	
}
