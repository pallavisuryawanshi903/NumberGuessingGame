package numberguessinggame;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class GameDAO {

    public static void saveResult(String name, int attempts, int score) {
        try {
            Connection con = DBConnection.getConnection();
            String sql = "INSERT INTO game_result(player_name, attempts, score)" +
                    " VALUES(?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, attempts);
            ps.setInt(3, score);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
