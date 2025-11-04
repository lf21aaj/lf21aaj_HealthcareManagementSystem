public class Prescription
{
    private String prescriptionId;
    private String patientId;
    private String clinicianId;
    private String drugName;
    private String dosage;
    private String instructions;
    private String pharmacy;
    private String status;

    public Prescription(String prescriptionId, String patientId, String clinicianId, String drugName, String dosage,
                        String instructions, String pharmacy, String status)
    {
        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.clinicianId = clinicianId;
        this.drugName = drugName;
        this.dosage = dosage;
        this.instructions = instructions;
        this.pharmacy = pharmacy;
        this.status = status;
    }

    public String getPrescriptionId()
    {
        return prescriptionId;
    }
    public String getPatientId()
    {
        return patientId;
    }
    public String getClinicianId()
    {
        return clinicianId;
    }
    public String getDrugName()
    {
        return drugName;
    }
    public String getDosage()
    {
        return dosage;
    }
    public String getInstructions()
    {
        return instructions;
    }
    public String getPharmacy()
    {
        return pharmacy;
    }
    public String getStatus()
    {
        return status;
    }

    @Override
    public String toString()
    {
        return String.format("%s | %s | %s | %s (%s) | %s", prescriptionId, patientId, clinicianId, drugName, dosage, instructions);
    }
}
