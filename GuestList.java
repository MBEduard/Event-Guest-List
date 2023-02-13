import java.util.ArrayList;
import java.util.Scanner;

public class GuestList {

    private final int seatsAvailable;
    private ArrayList<Guest> participants;
    private ArrayList<Guest> waitList;

    public ArrayList<Guest> getParticipants() {
        return participants;
    }

    public ArrayList<Guest> getWaitList() {
        return waitList;
    }

    public GuestList(int seatsAvailable, ArrayList<Guest> participants, ArrayList<Guest> waitList){
        this.seatsAvailable=seatsAvailable;
        this.participants=participants;
        this.waitList=waitList;
    }
    public GuestList(int seatsAvailable){
        this.seatsAvailable = seatsAvailable;
        this.participants = new ArrayList<Guest>();
        this.waitList = new ArrayList<Guest>();
    }


    /// Metoda de adaugare

    public int addInGuestList(Guest guestToAdd) {

        Guest guestGasitInParticipants;
        Guest guestGasitInWaitList;
        guestGasitInParticipants=SearchGuestInList(guestToAdd,this.participants,0);
        guestGasitInWaitList=SearchGuestInList(guestToAdd,this.waitList,0);


        if(guestGasitInParticipants == null && this.participants.size()<seatsAvailable)
        {
            this.participants.add(guestToAdd);
            return 0;
        } else
            if (guestGasitInWaitList == null && this.participants.size()>=seatsAvailable){
            this.waitList.add(guestToAdd);
            return this.waitList.size();
        } else return -1;


    }

    public Guest SearchGuestInList(Guest guestToSearch,ArrayList<Guest> listaDeCautat, int tipCautare){
        /*
        variabila tipCautare determina modul de cautare a unui guest, si anume:
        0 - cautare dupa toate campurile (nume si prenume, email si numar de telefon)
        1 - cautare dupa nume si prenume
        2 - cautare dupa email
        3 - cautare dupa numar de telefon
        * */
        Guest guestFound=null;
        switch (tipCautare){
            case 0:
                guestFound = searchByName(guestToSearch.getLastName(), guestToSearch.getFirstName(),listaDeCautat );
                if(guestFound==null)
                    guestFound = searchByEmail(guestToSearch.getEmail(),listaDeCautat);
                if(guestFound==null)
                    guestFound = searchByPhoneNumber(guestToSearch.getPhoneNumber(),listaDeCautat);
                break;
            case 1:
                guestFound = searchByName(guestToSearch.getLastName(), guestToSearch.getFirstName(),listaDeCautat );
                break;
            case 2:
                guestFound = searchByEmail(guestToSearch.getEmail(),listaDeCautat);
                break;
            case 3:
                guestFound = searchByPhoneNumber(guestToSearch.getPhoneNumber(),listaDeCautat);
                break;
        }

        return guestFound;
    }


    public boolean removeFromGuestList(Guest guestToRemove,int tipCautare) {

        Guest guestExistentInParticipants=this.SearchGuestInList(guestToRemove,this.participants,tipCautare);
        Guest guestExistentInWaitList=this.SearchGuestInList(guestToRemove,this.waitList,tipCautare);

        boolean aFostEliminat=false;

        if(this.participants.contains(guestExistentInParticipants)){
            this.participants.remove(guestExistentInParticipants);
            aFostEliminat = true;

            // luam prima pozitie din waitList si o adaugam la participanti;
            if(this.waitList.size()>0)
            {
                Guest primulGuestDinWaitList=this.waitList.get(0);
                this.waitList.remove(0);
                this.participants.add(primulGuestDinWaitList);
            }

        }

        if(this.waitList.contains(guestExistentInWaitList)) {
            this.waitList.remove(guestExistentInWaitList);
            aFostEliminat = true;
        }
        return aFostEliminat;
    }
    public void available(){
        System.out.println("Numarul de locuri ramase:"+ (seatsAvailable-this.participants.size()));
    }
    public void waitlistNumber(){
        System.out.println("Dimensiunea listei de asteptare: "+this.waitList.size());
    }
    public void showWaitlist(){
        if(this.waitList.size()==0){
            System.out.println("Lista de asteptare este goala…");
        }
        for(Guest i:waitList){
            System.out.println(i);
        }
    }
    public void participantsNumber(){
        System.out.println("Numarul de participanti: " + this.participants.size());
    }
    public void showParticipants(){
        if(this.participants.size()==0){
            System.out.println("Niciun participant inscris…");
        }
        for(Guest i:participants){
            System.out.println(i);
        }
    }

