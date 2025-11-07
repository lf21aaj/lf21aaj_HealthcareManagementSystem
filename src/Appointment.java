public class Appointment
{
    private String appointmentId;
    private String patientId;
    private String clinicianId;
    private String facilityId;
    private String appointmentDate;
    private String appointmentTime;
    private String durationMinutes;
    private String appointmentType;
    private String reasonForVisit;
    private String status;
    private String notes;
    private String createdDate;
    private String lastModified;

    public Appointment(String appointmentId, String patientId, String clinicianId, String facilityId, String appointmentDate, String appointmentTime,
                       String durationMinutes, String status, String appointmentType, String reasonForVisit, String notes, String createdDate, String lastModified)
    {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.clinicianId = clinicianId;
        this.facilityId = facilityId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.durationMinutes = durationMinutes;
        this.status = status;
        this.appointmentType = appointmentType;
        this.reasonForVisit = reasonForVisit;
        this.notes = notes;
        this.createdDate = createdDate;
        this.lastModified = lastModified;
    }

    public String  getAppointmentId()
    {
        return appointmentId;
    }
    public String  getPatientId()
    {
        return patientId;
    }
    public String  getClinicianId()
    {
        return clinicianId;
    }
    public String  getFacilityId()
    {
        return facilityId;
    }
    public String  getAppointmentDate()
    {
        return appointmentDate;
    }
    public String  getAppointmentTime()
    {
        return appointmentTime;
    }
    public String  getDurationMinutes()
    {
        return durationMinutes;
    }
    public String  getAppointmentType()
    {
        return appointmentType;
    }
    public String  getReasonForVisit()
    {
        return reasonForVisit;
    }
    public String  getStatus()
    {
        return status;
    }
    public String  getNotes()
    {
        return notes;
    }
    public String  getCreatedDate()
    {
        return createdDate;
    }
    public String  getLastModified()
    {
        return lastModified;
    }

    @Override
    public String toString()
    {
        return String.format("%s | %s | %s | %s %s", appointmentId, patientId, clinicianId, appointmentDate, appointmentTime);
    }
}
