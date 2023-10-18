namespace ScoreCalculator;

public class StudentScore
{
    public StudentScore(double score, int studentNumber, string lesson)
    {
        Score = score;
        StudentNumber = studentNumber;
        Lesson = lesson;
    }

    public double Score { get; }
    public int StudentNumber { get; }
    public string Lesson { get; }
}