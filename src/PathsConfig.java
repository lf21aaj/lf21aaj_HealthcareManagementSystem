import java.nio.file.Path;

public final class PathsConfig
{
    private PathsConfig() {}

    public static final Path DATA_DIR           = Path.of("data");
    public static final Path PATIENTS_CSV       = DATA_DIR.resolve("patients.csv");
    public static final Path CLINICIANS_CSV     = DATA_DIR.resolve("clinicians.csv");
    public static final Path APPOINTMENTS_CSV   = DATA_DIR.resolve("appointments.csv");
    public static final Path PRESCRIPTIONS_CSV  = DATA_DIR.resolve("prescriptions.csv");
    public static final Path REFERRALS_CSV      = DATA_DIR.resolve("referrals.csv");
    public static final Path FACILITIES_CSV     = DATA_DIR.resolve("facilities.csv");
    public static final Path STAFF_CSV          = DATA_DIR.resolve("staff.csv");

    public static final Path OUTBOX_DIR         = Path.of("outbox");
}
