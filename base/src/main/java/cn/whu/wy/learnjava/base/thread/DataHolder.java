package cn.whu.wy.learnjava.base.thread;

import cn.whu.wy.learnjava.base.jdbc.DBHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * 数据存储器，发挥类似数据库或内存表的作用
 * <p>
 * Author WangYong
 * Date 2020/08/31
 * Time 10:34
 */
public class DataHolder {
    // 存储是全体学生的成绩
    private Map<Integer, Grade> gradeMap = new HashMap<>();

    // 记录学生成绩已被处理（计算平均分）的学号
    private List<Integer> processedList = new ArrayList<>();

    public DataHolder() {
        initTestData();
    }

    public Map<Integer, Grade> getGradeMap() {
        return gradeMap;
    }

    public List<Integer> getProcessedList() {
        return processedList;
    }

    /**
     * 初始化测试数据
     */
    private void initTestData() {
        Random random = new Random();
        for (int i = 1; i <= 100; i++) {
            int sno = 2020_0000 + i;
            double chineseScore = 100 + random.nextInt(30) + random.nextInt(2) * 0.5;
            double mathScore = 100 + random.nextInt(45) + random.nextInt(2) * 0.5;
            double englishScore = 100 + random.nextInt(40) + random.nextInt(2) * 0.5;

            gradeMap.put(sno, new Grade(sno, chineseScore, mathScore, englishScore));
        }
    }

    public static void main(String[] args) {
        String sql = "SELECT id, city, weather FROM weather";
        ResultSet rs = DBHelper.getInstance().query(sql);
        // 展开结果集数据库
        try {
            while (rs.next()) {
                // 通过字段检索
                int id = rs.getInt("id");
                String name = rs.getString("city");
                String url = rs.getString("weather");

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 城市: " + name);
                System.out.print(", 天气: " + url);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
