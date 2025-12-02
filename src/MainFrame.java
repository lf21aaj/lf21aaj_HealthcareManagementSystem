import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainFrame extends JFrame
{
    private final PatientsController patientsController;
    private final CliniciansController cliniciansController;

    private final DefaultTableModel patientsModel = new DefaultTableModel(
            new String[]{"ID","First","Last","DOB","NHS","Gender","Phone","Email","Address",
            "Postcode","EC Name","EC Phone","Reg Date","GP Surgery"
            }, 0
    );

    private final DefaultTableModel cliniciansModel = new DefaultTableModel(
            new String[]{"ID","First","Last","Title","Specialty","GMC No","Phone","Email",
            "Workplace ID","Workplace Type","Employment Status","Start Date"
            }, 0
    );

    private final JTable patientsTable = new JTable(patientsModel);
    private final JTable cliniciansTable = new JTable(cliniciansModel);

    public MainFrame() throws Exception
    {
        setTitle("Healthcare Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);

        PatientsRepository patientsRepo = new PatientsRepository(PathsConfig.PATIENTS_CSV);
        CliniciansRepository cliniciansRepo = new CliniciansRepository(PathsConfig.CLINICIANS_CSV);

        this.patientsController = new PatientsController(patientsRepo);
        this.cliniciansController = new CliniciansController(cliniciansRepo);

        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Patients", buildPatientsTab());
        tabs.addTab("Clinicians", buildCliniciansTab());

        setContentPane(tabs);

        // initial builds
        refreshPatients();
        refreshClinicians();
    }

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

    private void showError(Exception e)
    {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
