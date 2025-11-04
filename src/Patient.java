
public class Patient
{
    private String patientId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String nhsNumber;
    private String gender;
    private String phoneNumber;
    private String email;
    private String address;
    private String postcode;
    private String emergencyContactName;
    private String emergencyContactPhone;

    public Patient(String patientId, String firstName, String lastName, String dateOfBirth,
                   String nhsNumber, String gender, String phoneNumber, String email, String address,
                   String postcode, String emergencyContactName, String emergencyContactPhone)
    {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.nhsNumber = nhsNumber;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.postcode = postcode;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public String getPatientId()
    {
        return patientId;
    }
    public String getFirstName()
    {
        return firstName;
    }
    public String getLastName()
    {
        return lastName;
    }
    public String getDateOfBirth()
    {
        return dateOfBirth;
    }
    public String getNhsNumber()
    {
        return nhsNumber;
    }
    public String getGender()
    {
        return gender;
    }
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public String getEmail()
    {
        return email;
    }
    public String getAddress()
    {
        return address;
    }
    public String getPostcode()
    {
        return postcode;
    }
    public String getEmergencyContactName()
    {
        return emergencyContactName;
    }
    public String getEmergencyContactPhone()
    {
        return emergencyContactPhone;
    }

    public String getFullName()
    {
        return (firstName + " " + lastName).trim();
    }

    @Override
    public String toString()
    {
        return String.format("%s | %s %s | NHS: %s", patientId, firstName, lastName, dateOfBirth, nhsNumber);
    }
}
