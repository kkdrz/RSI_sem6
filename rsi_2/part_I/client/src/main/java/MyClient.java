public class MyClient {
    public static void main(String[] args) {
        double wynik;
        CalcObject zObiekt;
        CalcObject2 zObiekt2;
        ResultType wynik2;
        InputType inObj;

        if (args.length < 2) {
            System.out.println("You have to enter RMI objects addresses in the form: //host_address/service_name //host_address2/service_name2");
            return;
        }

        String adres = args[0];
        String adres2 = args[1];

//        use this if needed
//        if (System.getSecurityManager() == null)
//            System.setSecurityManager(new SecurityManager());

        try {
            zObiekt = (CalcObject) java.rmi.Naming.lookup(adres);
            zObiekt2 = (CalcObject2) java.rmi.Naming.lookup(adres2);
        } catch (Exception e) {
            System.out.println("Nie mozna pobrac referencji do " + adres + " lub " + adres2);
            e.printStackTrace();
            return;
        }
        System.out.println("Referencja do " + adres + " i " + adres2 + " jest pobrana.");

        try {
            wynik = zObiekt.calculate(1.1, 2.2);
            inObj = new InputType();
            inObj.x1 = 1.1;
            inObj.x2 = 2.2;
            inObj.operation = "add";
            wynik2 = zObiekt2.calculate(inObj);
        } catch (Exception e) {
            System.out.println("Blad zdalnego wywolania");
            e.printStackTrace();
            return;
        }
        System.out.println("Wynik = " + wynik);
        System.out.println("Wynik2 = " + wynik2.result + " Komentarz: " + wynik2.result_description);
        return;
    }
}
