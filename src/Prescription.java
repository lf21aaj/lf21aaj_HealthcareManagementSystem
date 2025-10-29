public class Prescription {
    private String id;
    private String patientId;
    private String clinicianId;
    private String drugName;
    private String dosage;
    private String instructions;
    private String pharmacy;
    private String status;

    public Prescription(String id, String patientId, String clinicianId, String drugName, String dosage, String instructions, String pharmacy, String status) {
        this.id = id;
        this.patientId = patientId;
        this.clinicianId = clinicianId;
        this.drugName = drugName;
        this.dosage = dosage;
        this.instructions = instructions;
        this.pharmacy = pharmacy;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getPatiendId() {
        return patientId;
    }

    public String getClinicianId() {
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

    // setters for edit actions in the GUI
    public void setInstructions(String instructions)
    {
        this.instructions = instructions;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public void setPharmacy(String pharmacy)
    {
        this.pharmacy = pharmacy;
    }

    @Override
    public String toString()
    {
        return id + " | " + drugName + " (" + dosage + ") - " + status;
    }
}
