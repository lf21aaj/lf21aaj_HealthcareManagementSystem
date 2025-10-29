public class Appointment
{
    private String id;
    private String patientId;
    private String clinicianId;
    private String date;
    private String time;
    private String reason;
    private String status;

    public Appointment(String id, String patientId, String clinicianId, String date, String time, String reason, String status)
    {
        this.id = id;
        this.patientId = patientId;
        this.clinicianId = clinicianId;
        this.date = date;
        this.time = time;
        this.reason = reason;
        this.status = status;
    }

    public String getId()
    {
        return id;
    }
    public String getPatientId()
    {
        return patientId;
    }
    public String getClinicianId()
    {
        return clinicianId;
    }
    public String getDate()
    {
        return date;
    }
    public String getTime()
    {
        return time;
    }
    public String getReason()
    {
        return reason;
    }
    public String getStatus()
    {
        return status;
    }

    // setter to update appointment status - to cancel or complete appointment
    public void setStatus(String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "Appointment " + id + " | Patient: " + patientId + " | Clinician: " + clinicianId + " | " + date + " " + time + " (" + status + ")";
    }
}
