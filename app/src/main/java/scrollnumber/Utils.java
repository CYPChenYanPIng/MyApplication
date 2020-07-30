package scrollnumber;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * @author chenyanping
 * @date 2020-06-29
 */
public class Utils {
    /**
     * 格式化
     */
    private static DecimalFormat dfs = null;
    public static DecimalFormat format(String pattern) {
        if (dfs == null) {
            dfs = new DecimalFormat();
        }
        dfs.setRoundingMode(RoundingMode.FLOOR);
        dfs.applyPattern(pattern);
        return dfs;
    }
}
