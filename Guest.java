public class Guest {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    public Guest(String firstName,String lastName,String email,String phoneNumber){
        this.firstName=firstName;
        this.lastName=lastName;
        this.email=email;
        this.phoneNumber=phoneNumber;
    }
    public Guest(){
        this.firstName=null;
        this.lastName=null;
        this.email=null;
        this.phoneNumber=null;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String toString(){
        return "- Numele de familie: " + this.lastName+"- Prenume: " + this.firstName + "- Email: " + this.email +
                "- Numar de telefon: " + this.phoneNumber;
    }
}
