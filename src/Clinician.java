public class Clinician
{
    private String id;
    private String name;
    private String speciality;
    private String role;
    private String facility;

    public Clinician(String id, String name, String speciality, String role, String facility)
    {
        this.id = id;
        this.name = name;
        this.speciality = speciality;
        this.role = role;
        this.facility = facility;
    }

    public String getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getSpeciality()
    {
        return speciality;
    }

    public String getRole()
    {
        return role;
    }

    public String getFacility()
    {
        return facility;
    }

    @Override
    public String toString()
    {
        String spec = (speciality == null || speciality.isBlank()) ? "" : " (" + speciality +")";
        return name + " - " + role + spec;
    }
}
