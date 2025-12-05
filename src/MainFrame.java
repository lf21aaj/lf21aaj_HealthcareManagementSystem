import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainFrame extends JFrame
{
    private final PatientsController patientsController;
    private final CliniciansController cliniciansController;
    private final AppointmentsController appointmentsController;

    private final DefaultTableModel patientsModel = new DefaultTableModel(
            new String[]{"ID","First","Last","DOB","NHS","Gender","Phone","Email","Address",
            "Postcode","EC Name","EC Phone","Reg Date","GP Surgery"
            }, 0
    );

    private final DefaultTableModel cliniciansModel = new DefaultTableModel(
            new String[]{"ID","First","Last","Title","Speciality","GMC No","Phone","Email",
            "Workplace ID","Workplace Type","Employment Status","Start Date"
            }, 0
    );

    private final DefaultTableModel appointmentsModel = new DefaultTableModel(
            new String[]{"ID", "Patient ID", "Clinician ID", "Facility ID", "Date", "Time", "Duration",
            "Type", "Status", "Reason", "Notes", "Created", "Last Modified"
            }, 0
    );

    private final JTable patientsTable = new JTable(patientsModel);
    private final JTable cliniciansTable = new JTable(cliniciansModel);
    private final JTable appointmentsTable = new JTable(appointmentsModel);

    public MainFrame() throws Exception
    {
        setTitle("Healthcare Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 750);
        setLocationRelativeTo(null);

        PatientsRepository patientsRepo = new PatientsRepository(PathsConfig.PATIENTS_CSV);
        CliniciansRepository cliniciansRepo = new CliniciansRepository(PathsConfig.CLINICIANS_CSV);
        AppointmentsRepository appointmentsRepo = new AppointmentsRepository(PathsConfig.APPOINTMENTS_CSV);

        this.patientsController = new PatientsController(patientsRepo);
        this.cliniciansController = new CliniciansController(cliniciansRepo);
        this.appointmentsController = new AppointmentsController(appointmentsRepo);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Patients", buildPatientsTab());
        tabs.addTab("Clinicians", buildCliniciansTab());
        tabs.addTab("Appointments", buildAppointmentsTab());

        setContentPane(tabs);

        // initial builds
        refreshPatients();
        refreshClinicians();
        refreshAppointments();
    }

    /* ====================================
    *  ============= PATIENTS =============
    *  ==================================== */

    private JPanel buildPatientsTab()
    {
        JPanel panel = new JPanel(new BorderLayout());

        JTable table = new JTable(patientsModel);
        table.setAutoCreateRowSorter(true);

        // buttons -> strart with save and delete
        JButton saveBtn = new JButton("Save CSV");
        JButton delBtn = new JButton("Delete Selected");
        JButton addBtn = new JButton("Add Patient");

        addBtn.addActionListener(e -> addPatientDialog());
        saveBtn.addActionListener(e -> {
            try { patientsController.save(); JOptionPane.showMessageDialog(this, "Saved."); }
            catch (Exception ex) { showError(ex); }
        });

        delBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row < 0) { JOptionPane.showMessageDialog(this, "Please select a row."); return;}

            int modelRow = table.convertRowIndexToModel(row);
            String id = (String) patientsModel.getValueAt(modelRow, 0);
            if (patientsController.delete(id))
            {
                patientsModel.removeRow(modelRow);
            } else
            {
                JOptionPane.showMessageDialog(this, "Could not delete ID " +id);
            }
        });

        JPanel south = new JPanel();
        south.add(saveBtn);
        south.add(delBtn);
        south.add(addBtn);

        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        panel.add(south, BorderLayout.SOUTH);
        return panel;
    }

    private void refreshPatients()
    {
        patientsModel.setRowCount(0);
        for (Patient p : patientsController.all())
        {
            patientsModel.addRow(new Object[]{
                    // ERROR: Putting all data into patient field, think it might be here
                    // TODO: Look into ^
                    p.getPatientId(), p.getFirstName(), p.getLastName(), p.getDateOfBirth(),
                    p.getNhsNumber(), p.getGender(), p.getPhoneNumber(), p.getEmail(),
                    p.getAddress(), p.getPostcode(), p.getEmergencyContactName(), p.getEmergencyContactPhone(),
                    p.getRegistrationDate(), p.getGpSurgeryId()
            });
        }
    }

    private void addPatientDialog() {
        String id   = JOptionPane.showInputDialog(this, "Patient ID: ");
        if (id == null || id.isBlank()) return;
        String fn   = JOptionPane.showInputDialog(this, "First name: ");
        String ln   = JOptionPane.showInputDialog(this, "Last name: ");
        String dob  = JOptionPane.showInputDialog(this, "Date of birth (YYYY-MM-DD): ");
        String nhs  = JOptionPane.showInputDialog(this, "NHS number: ");
        String gen  = JOptionPane.showInputDialog(this, "Gender: ");
        String ph   = JOptionPane.showInputDialog(this, "Phone: ");
        String em   = JOptionPane.showInputDialog(this, "Email: ");
        String addr = JOptionPane.showInputDialog(this, "Address: ");
        String pc   = JOptionPane.showInputDialog(this, "Postcode: ");
        String ecN  = JOptionPane.showInputDialog(this, "Emergency contact name: ");
        String ecP  = JOptionPane.showInputDialog(this, "Emergency contact phone: ");
        String reg  = JOptionPane.showInputDialog(this, "Registration date (YYYY-MM-DD): ");
        String gp   = JOptionPane.showInputDialog(this, "GP Surgery ID: ");

        Patient p = new Patient(id, fn, ln, dob, nhs, gen, ph, em, addr, pc, ecN, ecP, reg, gp);
        patientsController.add(p);
        // append to table model
        ((DefaultTableModel) ((JTable)((JScrollPane)getContentPane()
                .getComponent(0)).getViewport().getView()).getModel())
                .addRow(new Object[]{ id, fn, ln, dob, nhs, gen, ph, em, addr, pc, ecN, ecP, reg, gp });
    }

    /* =====================================
     *  ============ CLINICIANS ============
     *  ==================================== */

    private JPanel buildCliniciansTab()
    {
        JPanel panel = new JPanel(new BorderLayout());

        cliniciansTable.setAutoCreateRowSorter(true);

        JButton addBtn = new JButton("Add Clinician");
        JButton delBtn = new JButton("Delete Clinician");
        JButton saveBtn = new JButton("Save CSV");

        addBtn.addActionListener(e -> addClinicianDialog());

        delBtn.addActionListener(e -> {
            int row = cliniciansTable.getSelectedRow();
            if (row < 0) { JOptionPane.showMessageDialog(this, "Please select a row."); return;}

            int modelRow = cliniciansTable.convertRowIndexToModel(row);
            String id = (String) cliniciansModel.getValueAt(modelRow, 0);
            if (cliniciansController.delete(id))
            {
                cliniciansModel.removeRow(modelRow);
            } else
            {
                JOptionPane.showMessageDialog(this, "Could not delete ID " +id);
            }
        });

        saveBtn.addActionListener(e -> {
            try
            {
                cliniciansController.save();
                JOptionPane.showMessageDialog(this, "Saved.");
            } catch (Exception ex) { showError(ex); }

        });

        JPanel buttons = new JPanel();
        buttons.add(addBtn);
        buttons.add(delBtn);
        buttons.add(saveBtn);

        panel.add(new JScrollPane(cliniciansTable), BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.SOUTH);
        return panel;
    }

    private void refreshClinicians()
    {
        cliniciansModel.setRowCount(0);
        for (Clinician c : cliniciansController.all())
        {
            cliniciansModel.addRow(new Object[]{
                    c.getClinicianId(),
                    c.getFirstName(),
                    c.getLastName(),
                    c.getTitle(),
                    c.getSpeciality(),
                    c.getGmcNumber(),
                    c.getPhoneNumber(),
                    c.getEmail(),
                    c.getWorkplaceId(),
                    c.getWorkplaceType(),
                    c.getEmploymentStatus(),
                    c.getStartDate()
            });
        }
    }

    private void addClinicianDialog()
    {
        String id = JOptionPane.showInputDialog(this, "Clinician ID: ");
        if (id == null || id.isBlank()) return;

        String fn = JOptionPane.showInputDialog(this, "First name: ");
        if (fn == null) return;
        String ln = JOptionPane.showInputDialog(this, "Last name: ");
        if (ln == null) return;
        String title = JOptionPane.showInputDialog(this, "Title: ");
        if (title == null) return;
        String speciality = JOptionPane.showInputDialog(this, "Speciality: ");
        if (speciality == null) return;
        String gmc =  JOptionPane.showInputDialog(this, "GMC number: ");
        if (gmc == null) return;
        String phone =  JOptionPane.showInputDialog(this, "Phone number: ");
        if (phone == null) return;
        String email =  JOptionPane.showInputDialog(this, "Email: ");
        if (email == null) return;
        String workplaceId = JOptionPane.showInputDialog(this, "Workplace ID: ");
        if (workplaceId == null) return;
        String workplaceType = JOptionPane.showInputDialog(this, "Workplace type: ");
        if (workplaceType == null) return;
        String status = JOptionPane.showInputDialog(this, "Employment status: ");
        if (status == null) return;
        String startDate = JOptionPane.showInputDialog(this, "Start date (YYYY-MM-DD): ");
        if (startDate == null) return;

        Clinician c = new Clinician(
                id, fn, ln, title, speciality, gmc, phone, email,
                workplaceId, workplaceType, status, startDate
        );
        cliniciansController.add(c);

        cliniciansModel.addRow(new Object[]{
                id, fn, ln, title, speciality, gmc, phone, email,
                workplaceId, workplaceType, status, startDate
        });
    }

    /* =====================================
     *  =========== APPOINTMENTS ===========
     *  ==================================== */

    private JPanel buildAppointmentsTab()
    {
        JPanel panel = new JPanel(new BorderLayout());

        JTable table = new JTable(appointmentsModel);
        table.setAutoCreateRowSorter(true);

        JScrollPane scrollPane = new JScrollPane(table);

        JButton addBtn = new JButton("Add Appointment");
        JButton deleteBtn = new JButton("Delete Appointments");
        JButton saveBtn = new JButton("Save CSV");

        deleteBtn.addActionListener(e -> {
            int viewRow = table.getSelectedRow();
            if (viewRow < 0) {
                JOptionPane.showMessageDialog(this, "Please select a row.");
                return;
            }
            int modelRow = table.convertRowIndexToModel(viewRow);
            String id = (String) table.getValueAt(modelRow, 0);
            if (appointmentsController.delete(id))
            {
                appointmentsModel.removeRow(modelRow);
            } else {
                JOptionPane.showMessageDialog(this, "Could not delete appointment ID " + id);
            }
        });

        saveBtn.addActionListener(e -> {
            try
            {
                appointmentsController.save();
                JOptionPane.showMessageDialog(this, "Appointments saved.");
            } catch (Exception ex) { showError(ex); }
        });

        addBtn.addActionListener(e -> addAppointmentDialog());

        JPanel south = new JPanel();
        south.add(addBtn);
        south.add(deleteBtn);
        south.add(saveBtn);

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(south, BorderLayout.SOUTH);
        return panel;
    }


    private void refreshAppointments() {
        appointmentsModel.setRowCount(0);
        for (Appointment a : appointmentsController.all()) {
            appointmentsModel.addRow(new Object[]{
                a.getAppointmentId(), a.getPatientId(), a.getClinicianId(), a.getFacilityId(),
                a.getAppointmentDate(), a.getAppointmentTime(), a.getDurationMinutes(), a.getAppointmentType(),
                a.getStatus(), a.getReasonForVisit(), a.getNotes(), a.getCreatedDate(), a.getLastModified()
            });
        }
    }

    private void addAppointmentDialog()
    {
        String id = JOptionPane.showInputDialog(this, "Appointment ID: ");
        if (id == null || id.isBlank()) return;

        String patientId = JOptionPane.showInputDialog(this, "Patient ID:");
        if (patientId == null) return;

        String clinicianId = JOptionPane.showInputDialog(this, "Clinician ID:");
        if (clinicianId == null) return;

        String facilityId = JOptionPane.showInputDialog(this, "Facility ID:");
        if (facilityId == null) return;

        String date = JOptionPane.showInputDialog(this, "Appointment date (YYYY-MM-DD):");
        if (date == null) return;

        String time = JOptionPane.showInputDialog(this, "Appointment time (HH:MM):");
        if (time == null) return;

        String durationMinutes = JOptionPane.showInputDialog(this, "Duration in minutes:");
        if (durationMinutes == null) return;

        String type = JOptionPane.showInputDialog(this, "Appointment type (e.g. Consultation, Follow-up):");
        if (type == null) return;

        String status = JOptionPane.showInputDialog(this, "Status (e.g. Scheduled, Completed, Cancelled):");
        if (status == null) return;

        String reason = JOptionPane.showInputDialog(this, "Reason for visit:");
        if (reason == null) return;

        String notes = JOptionPane.showInputDialog(this, "Notes (optional):");
        if (notes == null) notes = "";

        String createdDate = JOptionPane.showInputDialog(this, "Created date (YYYY-MM-DD):");
        if (createdDate == null) return;

        String lastModified = JOptionPane.showInputDialog(this, "Last modified date (YYYY-MM-DD):");
        if (lastModified == null) return;

        Appointment a = new Appointment(
                id,
                patientId,
                clinicianId,
                facilityId,
                date,
                time,
                durationMinutes,
                type,
                status,
                reason,
                notes,
                createdDate,
                lastModified
        );

        appointmentsController.add(a);

        appointmentsModel.addRow(new Object[]{
                id,
                patientId,
                clinicianId,
                facilityId,
                date,
                time,
                durationMinutes,
                type,
                status,
                reason,
                notes,
                createdDate,
                lastModified
        });

    }

    private void showError(Exception e)
    {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
