public class main {
    public static void main(String[] args){
        SessionService ss = new SessionService(Integer.parseInt(args[0]));
        ss.startGenericSession();
    }
}
