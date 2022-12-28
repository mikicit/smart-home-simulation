package dev.mikita.sh.core.report;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.simulation.ITimeTracker;
import java.io.IOException;

public class ReportSystem implements ITimeTracker {
    private long time = 0;
    private final ActivityAndUsageReport activityAndUsageReport = new ActivityAndUsageReport();
    private final HouseConfigurationReport houseConfigurationReport = new HouseConfigurationReport();
    private final ConsumptionReport consumptionReport = new ConsumptionReport();
    private final EventReport eventReport = new EventReport();

    public ReportSystem() {
        // Init
        SHSystem.getInstance().getSimulation().subscribe(this);
    }

    public ActivityAndUsageReport getActivityAndUsageReport() {
        return activityAndUsageReport;
    }

    public ConsumptionReport getConsumptionReport() {
        return consumptionReport;
    }

    private void generateConsumptionReport() throws IOException {
        consumptionReport.generateReport();
    }

    private void generateActivityAndUsageReport() throws IOException {
        activityAndUsageReport.generateReport();
    }

    @Override
    public void update(long time) throws IOException {
        this.time += time;

        if (this.time > 23 * 3600L * 1000000000L && this.time < 24 * 3600L * 1000000000L) {
            generateConsumptionReport();
            generateActivityAndUsageReport();
            this.time = 0;
        }
    }
}
