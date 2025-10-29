public class Patient
{
    private String id;
    private String name;
    private String nhsNumber;
    private String dateOfBirth;
    private String phoneNumber;
    private String email;
    private String address;
    private String gpSurgery;

    public Patient(String id, String name, String nhsNumber, String dateOfBirth, String phoneNumber, String email, String address, String gpSurgery)
    {
        this.id = id;
        this.name = name;
        this.nhsNumber = nhsNumber;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.gpSurgery = gpSurgery;
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }
    public String getNhsNumber()
    {
        return nhsNumber;
    }
    public String getGpSurgery()
    {
        return gpSurgery;
    }

    @Override
    public String toString()
    {
        return name + " (" + nhsNumber + ")";
    }

}
