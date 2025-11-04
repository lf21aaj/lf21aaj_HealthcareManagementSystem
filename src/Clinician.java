public class Clinician
{
    protected String clinicianId;
    protected String firstName;
    protected String lastName;
    protected String title;
    protected String speciality;
    protected String gmcNumber;
    protected String phoneNumber;
    protected String email;
    protected String workplaceId;
    protected String workplaceType;
    protected String employmentStatus;
    protected String startDate;

    // constructor
    public Clinician(String clinicianId, String firstName, String lastName, String title,
                     String speciality, String gmcNumber, String phoneNumber, String email,
                     String workplaceId, String workplaceType, String employmentStatus, String startDate)
    {
        this.clinicianId = clinicianId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.speciality = speciality;
        this.gmcNumber = gmcNumber;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.workplaceId = workplaceId;
        this.workplaceType = workplaceType;
        this.employmentStatus = employmentStatus;
        this.startDate = startDate;
    }

    public String getClinicianId()
    {
        return clinicianId;
    }
    public String getFirstName()
    {
        return firstName;
    }
    public String getLastName()
    {
        return lastName;
    }
    public String getTitle()
    {
        return title;
    }
    public String getSpeciality()
    {
        return speciality;
    }
    public String getGmcNumber()
    {
        return gmcNumber;
    }
    public String getPhoneNumber()
    {
        return phoneNumber;
    }
    public String getEmail()
    {
        return email;
    }
    public String getWorkplaceId()
    {
        return workplaceId;
    }
    public String getWorkplaceType()
    {
        return workplaceType;
    }
    public String getEmploymentStatus()
    {
        return employmentStatus;
    }
    public String getStartDate()
    {
        return startDate;
    }

    public String getDisplayName()
    {
        return (title + " " + firstName + " " + lastName).trim();
    }

    @Override
    public String toString()
    {
        return String.format("%s | %s | %s", clinicianId, getDisplayName(), speciality);
    }
}

// subclasses
class GeneralPractitioner extends Clinician
{
    public GeneralPractitioner(String... fields)
    {
        super(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], fields[7], fields[8],
                fields[9], fields[10], fields[11]);
    }
}

class Nurse extends Clinician
{
    public Nurse(String... fields)
    {
        super(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], fields[7], fields[8],
                fields[9], fields[10], fields[11]);
    }
}

class SpecialistDoctor extends Clinician
{
    public SpecialistDoctor(String... fields)
    {
        super(fields[0], fields[1], fields[2], fields[3], fields[4], fields[5], fields[6], fields[7], fields[8],
                fields[9], fields[10], fields[11]);
    }
}

