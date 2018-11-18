package ohtu;

public class Submission {
    private int week;
    private int hours;
    private String course;
    private int[] exercises;
    
    public Submission() {
        this.week = -1;
        this.hours = -1;
        this.course = "";
        this.exercises = new int[50];
                
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int[] getExercises() {
        return exercises;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }
    
    public String getTehtavaMuotoilu() {
       
        int[] tehtavat = getExercises();
        String muotoilu = tehtavat[0] + "";
        for (int i = 1; i < tehtavat.length; i ++) {
            muotoilu = muotoilu + ", " + tehtavat[i];
        }
        return muotoilu;
    }
    public int montaTehtavaa() {
        int[] tehtavat = getExercises();
        int maara = tehtavat.length;
        return maara;
    }
    
    
    @Override
    public String toString() {
        return getCourse() + ", viikko "+ getWeek() + " tehtyjä "
                + "tehtäviä yhteensä " + montaTehtavaa() + " tehdyt tehtävät: " + getTehtavaMuotoilu();
    }
    
}