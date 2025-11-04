public class Referral
{
    private String referralId;
    private String patientId;
    private String gpId;
    private String reason;
    private String clinicalSummary;
    private String urgency;
    private String status;
    private String createdAt;
    private String targetWorkplaceId;

    public Referral(String referralId, String patientId, String gpId, String reason, String clinicalSummary, String urgency,
                    String status, String createdAt, String targetWorkplaceId)
    {
        this.referralId = referralId;
        this.patientId = patientId;
        this.gpId = gpId;
        this.reason = reason;
        this.clinicalSummary = clinicalSummary;
        this.urgency = urgency;
        this.status = status;
        this.createdAt = createdAt;
        this.targetWorkplaceId = targetWorkplaceId;
    }

    public String getReferralId()
    {
        return referralId;
    }
    public String getPatientId()
    {
        return patientId;
    }
    public String getGpId()
    {
        return gpId;
    }
    public String getReason()
    {
        return reason;
    }
    public String getClinicalSummary()
    {
        return clinicalSummary;
    }
    public String getUrgency()
    {
        return urgency;
    }
    public String getStatus()
    {
        return status;
    }
    public String getCreatedAt()
    {
        return createdAt;
    }
    public String getTargetWorkplaceId()
    {
        return targetWorkplaceId;
    }

    @Override
    public String toString()
    {
        return String.format("%s | %s | %s | %s (%s)", referralId, patientId, gpId, reason, urgency);
    }
}
