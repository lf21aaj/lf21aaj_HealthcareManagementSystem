import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainFrame extends JFrame
{
    private final PatientsController patientsController;
    private final DefaultTableModel patientsModel = new DefaultTableModel(
            new String[]{"ID","First","Last","DOB","NHS","Gender","Phone","Email","Address",
            "Postcode","EC Name","EC Phone","Reg Date","GP Surgery"
            }, 0
    );

    public MainFrame() throws Exception
    {
        setTitle("Healthcare Management System");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1100, 700);
        setLocationRelativeTo(null);

        var patientsRepo = new PatientsRepository(PathsConfig.PATIENTS_CSV);
        this.patientsController = new PatientsController(patientsRepo);

        var tabs = new JTabbedPane();
        tabs.addTab("Patients", buildPatientsTab());
        setContentPane(tabs);

        // initial build
        refreshPatients();
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
        String id   = JOptionPane.showInputDialog(this, "Patient ID:");
        if (id == null || id.isBlank()) return;
        String fn   = JOptionPane.showInputDialog(this, "First name:");
        String ln   = JOptionPane.showInputDialog(this, "Last name:");
        String dob  = JOptionPane.showInputDialog(this, "Date of birth (YYYY-MM-DD):");
        String nhs  = JOptionPane.showInputDialog(this, "NHS number:");
        String gen  = JOptionPane.showInputDialog(this, "Gender:");
        String ph   = JOptionPane.showInputDialog(this, "Phone:");
        String em   = JOptionPane.showInputDialog(this, "Email:");
        String addr = JOptionPane.showInputDialog(this, "Address:");
        String pc   = JOptionPane.showInputDialog(this, "Postcode:");
        String ecN  = JOptionPane.showInputDialog(this, "Emergency contact name:");
        String ecP  = JOptionPane.showInputDialog(this, "Emergency contact phone:");
        String reg  = JOptionPane.showInputDialog(this, "Registration date (YYYY-MM-DD):");
        String gp   = JOptionPane.showInputDialog(this, "GP Surgery ID:");

        Patient p = new Patient(id, fn, ln, dob, nhs, gen, ph, em, addr, pc, ecN, ecP, reg, gp);
        patientsController.add(p);
        // append to table model
        ((DefaultTableModel) ((JTable)((JScrollPane)getContentPane()
                .getComponent(0)).getViewport().getView()).getModel())
                .addRow(new Object[]{ id, fn, ln, dob, nhs, gen, ph, em, addr, pc, ecN, ecP, reg, gp });
    }


    private void showError(Exception e)
    {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
}
