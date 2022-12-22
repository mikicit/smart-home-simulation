package dev.mikita.sh.core.report;

public class ReportSystem {
    private final ActivityAndUsageReport activityAndUsageReport = new ActivityAndUsageReport();
    private final HouseConfigurationReport houseConfigurationReport = new HouseConfigurationReport();
    private final ConsumptionReport consumptionReport = new ConsumptionReport();
    private final EventReport eventReport = new EventReport();

    public ActivityAndUsageReport getActivityAndUsageReport() {
        return activityAndUsageReport;
    }
}
