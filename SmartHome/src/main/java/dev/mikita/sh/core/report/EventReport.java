package dev.mikita.sh.core.report;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.event.AEvent;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 * Class for generating event report
 */
public class EventReport {
    private FileWriter eventReport = null;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public EventReport() {
        try {
            this.eventReport = new FileWriter("report/EventReport.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateReport(AEvent event) throws IOException {
        eventReport.write(String.format("Event type \"%s\" from \"%s\" in \"%s\" [%s]\n",
                event.getClass().getSimpleName(),
                event.getSource().getName(),
                event.getLocation().getName(),
                SHSystem.getInstance().getSimulation().getFormattedTime()));
        eventReport.flush();
    }
}
