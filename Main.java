import java.util.Scanner;

public class Main {
    private static GuestList myGuestList;

    public static void main(String[] args) {

        System.out.println("Bun venit! Introduceti numarul de locuri disponibile:");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        myGuestList = new GuestList(n);
        System.out.println("A fost creata lista de comenzi.)");
        System.out.println("");
        System.out.println("========================================");
        String comandaIntrodusa="";

        do{
            comandaIntrodusa = helpMenu();

        }while (
                comandaIntrodusa.compareTo("quit")!=0
        );

    }

    private static String helpMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Asteapta comanda: (help - Afiseaza lista de comenzi)");
        String comandaIntrodusa = sc.next();
        switch (comandaIntrodusa){
            case "help":
                System.out.println("help - Afiseaza aceasta lista de comenzi");
                System.out.println("add - Adauga o noua persoana (inscriere)");
                System.out.println("check - Verifica daca o persoana este inscrisa la eveniment");
                System.out.println("remove - Sterge o persoana existenta din lista");
                System.out.println("update - Actualizeaza detaliile unei persoane");
                System.out.println("guests - Lista de persoane care participa la eveniment");
                System.out.println("waitlist - Persoanele din lista de asteptare");
                System.out.println("available - Numarul de locuri libere");
                System.out.println("guests_no - Numarul de persoane care participa la eveniment");
                System.out.println("waitlist_no - Numarul de persoane din lista de asteptare");
                System.out.println("subscribe_no - Numarul total de persoane inscrise");
                System.out.println("search - Cauta toti invitatii conform sirului de caractere introdus");
                System.out.println("quit - Inchide aplicatia");
                break;
            case "add":
                Adauga();
                break;
            case "check":
                cautareCompleta();
                break;
            case "search":
                cautarePartiala();
                break;
            case "remove":
                Stergere();
                break;
            case "update":
                Update();
                break;
            case "available":
                myGuestList.available();
                break;
            case "guests":
                myGuestList.showParticipants();
                break;
            case "guests_no":
                myGuestList.participantsNumber();
                break;
            case "subscriber_no":
                myGuestList.subscriberNumber();
                break;
            case "waitlist_no":
                myGuestList.waitlistNumber();
                break;
            case "waitlist":
                myGuestList.showWaitlist();
                break;
            case "quit":
                System.out.println("Aplicatia se inchide...");
                break;
        }
        return comandaIntrodusa;
    }
    private static void Adauga(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Se adauga o noua persoana…");
        System.out.println("Introduceti numele de familie:");
        Guest newGuest = new Guest();
        String lastName = sc.nextLine();
        newGuest.setLastName(lastName);
        System.out.println("Introduceti prenumele:");
        String firstName = sc.nextLine();
        newGuest.setFirstName(firstName);
        System.out.println("Introduceti email:");
        String email=sc.nextLine();
        newGuest.setEmail(email);
        System.out.println("Introduceti numar de telefon (format „+40733386463“):");
        String phoneNumber=sc.nextLine();
        newGuest.setPhoneNumber(phoneNumber);
        int addResult=myGuestList.addInGuestList(newGuest);
        if(addResult == -1) {
            System.out.println("Aceasta persoana este deja inscrisa");
        }
        if(addResult == 0) {
            System.out.println(firstName + " " + lastName + ": Felicitari! Locul tau la eveniment este confirmat. Te asteptam!");
        }
        if(addResult>0) {
            System.out.println("Te-ai inscris cu succes in lista de asteptare si ai primit numarul de ordine " + addResult + ". Te vom notifica daca un loc devine disponibil.");
        }
    }
    private static Guest cautareCompleta(){
        Scanner sc = new Scanner(System.in);
        String firstName,lastName,email,phoneNumber;
        Guest guestDeCautat = new Guest();
        Guest guestGasit = null;
        System.out.println("Alege modul de cautare, tastand:");
        System.out.println("\n\t\"1 - Nume si prenume ");
        System.out.println("\n\t\"2 - Email");
        System.out.println("\n\t\"3\" - Numar de telefon (format \"+40733386463\")");
        int comandaIntrodusa = sc.nextInt();


        switch (comandaIntrodusa){
            case 1:
                System.out.println("Introduceti numele de familie:");
                lastName = sc.next();
                guestDeCautat.setLastName(lastName);
                System.out.println("Introduceti prenumele:");
                firstName = sc.next();
                guestDeCautat.setFirstName(firstName);
                guestGasit = myGuestList.SearchGuestInList(guestDeCautat,myGuestList.getParticipants(),1);
                if(guestGasit==null)
                    guestGasit = myGuestList.SearchGuestInList(guestDeCautat,myGuestList.getWaitList(),1);
                System.out.println(guestGasit);
                break;
            case 2:
                System.out.println("Introduceti email-ul:");
                email=sc.nextLine();
                guestDeCautat.setEmail(email);
                guestGasit = myGuestList.SearchGuestInList(guestDeCautat,myGuestList.getParticipants(),2);
                if(guestGasit==null){
                    guestGasit = myGuestList.SearchGuestInList(guestDeCautat,myGuestList.getWaitList(),2);
                }
                System.out.println(guestGasit);
                break;
            case 3:
                System.out.println("Introduceti numar de telefon (format „+40733386463“):");
                phoneNumber=sc.nextLine();
                guestDeCautat.setPhoneNumber(phoneNumber);
                guestGasit = myGuestList.SearchGuestInList(guestDeCautat,myGuestList.getParticipants(),3);
                if(guestGasit==null){
                    guestGasit = myGuestList.SearchGuestInList(guestDeCautat,myGuestList.getWaitList(),3);
                }
                System.out.println(guestGasit);
                break;
        }

        return guestGasit;
    }
    private static void cautarePartiala(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Se realizeaza cautarea partiala:");
        System.out.println("Introduceti cuvantul dorit:");
        String searchWord=sc.nextLine();
        System.out.println(myGuestList.partialSearchInGuestList(searchWord));
    }
    private static void Stergere(){
        Scanner sc=new Scanner(System.in);
        String firstName,lastName,email,phoneNumber;
        Guest newGuest = new Guest();
        System.out.println("Se sterge o persoana existenta din lista…");
        System.out.println("Alege modul de autentificare, tastand:");
        System.out.println("\n\t\"1 - Nume si prenume ");
        System.out.println("\n\t\"2 - Email");
        System.out.println("\n\t\"3\" - Numar de telefon (format \"+40733386463\")");
        int comandaIntrodusa = sc.nextInt();
        switch(comandaIntrodusa){
            case 1:
                System.out.println("Introduceti numele de familie:");
                lastName = sc.next();
                newGuest.setLastName(lastName);
                System.out.println("Introduceti prenumele:");
                firstName = sc.next();
                newGuest.setFirstName(firstName);
                boolean aReusitEliminarea1 = myGuestList.removeFromGuestList(newGuest,1);
                break;
            case 2:
                System.out.println("Introduceti email-ul: ");
                email = sc.next();
                newGuest.setEmail(email);
                boolean aReusitEliminarea2 = myGuestList.removeFromGuestList(newGuest,2);
                break;
            case 3:
                System.out.println("Introduceti numar de telefon (format „+40733386463“):");
                phoneNumber = sc.next();
                newGuest.setPhoneNumber(phoneNumber);
                boolean aReusitEliminarea3 = myGuestList.removeFromGuestList(newGuest,3);
                break;
        }
    }

    private static void Update(){
        Scanner sc = new Scanner(System.in);
        /*
        Determinam care este guestul care trebuie actualizat (il cautam cu metoda cautareCompleta).
        Determinam ce anume vrem sa actualizam (intrebam utilizatorul, la fel ca la metoda de cautare)
        Memoram noile valori intr-o alta instanta de tip guest, si o transmitem la metoda de actualizare.
        Deci vom transmite instanta de tip guest gasita, folosind metoda de cautare, si instanta de tip guest, nou creata, care contine noile valori.
        Pe langa aceasta transmitem si tipul de actualizare (dupa nume, email,etc)
        La final afisam valorile actualizate (apelam toString pe instanta intoarsa de metoda de update).
        */

        // determinam care este guestul care trebuie sa il actualizam
        Guest guestDeActualizat=cautareCompleta();
        Guest guestCuValoriActualizate= new Guest();
        Guest guestActualizat=null;


        // il intrebam ce vrea sa actualizeze ..
        int tipActualizare = -1;


        System.out.println("Alege modul de actualizare, tastand:");
        System.out.println("\n\t\"1 - Nume si prenume ");
        System.out.println("\n\t\"2 - Email");
        System.out.println("\n\t\"3\" - Numar de telefon (format \"+40733386463\")");
        tipActualizare = sc.nextInt();

        switch(tipActualizare){
            case 1:
                System.out.println("Actualizati numele de familie:");
                String lastName = sc.next();
                guestCuValoriActualizate.setLastName(lastName);
                System.out.println("Actualizati prenumele:");
                String firstName = sc.next();
                guestCuValoriActualizate.setFirstName(firstName);
                guestActualizat = myGuestList.updateGuest(guestDeActualizat,guestCuValoriActualizate,tipActualizare);
                System.out.println(guestActualizat.toString());
                break;
            case 2:
                System.out.println("Actualizati email-ul:");
                String email=sc.nextLine();
                guestCuValoriActualizate.setEmail(email);
                guestActualizat = myGuestList.updateGuest(guestDeActualizat,guestCuValoriActualizate,tipActualizare);
                System.out.println(guestActualizat.toString());
                break;
            case 3:
                System.out.println("Actualizati numar de telefon (format „+40733386463“):");
                String phoneNumber=sc.nextLine();
                guestCuValoriActualizate.setPhoneNumber(phoneNumber);
                guestActualizat = myGuestList.updateGuest(guestDeActualizat,guestCuValoriActualizate,tipActualizare);
                System.out.println(guestActualizat.toString());
                break;
        }
    }
}