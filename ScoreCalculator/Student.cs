namespace ScoreCalculator;

public class Student
{
    public Student(string firstName, string lastName, int studentNumber)
    {
        FirstName = firstName;
        LastName = lastName;
        StudentNumber = studentNumber;
    }

    public string FirstName { get; }
    public string LastName { get; }
    public int StudentNumber { get; }
}