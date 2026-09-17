// Abstraction implementation
public abstract class AbstractReport implements Reportable {
    protected String reportTitle; //// 'protected' use koray ei class ebong child class theke access kora jabe

    public AbstractReport(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    public abstract void printHeader();//Report-er header print korar jonno abstract method Child class-e ei method-er implementation dite hobe
}