import javax.swing.*;

public class MainApp {
    public static void main(String[] args)
    {
        PathsConfig.verifyFiles();
        DataLoader.debugSummary();
        // TODO: NEEDS FIXING
        SwingUtilities.invokeLater(() -> {
            try { new MainFrame().setVisible(true); } catch (Exception e) { e.printStackTrace(); }
        });
    }
}
