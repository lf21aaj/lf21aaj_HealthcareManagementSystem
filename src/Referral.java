public class Referral
{
    private String id;
    private String patientId;
    private String gpId;
    private String reason;
    private String clinicalSummary;
    private String urgency;
    private String status;
    private String createdAt;

    public Referral(String id, String patientId, String gpId, String reason, String clinicalSummary, String urgency, String status, String createdAt)
    {
        this.id = id;
        this.patientId = patientId;
        this.gpId = gpId;
        this.reason = reason;
        this.clinicalSummary = clinicalSummary;
        this.urgency = urgency;
        this.status = status;
        this.createdAt = createdAt;
    }

    public String getId()
    {
        return id;
    }

    public String  getPatientId()
    {
        return patientId;
    }

    public String  getGpId()
    {
        return gpId;
    }

    public String  getReason()
    {
        return reason;
    }

    public String  getClinicalSummary()
    {
        return clinicalSummary;
    }

    public String  getUrgency()
    {
        return urgency;
    }

    public String  getStatus()
    {
        return status;
    }

    public String  getCreatedAt()
    {
        return createdAt;
    }

    // setters for GUI edit actions
    public void setClinicalSummary(String clinicalSummary)
    {
        this.clinicalSummary = clinicalSummary;
    }

    public void setUrgency(String urgency)
    {
        this.urgency = urgency;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return id + " | Patient: " + patientId + " | GP: " + gpId + " | " + urgency + " | " + status + " | " + reason;
    }
}
