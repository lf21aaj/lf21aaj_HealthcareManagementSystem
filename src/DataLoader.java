import java.io.IOException;
// REMOVED java.nio.file.Path, java.rmi.ServerError -> unused
import java.util.ArrayList;
import java.util.List;

public final class DataLoader
{
    /**
     * Load CSV files into Lists
     * String-based to match
     */
    private DataLoader() {}

    // PATIENTS
    public static List<Patient> loadPatients() throws IOException
    {
        var rows = CSVUtil.readAll(PathsConfig.PATIENTS_CSV, true);
        List<Patient> patients = new ArrayList<>();

        for (var row : rows)
        {
            var r = pad(row, 14);
            patients.add(new Patient(
                    r.get(0), r.get(1), r.get(2), r.get(3), r.get(4), r.get(5), r.get(6), r.get(7),
                    r.get(8), r.get(9), r.get(10), r.get(11), r.get(12), r.get(13)
            ));
        }
        return patients;
    }

    // CLINICIANS
    public static List<Clinician> loadClinicians() throws IOException
    {
        var rows = CSVUtil.readAll(PathsConfig.CLINICIANS_CSV, true);
        List<Clinician> clinicians = new ArrayList<>();

        for (var row : rows)
        {
            var r = pad(row, 12);
            String title = r.get(3).toLowerCase();
            String speciality = r.get(4).toLowerCase();

            // subclassing
            if (title.contains(("nurse")) || speciality.contains("nurse"))
            {
                clinicians.add(new Nurse(r.toArray(new String[0])));
            }
            else if (title.contains("gp") || speciality.contains("general"))
            {
                clinicians.add(new GeneralPractitioner(r.toArray(new String[0])));
            }
            else
            {
                clinicians.add(new SpecialistDoctor(r.toArray(new String[0])));
            }
        }
        return clinicians;
    }

    // APPOINTMENTS
    public static List<Appointment> loadAppointments() throws IOException
    {
        var rows = CSVUtil.readAll(PathsConfig.APPOINTMENTS_CSV, true);
        List<Appointment> appointments = new ArrayList<>();

        for (var row : rows)
        {
            var r = pad(row, 13);
            appointments.add(new Appointment(
                    r.get(0), r.get(1), r.get(2), r.get(3), r.get(4), r.get(5),
                    r.get(6), r.get(7), r.get(8), r.get(9), r.get(10), r.get(11), r.get(12)
            ));
        }
        return appointments;
    }

    // PRESCRIPTIONS
    public static List<Prescription> loadPrescriptions() throws IOException
    {
        var rows = CSVUtil.readAll(PathsConfig.PRESCRIPTIONS_CSV, true);
        List<Prescription> prescriptions = new ArrayList<>();

        for (var row : rows)
        {
            var r = pad(row, 15);
            prescriptions.add(new Prescription(
                 r.get(0), r.get(1), r.get(2), r.get(3), r.get(4), r.get(5), r.get(6),
                 r.get(7), r.get(8), r.get(9), r.get(10), r.get(11), r.get(12), r.get(13), r.get(14)
            ));
        }
        return prescriptions;
    }

    // REFERRALS
    public static List<Referral> loadReferrals() throws IOException
    {
        var rows = CSVUtil.readAll(PathsConfig.REFERRALS_CSV, true);
        List<Referral> referrals = new ArrayList<>();

        for (var row : rows)
        {
            var r = pad(row, 16);
            referrals.add(new Referral(
                    r.get(0), r.get(1), r.get(2), r.get(3), r.get(4), r.get(5), r.get(6), r.get(7),
                    r.get(8), r.get(9), r.get(10), r.get(11), r.get(12), r.get(13), r.get(14), r.get(15)
            ));
        }
        return referrals;
    }

    // pad a row so missing columns do not cause IndexOutOfBoundsException
    private static List<String> pad(List<String> row, int expectedColumns)
    {
        var copy = new ArrayList<>(row);
        while (copy.size() < expectedColumns) copy.add("");
        return copy;
    }

    // verify each CSV file loads and prints counts - testing
    public static void debugSummary()
    {
        try
        {
            System.out.println("----- Data Summary Check -----");
            System.out.println("Patients:       " + loadPatients().size());
            System.out.println("Clinicians:     " + loadClinicians().size());
            System.out.println("Appointments:   " + loadAppointments().size());
            System.out.println("Prescriptions:  " + loadPrescriptions().size());
            System.out.println("Referrals:      " + loadReferrals().size());
            System.out.println("------------------------------");
        } catch (Exception e)
        {
            System.err.println("Error reading files: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
