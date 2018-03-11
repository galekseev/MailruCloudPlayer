package camo.mailru.api;

/**
 * Created by GAlekseev on 12.03.2018.
 */

public class DiskUsage {

    private FileSize Total;
    /// <summary>
    /// Gets total disk size.
    /// </summary>
    public FileSize getTotal() { return Total; }

    private FileSize Used;
    /// <summary>
    /// Gets used disk size.
    /// </summary>
    public FileSize getUsed() { return Used; }

    /// <summary>
    /// Gets free disk size.
    /// </summary>
    public FileSize getFree()
    {
        return new FileSize(this.Total.getDefaultValue() - this.Used.getDefaultValue());
    }
}