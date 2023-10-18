using Newtonsoft.Json;

namespace ScoreCalculator;

public class StudentReader
{
    public StudentReader(string studentsFilePath, string scoresFilePath)
    {
        var students = JsonConvert.DeserializeObject<List<Student>>(File.ReadAllText(studentsFilePath));
        var scores = JsonConvert.DeserializeObject<List<StudentScore>>(File.ReadAllText(scoresFilePath));
        var topStudents =
            (from score in scores
                group score by new { score.StudentNumber }
                into groupedScore
                join student in students on groupedScore.Key.StudentNumber equals student.StudentNumber
                select new
                {
                    student.FirstName,
                    student.LastName,
                    GPA = groupedScore.Average(s => s.Score)
                }
            )
            .OrderByDescending(s => s.GPA)
            .Take(3);
        foreach (var student in topStudents)
        {
            Console.WriteLine(student);
        }
    }
}