    public void subscriberNumber(){
        System.out.println("Numarul total de persoane: " + (this.participants.size()+this.waitList.size()));
    }

    public Guest updateGuest(Guest existingGuest, Guest updatedGuest, int tipActualizare){

        int indexParticipants=this.participants.indexOf(existingGuest);
        int indexWaitList=this.waitList.indexOf(existingGuest);

        switch(tipActualizare)
        {
            case 1:// actualizare dupa nume si prenume
                if(indexParticipants>=0)
                {
                    this.participants.get(indexParticipants).setLastName(updatedGuest.getLastName());
                    this.participants.get(indexParticipants).setFirstName(updatedGuest.getFirstName());

                }
                else{
                    this.waitList.get(indexWaitList).setLastName(updatedGuest.getLastName());
                    this.waitList.get(indexWaitList).setFirstName(updatedGuest.getFirstName());

                }
                break;
            case 2:
                if(indexParticipants>=0){
                    this.participants.get(indexParticipants).setEmail(updatedGuest.getEmail());
                }
                else{
                    this.waitList.get(indexWaitList).setEmail(updatedGuest.getEmail());
                }
                break;
            case 3:
                if(indexParticipants>=0){
                    this.participants.get(indexParticipants).setPhoneNumber(updatedGuest.getPhoneNumber());
                }
                else{
                    this.waitList.get(indexWaitList).setPhoneNumber(updatedGuest.getPhoneNumber());
                }
                break;
        }

        // intoarcem instanta de tip guest din lista, care este deja actualizata.
        if(indexParticipants>=0)
            return this.participants.get(indexParticipants);
        else
            return this.waitList.get(indexWaitList);
    }
    public String partialSearchInGuestList(String searchWord){
        if (searchWord != null ) {
            searchWord = searchWord.toLowerCase().trim();
        }
        ArrayList<Guest> searchResult = new ArrayList<Guest>();
        for(Guest i:participants){
            if((i.getFirstName().toLowerCase().contains(searchWord)&&i.getLastName().toLowerCase().contains(searchWord))
                    ||i.getEmail().toLowerCase().contains(searchWord) ||i.getPhoneNumber().contains(searchWord)){
                searchResult.add(i);
            }
        }
        for(Guest i:waitList){
            if((i.getFirstName().toLowerCase().contains(searchWord)&&i.getLastName().toLowerCase().contains(searchWord))
                    ||i.getEmail().toLowerCase().contains(searchWord) ||i.getPhoneNumber().contains(searchWord)){
                searchResult.add(i);
            }
        }
        return searchResult.toString()+"\n\t";
    }

    /*
    Metode cautare
    * */

    private Guest searchByName(String lastName,String firstName,ArrayList<Guest> listaDeCautat){
        /*
        Trebuie sa caut dupa nume si prenume in lista participanti si in lista de asteptare.
        Daca gasesc, intorc true, daca nu false;
         */

        if (firstName != null ) {
            firstName = firstName.toLowerCase().trim();
        }
        if (lastName != null ) {
            lastName = lastName.toLowerCase().trim();
        }

        for(Guest i:listaDeCautat){
            if((i.getLastName().toLowerCase().equals(lastName)&&i.getFirstName().toLowerCase().equals(firstName))){
                return i;
            }
            return null;
        }



        return null;
    }
    private Guest searchByEmail(String email,ArrayList<Guest> listaDeCautat){
        if (email != null ) {
            email = email.toLowerCase().trim();
        }
        for(Guest guest:listaDeCautat){
            if( guest.getEmail().toLowerCase().equals(email))
                return guest;
        }

        return null;
    }
    private Guest searchByPhoneNumber(String phoneNo,ArrayList<Guest> listaDeCautat){
        if (phoneNo != null ) {
            phoneNo = phoneNo.toLowerCase().trim();
        }
        for(Guest guest:listaDeCautat){
            if(guest.getPhoneNumber().toLowerCase().equals(phoneNo))
                return guest;
        }

        return null;
    }

    @Override
    public String toString(){
        return super.toString();
    }

}
