package dev.mikita.sh.core.report;

import dev.mikita.sh.core.SHSystem;
import dev.mikita.sh.core.simulation.ITimeTracker;
import java.io.IOException;

/**
 * Class for working with reports
 */
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

    /**
     * Returns activity and usage report
     * @return activity and usage report
     */
    public ActivityAndUsageReport getActivityAndUsageReport() {
        return activityAndUsageReport;
    }

    /**
     * Returns consumption report
     * @return consumption report
     */
    public ConsumptionReport getConsumptionReport() {
        return consumptionReport;
    }

    /**
     * Returns event report.
     * @return the event report
     */
    public EventReport getEventReport() {
        return eventReport;
    }

    /**
     * Generates consumption report
     * @throws IOException writing to file is unsuccessful
     */
    private void generateConsumptionReport() throws IOException {
        consumptionReport.generateReport();
    }

    /**
     * Generates activity and usage report
     * @throws IOException writing to file is unsuccessful
     */
    private void generateActivityAndUsageReport() throws IOException {
        activityAndUsageReport.generateReport();
    }

    /**
     * Generates house configuration report
     * @throws IOException writing to file is unsuccessful
     */
    private void generateHouseConfReport() throws IOException {
        houseConfigurationReport.generateReport();
    }

    /**
     * Updates local time
     * @param time time
     * @throws IOException writing to file is unsuccessful
     */
    @Override
    public void update(long time) throws IOException {
        this.time += time;

        if (this.time > 23 * 3600L * 1000000000L && this.time < 24 * 3600L * 1000000000L) {
            generateConsumptionReport();
            generateActivityAndUsageReport();
            this.time = 0;
        }
        if (SHSystem.getInstance().getSimulation().getDay() == 1
                && SHSystem.getInstance().getSimulation().getHour() == 0) {
            generateHouseConfReport();
        }
    }
}
