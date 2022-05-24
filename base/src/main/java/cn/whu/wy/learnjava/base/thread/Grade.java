package cn.whu.wy.learnjava.base.thread;

/**
 * 学生各科成绩
 * <p>
 * Author WangYong
 * Date 2020/08/31
 * Time 10:36
 */
public class Grade {
    int sno;

    double chineseScore;
    double mathScore;
    double englishScore;

    double avg = 0;

    public Grade(int sno, double chineseScore, double mathScore, double englishScore) {
        this.sno = sno;
        this.chineseScore = chineseScore;
        this.mathScore = mathScore;
        this.englishScore = englishScore;
    }
}
