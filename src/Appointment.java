

public class Appointment
{
    private String appointmentId;
    private String patientId;
    private String clinicianId;
    private String date;
    private String time;
    private String reason;
    private String status;
    private String location;

    public Appointment(String patientId, String clinicianId, String date, String time, String reason,
                       String status, String location)
    {
        this.patientId = patientId;
        this.clinicianId = clinicianId;
        this.date = date;
        this.time = time;
        this.reason = reason;
        this.status = status;
        this.location = location;
    }

    public String getAppointmentId()
    {
        return appointmentId;
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
    public String getLocation()
    {
        return location;
    }

    @Override
    public String toString()
    {
        return String.format("%s | %s | %s | %s %s", appointmentId, patientId, clinicianId, date, time);
    }
}
