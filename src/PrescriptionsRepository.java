import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PrescriptionsRepository implements Repository<Prescription, String> {
    private final List<Prescription> data = new ArrayList<>();
    private final Path path;

    public PrescriptionsRepository(Path csvPath) throws IOException {
        this.path = csvPath;
        data.addAll(DataLoader.loadPrescriptions());
    }

    @Override
    public List<Prescription> findAll() {
        return Collections.unmodifiableList(data);
    }

    @Override
    public Prescription findById(String id) {
        for (Prescription p : data) {
            if (p.getPrescriptionId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void add(Prescription p) {
        data.add(p);
    }

    @Override
    public void update(Prescription p) {
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).getPrescriptionId().equals(p.getPrescriptionId())) {
                data.set(i, p);
                return;
            }
        }
    }

    @Override
    public boolean deleteById(String id) {
        return data.removeIf(p -> p.getPrescriptionId().equals(id));
    }

    @Override
    public void saveAll() throws IOException {
        String header = String.join(",",
                "prescription_id",
                "patient_id",
                "clinician_id",
                "appointment_id",
                "prescription_date",
                "medication_name",
                "dosage",
                "frequency",
                "duration_days",
                "quantity",
                "instructions",
                "pharmacy_name",
                "status",
                "issue_date",
                "collection_date"
        );

        List<String> lines = new ArrayList<>();
        lines.add(header);

        for (Prescription p : data) {
            lines.add(String.join(",",
                    p.getPrescriptionId(),
                    p.getPatientId(),
                    p.getClinicianId(),
                    p.getAppointmentId(),
                    p.getPrescriptionDate(),
                    p.getMedicationName(),
                    p.getDosage(),
                    p.getFrequency(),
                    p.getDurationDays(),
                    p.getQuantity(),
                    p.getInstructions(),
                    p.getPharmacyName(),
                    p.getStatus(),
                    p.getIssueDate(),
                    p.getCollectionDate()
            ));
        }
        CSVUtil.writeAll(path, lines);

    }
}
