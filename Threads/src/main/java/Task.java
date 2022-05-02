import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Task {
    private int job_id;
    private int precision;
    private double pi_value;
    private double error;
    private int n_iteration=0;
    private String format="0.";
    private DecimalFormat df;

    public Task(int job_id, int precision) {
        this.job_id = job_id;
        this.precision = precision;
    }
    private void countError(double pi){
        this.error = Math.abs(pi - Math.PI);
    }

    private void formatError(){
        for(int i=0;i<this.precision;i++){
            this.format+="0";
        }
        df = new DecimalFormat(format);
    }
    public void setThreadNumber(int n_iteration){
        this.n_iteration=n_iteration;
    }
    public Task count(){
        int i=1;
        do{
            this.pi_value += 4*Math.pow(-1,i-1)/(2*i-1);
            countError(this.pi_value);
            i++;
        }while(Math.abs(this.pi_value - Math.PI) > Math.pow(10,-precision));

        formatError();
        System.out.println("Liczenie liczby Pi o job_id "+job_id+" zostało zakończone");
        return this;
    }
    @Override
    public String toString(){
        return "Job_id "+this.job_id+" wartosc PI "+this.pi_value+" o precyzji "+this.precision+" i bledzie "
                +df.format(this.error)+", numer watku "+ this.n_iteration;
    }

}
